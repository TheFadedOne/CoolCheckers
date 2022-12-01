package com.zybooks.coolcheckers;

import android.app.Activity;
import android.os.Bundle;

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

    private static CheckersGame mGame;
    CheckersGame board = new CheckersGame();
    private GridLayout mCheckerBoard;
    public static GamePiece[] mPieces;
    public ImageView[] mPieceImages;
    public static BoardSpace[] mBoardSpaces;
    public static boolean gameOver;
    public static playerTurn mPlayerTurn;

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


        startGame();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    //Click listener for all the buttons
    private void onBoardSpaceClick(View view) {


    }


    /*
     * initial setup for the game
     */
    public static void startGame()
    {
        mGame = new CheckersGame();
        mPieces = new GamePiece[24];
        gameOver = false;

        mGame.newGame();
        mPieces = mGame.getPieces();
        mBoardSpaces = mGame.getBoardSpaces();
        mPlayerTurn = playerTurn.RED;

        playGame();
    }

    /*
     * game loop. game does not end until a player does not have any remaining pieces.
     */
    public static void playGame()
    {
        Scanner scan = new Scanner(System.in);
        int pieceX = 3;
        int pieceY = 3;
        int spaceX = 4;
        int spaceY = 4;
        GamePiece[] temp = new GamePiece[24];
        while (gameOver == false)
        {


            mPieces = mGame.move(mPlayerTurn, mPieces, getPieceWithPosition(pieceX, pieceY), getBoardSpaceWithPosition(spaceX, spaceY));
            mPlayerTurn = (mPlayerTurn == playerTurn.RED) ? playerTurn.BLACK : playerTurn.RED;

            gameOver = true;
        }
    }


    /*
     * Updates the positions of the image views to reflect the positions of the checker
     * pieces within the array
     */
    public void updateBoardView(GamePiece[] pieces)
    {

    }

    /*
     * returns a GamePiece within the mPieces array that corresponds with the
     * coordinates of the button pressed.
     * If the coordinates of the button pressed do not correspond with an actual piece within the array
     * then will return a null GamePiece which will tell the CheckersGame model class that
     * no real piece has been selected.
     */
    public static GamePiece getPieceWithPosition(int x, int y)
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
    public static BoardSpace getBoardSpaceWithPosition(int x, int y)
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

    /*
     * prints all of the pieces
     */
    public static void printPieces()
    {
        for (int i = 0; i < 24; ++i)
        {
            System.out.print("Piece #" + i + " === ");
            mPieces[i].printPiece();

        }
        System.out.println();

    }
}