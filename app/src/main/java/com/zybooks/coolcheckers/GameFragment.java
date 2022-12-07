package com.zybooks.coolcheckers;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.Random;


public class GameFragment extends Fragment {

    private CheckersGame mGame;
    private Button mConfirmButton;
    private GridLayout mMoveSelectionBoxes;
    CheckersGame board = new CheckersGame();
    private GridLayout mCheckerBoardImageButtons;
    public GamePiece[] mPieces;
    public BoardSpace[] mBoardSpaces;
    public boolean gameOver;
    public boolean playingBot = true;
    public playerTurn mPlayerTurn;
    private Menu mMenu;
    public boolean pieceToMoveSelected = false;

    private Button mChangeBoardButton;
    private ImageView mCheckerBoardImage;

    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View parentView = inflater.inflate(R.layout.fragment_game, container, false);
        mConfirmButton = parentView.findViewById(R.id.confirm_move_button);
        mMoveSelectionBoxes = parentView.findViewById(R.id.MoveInputs);
        mCheckerBoardImageButtons = parentView.findViewById(R.id.CheckerImageButtons);

        mChangeBoardButton = parentView.findViewById(R.id.changeBoardButton);
        mCheckerBoardImage = parentView.findViewById(R.id.board);

        setupGame();

        /*
         * sets click listener for all 24 game piece image buttons
         */
        for (int i = 0; i < 64; ++i)
        {
            mCheckerBoardImageButtons.getChildAt(i).setOnClickListener(this::onBoardSpaceClick);
        }


        mChangeBoardButton.setOnClickListener(this:: onChangeBoardClick);

