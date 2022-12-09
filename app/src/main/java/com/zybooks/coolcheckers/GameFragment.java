package com.zybooks.coolcheckers;

import android.graphics.Color;
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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class GameFragment extends Fragment {

    private CheckersGame mGame;
    public GamePiece[] mPieces;
    public BoardSpace[] mBoardSpaces;
    public boolean gameOver;
    public boolean playingBot = false;
    public playerTurn mPlayerTurn;
    public int tempX = 0;
    public int tempY = 0;
    public boolean pieceToMoveSelected = false;

    private Button mChangeBoardButton;
    private ImageView mCheckerBoardImage;
    private GridLayout mCheckerBoardImageButtons;
    private Switch mBotToggleSwitch;
    private TextView mTurnText;

    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View parentView = inflater.inflate(R.layout.fragment_game, container, false);
        mCheckerBoardImageButtons = parentView.findViewById(R.id.CheckerImageButtons);
        mChangeBoardButton = parentView.findViewById(R.id.changeBoardButton);
        mCheckerBoardImage = parentView.findViewById(R.id.board);
        mBotToggleSwitch = parentView.findViewById(R.id.botToggleSwitch);
        mTurnText = parentView.findViewById(R.id.turnDisplayText);


        setupGame();

        /*
         * sets click listener for all 24 game piece image buttons
         */
        for (int i = 0; i < 64; ++i)
        {
            mCheckerBoardImageButtons.getChildAt(i).setOnClickListener(this::onBoardSpaceClick);
        }

        mChangeBoardButton.setOnClickListener(this:: onChangeBoardClick);

        //click listener for bot toggle button
        mBotToggleSwitch.setOnClickListener(this::onBotToggleClick);



        // Inflate the layout for this fragment
        return parentView;
    }

    private void onBotToggleClick(View view) {

        boolean on = ((Switch)view).isChecked();
        if (on)
        {
            playingBot = true;
        }
        else
        {
            playingBot = false;
        }
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

        int pieceX;
        int pieceY;
        int spaceX;
        int spaceY;

        int buttonIndex = 0;
        for (int i = 0; i < 64; ++i)
        {
            if (mCheckerBoardImageButtons.getChildAt(i) == (Button)view)
            {
                buttonIndex = i;
            }
        }

        //if piece has not been selected yet
        if (pieceToMoveSelected == false)
        {
            tempX = getXFromButtonIndex(buttonIndex);
            tempY = getYFromButtonIndex(buttonIndex);
            pieceToMoveSelected = true;
        }
        //if piece has been selected then move
        else if (pieceToMoveSelected == true)
        {
            pieceX = tempX;
            pieceY = tempY;
            spaceX = getXFromButtonIndex(buttonIndex);
            spaceY = getYFromButtonIndex(buttonIndex);

            movePiece(pieceX, pieceY, spaceX, spaceY);
            gameOver = checkGameOverState();
            pieceToMoveSelected = false;
            updateBoardView();


            //if playing bot, allow bot to move after red player has moved
            if (playingBot == true && gameOver == false)
            {
                movePiece(0, 0, 0, 0);
                gameOver = checkGameOverState();
                updateBoardView();
            }
        }
    }





    public void movePiece(int pieceX, int pieceY, int spaceX, int spaceY)
    {
        if (mPlayerTurn == playerTurn.RED) {
            mPieces = mGame.move(mPlayerTurn, mPieces, getPieceWithPosition(pieceX, pieceY), getBoardSpaceWithPosition(spaceX, spaceY));
            mPlayerTurn = (mPlayerTurn == playerTurn.RED) ? playerTurn.BLACK : playerTurn.RED;
            mTurnText.setText("Turn: BLACK");
            mTurnText.setTextColor(Color.BLACK);
        }
        else if (mPlayerTurn == playerTurn.BLACK)
        {
            if (playingBot == false)
            {
                mPieces = mGame.move(mPlayerTurn, mPieces, getPieceWithPosition(pieceX, pieceY), getBoardSpaceWithPosition(spaceX, spaceY));
                mPlayerTurn = (mPlayerTurn == playerTurn.RED) ? playerTurn.BLACK : playerTurn.RED;
                mTurnText.setText("Turn: RED");
                mTurnText.setTextColor(Color.RED);
            }
            else if (playingBot == true)
            {
                //rather than a human inputting a piece and board space, an automated process will input these arguments

                int[] botMove = new int[4];
                CheckBot cb = new CheckBot(mPieces, mBoardSpaces);
                botMove = cb.generateMove(mPieces, mBoardSpaces, mPlayerTurn);

                mPieces = mGame.move(mPlayerTurn, mPieces, getPieceWithPosition(botMove[0], botMove[1]), getBoardSpaceWithPosition(botMove[2], botMove[3]));
                mPlayerTurn = (mPlayerTurn == playerTurn.RED) ? playerTurn.BLACK : playerTurn.RED;
                mTurnText.setText("Turn: RED");
                mTurnText.setTextColor(Color.RED);
            }
        }


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

        if (blackRemaining < 1)
        {
            Toast.makeText(getContext(), "RED WINS!!!", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (redRemaining < 1)
        {
            Toast.makeText(getContext(), "BLACK WINS!!!", Toast.LENGTH_SHORT).show();
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
