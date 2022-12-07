package com.zybooks.coolcheckers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.metrics.Event;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;



public class GameFragment extends Fragment {

    private CheckersGame mGame;
    private Button mConfirmButton;
    private GridLayout mMoveSelectionBoxes;
    CheckersGame board = new CheckersGame();
    private GridLayout mCheckerBoard;
    private GridLayout mCheckerBoardImages;
    public GamePiece[] mPieces;
    public ImageView[] mPieceImages;
    public BoardSpace[] mBoardSpaces;
    public boolean gameOver;
    public boolean playingBot = true;
    public playerTurn mPlayerTurn;
    public boolean moveReady = false;
    private Menu mMenu;

    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View parentView = inflater.inflate(R.layout.fragment_game, container, false);
        mConfirmButton = parentView.findViewById(R.id.confirm_move_button);
        mMoveSelectionBoxes = parentView.findViewById(R.id.MoveInputs);
        mCheckerBoardImages = parentView.findViewById(R.id.CheckerGameBoardImages);

        setupGame();

        /*
         * click listener for the confirm move button
         */
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pieceX = -1;
                int pieceY = -1;
                int spaceX = -1;
                int spaceY = -1;

                EditText pX = mMoveSelectionBoxes.findViewById(R.id.pieceX);
                EditText pY = mMoveSelectionBoxes.findViewById(R.id.pieceY);
                EditText sX = mMoveSelectionBoxes.findViewById(R.id.spaceX);
                EditText sY = mMoveSelectionBoxes.findViewById(R.id.spaceY);

                pieceX = Integer.parseInt(pX.getText().toString());
                pieceY = Integer.parseInt(pY.getText().toString());
                spaceX = Integer.parseInt(sX.getText().toString());
                spaceY = Integer.parseInt(sY.getText().toString());


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


                updateBoardView();

                gameOver = (checkGameOverState() == true) ? true : false;

            }
        });


        // Inflate the layout for this fragment
        return parentView;
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
        //playGame();
    }

    /*
     * game loop. game does not end until a player does not have any remaining pieces.
     */
    public void playGame()
    {

        int pieceIndex;

        int pieceX = -1;
        int pieceY = -1;
        int spaceX = -1;
        int spaceY = -1;

        EditText pX = mMoveSelectionBoxes.findViewById(R.id.pieceX);
        EditText pY = mMoveSelectionBoxes.findViewById(R.id.pieceY);
        EditText sX = mMoveSelectionBoxes.findViewById(R.id.spaceX);
        EditText sY = mMoveSelectionBoxes.findViewById(R.id.spaceY);

        pieceX = Integer.parseInt(pX.getText().toString());
        pieceY = Integer.parseInt(pY.getText().toString());
        spaceX = Integer.parseInt(sX.getText().toString());
        spaceY = Integer.parseInt(sY.getText().toString());



        while (checkGameOverState())
        {
            if (playingBot == false && mPlayerTurn == playerTurn.RED) {
                mPieces = mGame.move(mPlayerTurn, mPieces, getPieceWithPosition(pieceX, pieceY), getBoardSpaceWithPosition(spaceX, spaceY));
            } else if (playingBot == false && mPlayerTurn == playerTurn.BLACK) {
                mPieces = mGame.move(mPlayerTurn, mPieces, getPieceWithPosition(pieceX, pieceY), getBoardSpaceWithPosition(spaceX, spaceY));
            } else if (playingBot == true && mPlayerTurn == playerTurn.BLACK) {
                //rather than a human inputting a piece and board space, an automated process will input these arguments

                int[] botMove = new int[4];
                CheckBot cb = new CheckBot(mPieces, mBoardSpaces);
                botMove = cb.generateMove(mPieces, mBoardSpaces, mPlayerTurn);

                mPieces = mGame.move(mPlayerTurn, mPieces, getPieceWithPosition(botMove[0], botMove[1]), getBoardSpaceWithPosition(botMove[2], botMove[3]));
            }

            mPlayerTurn = (mPlayerTurn == playerTurn.RED) ? playerTurn.BLACK : playerTurn.RED;
            updateBoardView();

            gameOver = (checkGameOverState() == true) ? true : false;
        }
    }

    /*
     * Updates the images of the image views to reflect the positions of the checker
     * pieces within the mPieces array
     */
    public void updateBoardView()
    {
        ImageView boardImage;
        Drawable red = getResources().getDrawable(R.drawable.redpiece);
        Drawable redKing = getResources().getDrawable(R.drawable.redpieceking);
        Drawable black = getResources().getDrawable(R.drawable.blackpiece);
        Drawable blackKing = getResources().getDrawable(R.drawable.blackpieceking);
        for (int i = 0; i < 64; ++i)
        {
            boardImage = (ImageView) mCheckerBoardImages.getChildAt(i);
            boardImage.setImageDrawable(null);
            for (int j = 0; j < 24; ++j)
            {
                if (getXFromButtonIndex(i) == mPieces[j].getX()
                        && getYFromButtonIndex(i) == mPieces[j].getY())
                {
                    if (!mPieces[j].crowned && mPieces[j].getColor() == pieceColor.RED)
                    {boardImage.setImageDrawable(red);}
                    else if (mPieces[j].crowned && mPieces[j].getColor() == pieceColor.RED)
                    {boardImage.setImageDrawable(redKing);}
                    else if (!mPieces[j].crowned && mPieces[j].getColor() == pieceColor.BLACK)
                    {boardImage.setImageDrawable(black);}
                    else if (mPieces[j].crowned && mPieces[j].getColor() == pieceColor.BLACK)
                    {boardImage.setImageDrawable(blackKing);}
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

    public int getYFromButtonIndex(int i) {
        return (i / 8) + 1;
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