        // Inflate the layout for this fragment
        return parentView;
    }

    private void onChangeBoardClick(View view) {

        Random random = new Random();
        int randomBoard = random.nextInt(4);

        Drawable standard = getResources().getDrawable(R.drawable.checkerboard);
        Drawable green = getResources().getDrawable(R.drawable.checkerboardgreen);
        Drawable ice = getResources().getDrawable(R.drawable.checkerboardice);
        Drawable ruby = getResources().getDrawable(R.drawable.checkerboardruby);

        switch(randomBoard) {

            case 0:
                mCheckerBoardImage.setImageDrawable(standard);
                break;

            case 1:
                mCheckerBoardImage.setImageDrawable(green);
                break;

            case 2:
                mCheckerBoardImage.setImageDrawable(ice);
                break;

            case 3:
                mCheckerBoardImage.setImageDrawable(ruby);
                break;
        }

    }


    /*
     * initial setup for the game
     */
    public void setupGame()
    {
        mGame = new CheckersGame();
        mPieces = new GamePiece[24];
        gameOver = false;

        mGame.newGame();
        mPieces = mGame.getPieces();
        mBoardSpaces = mGame.getBoardSpaces();
        mPlayerTurn = playerTurn.RED;

        updateBoardView();
    }





    /*
     * Action taken when button clicked
     */
    private void onBoardSpaceClick(View view) {

        int pieceX = 3;
        int pieceY = 3;
        int spaceX = 4;
        int spaceY = 4;

        int buttonIndex = 0;
        for (int i = 0; i < 64; ++i)
        {
            if (mCheckerBoardImageButtons.getChildAt(i) == (Button)view)
            {
                buttonIndex = i;
            }
        }

        if (pieceToMoveSelected == false)
        {
            pieceX = getXFromButtonIndex(buttonIndex);
            pieceY = getYFromButtonIndex(buttonIndex);
            pieceToMoveSelected = true;
        }
        else if (pieceToMoveSelected == true)
        {
            spaceX = getXFromButtonIndex(buttonIndex);
            spaceY = getYFromButtonIndex(buttonIndex);
            movePiece(pieceX, pieceY, spaceX, spaceY);
        }
    }





    public void movePiece(int pieceX, int pieceY, int spaceX, int spaceY)
    {
        if (mPlayerTurn == playerTurn.RED) {
            mPieces = mGame.move(mPlayerTurn, mPieces, getPieceWithPosition(pieceX, pieceY), getBoardSpaceWithPosition(spaceX, spaceY));
            mPlayerTurn = (mPlayerTurn == playerTurn.RED) ? playerTurn.BLACK : playerTurn.RED;
        }
        if (playingBot == false && mPlayerTurn == playerTurn.BLACK) {
            mPieces = mGame.move(mPlayerTurn, mPieces, getPieceWithPosition(pieceX, pieceY), getBoardSpaceWithPosition(spaceX, spaceY));
            mPlayerTurn = (mPlayerTurn == playerTurn.RED) ? playerTurn.BLACK : playerTurn.RED;
        }
        if (playingBot == true && mPlayerTurn == playerTurn.BLACK) {
            //rather than a human inputting a piece and board space, an automated process will input these arguments

            int[] botMove = new int[4];
            CheckBot cb = new CheckBot(mPieces, mBoardSpaces);
            botMove = cb.generateMove(mPieces, mBoardSpaces, mPlayerTurn);

            mPieces = mGame.move(mPlayerTurn, mPieces, getPieceWithPosition(botMove[0], botMove[1]), getBoardSpaceWithPosition(botMove[2], botMove[3]));
            mPlayerTurn = (mPlayerTurn == playerTurn.RED) ? playerTurn.BLACK : playerTurn.RED;
        }
        pieceToMoveSelected = false;

        updateBoardView();
    }




    /*
     * Updates the images of the image views to reflect the positions of the checker
     * pieces within the mPieces array
     */
    public void updateBoardView()
    {
        Button boardImage;
        Drawable red = getResources().getDrawable(R.drawable.redpiece);
        Drawable redKing = getResources().getDrawable(R.drawable.redpieceking);
        Drawable black = getResources().getDrawable(R.drawable.blackpiece);
        Drawable blackKing = getResources().getDrawable(R.drawable.blackpieceking);
        for (int i = 0; i < 64; ++i)
        {
            boardImage = (Button) mCheckerBoardImageButtons.getChildAt(i);
            boardImage.setBackground(null);
            for (int j = 0; j < 24; ++j)
            {
                if (getXFromButtonIndex(i) == mPieces[j].getX()
                        && getYFromButtonIndex(i) == mPieces[j].getY())
                {
                    if (!mPieces[j].crowned && mPieces[j].getColor() == pieceColor.RED)
                    {boardImage.setBackground(red);}
                    else if (mPieces[j].crowned && mPieces[j].getColor() == pieceColor.RED)
                    {boardImage.setBackground(redKing);}
                    else if (!mPieces[j].crowned && mPieces[j].getColor() == pieceColor.BLACK)
                    {boardImage.setBackground(black);}
                    else if (mPieces[j].crowned && mPieces[j].getColor() == pieceColor.BLACK)
                    {boardImage.setBackground(blackKing);}
                }
            }
        }
    }



    public boolean checkGameOverState()
    {
        int redRemaining = 0;
        int blackRemaining = 0;
        for (int i = 0; i < 24; ++i)
        {
            if (mPieces[i].getColor() == pieceColor.RED) {
                ++redRemaining;
            }
            else if (mPieces[i].getColor() == pieceColor.BLACK) {
                ++blackRemaining;
            }
        }

        if (redRemaining == 0 || blackRemaining == 0) {
            return true;
        }
        return false;
    }

    /*
     * returns a GamePiece within the mPieces array that corresponds with the
     * coordinates of the button pressed.
     * If the coordinates of the button pressed do not correspond with an actual piece within the array
     * then will return a null GamePiece which will tell the CheckersGame model class that
     * no real piece has been selected.
     */
    public GamePiece getPieceWithPosition(int x, int y)
    {
        GamePiece selectedPiece = null;

        for (int i = 0; i < 24; ++i)
        {
            if (mPieces[i].getX() == x && mPieces[i].getY() == y)
            {
                selectedPiece = mPieces[i];
            }
        }
        return selectedPiece;
    }

    /*
     * has similar function to the 'getPieceWithPosition' method, except for Board Spaces
     */
    public BoardSpace getBoardSpaceWithPosition(int x, int y)
    {
        BoardSpace selectedSpace = null;

        for (int i = 0; i < 64; ++i)
        {
            if (mBoardSpaces[i].getX() == x && mBoardSpaces[i].getY() == y)
            {
                selectedSpace = mBoardSpaces[i];
            }
        }
        return selectedSpace;
    }

    public int getXFromButtonIndex(int i) {
        return (i % 8) + 1;
    }

    public int getXFromButton(Button button) {
        for (int i = 0; i < 64; ++i)
        {
            if (mCheckerBoardImageButtons.getChildAt(i) == button)
            {
                return (i % 8) + 1;
            }
        }
        return 0;
    }

    public int getYFromButtonIndex(int i) {
        return (i / 8) + 1;
    }

    public int getYFromButton(Button button) {
        for (int i = 0; i < 64; ++i)
        {
            if (mCheckerBoardImageButtons.getChildAt(i) == button)
            {
                return (i / 8) + 1;
            }
        }
        return 0;
    }


    public int getButtonIndexFromPosition (int x, int y) {
        x-=1;
        y-=1;
        int index = ((y * 8) + x);
        return index;
    }


    public void setBoardImage(View view){



    }

}
