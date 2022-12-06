package com.zybooks.coolcheckers;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Scanner;



public class GameFragment extends Fragment {

    private CheckersGame mGame;
    CheckersGame board = new CheckersGame();
    private GridLayout mCheckerBoard;
    private GridLayout mCheckerBoardImages;
    public GamePiece[] mPieces;
    public ImageView[] mPieceImages;
    public BoardSpace[] mBoardSpaces;
    public boolean gameOver;
    public boolean playingBot = false;
    public playerTurn mPlayerTurn;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View parentView = inflater.inflate(R.layout.fragment_game, container, false);

        //Gives every button the same click listener
        mCheckerBoard = parentView.findViewById(R.id.CheckerGameBoard);
        for (int i = 0; i < mCheckerBoard.getChildCount(); i++) {
            Button spaceButton = (Button) mCheckerBoard.getChildAt(i);
            spaceButton.setOnClickListener(this::onBoardSpaceClick);
        }




        mCheckerBoardImages = parentView.findViewById(R.id.CheckerGameBoardImages);
        ImageView piece = (ImageView) mCheckerBoardImages.getChildAt(0);
        Drawable myIcon = getResources().getDrawable(R.drawable.redpiece);
        piece.setImageDrawable(myIcon);

        startGame();

        // Inflate the layout for this fragment
        return parentView;
    }

    //Click listener for all the buttons
    private void onBoardSpaceClick(View view)
    {

    }


    /*
     * initial setup for the game
     */
    public void startGame()
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
        Scanner scan = new Scanner(System.in);
        int pieceIndex;
        int pieceX = 3;
        int pieceY = 3;
        int spaceX = 4;
        int spaceY = 4;
        
        while (gameOver == false)
        {
            if (mPlayerTurn == playerTurn.RED)
            {

            }
            else if (playingBot == false && mPlayerTurn == playerTurn.BLACK)
            {

            }

            else if (playingBot == true && mPlayerTurn == playerTurn.BLACK)
            {

            }

            mPlayerTurn = (mPlayerTurn == playerTurn.RED) ? playerTurn.BLACK : playerTurn.RED;

            updateBoardView();

            gameOver = true;
        }

        View.OnClickListener pieceButton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = (Integer) view.getId();
                int pieceX = id / 10;
                int pieceY = id % 10;

            }
        };
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
}