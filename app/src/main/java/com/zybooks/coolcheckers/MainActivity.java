package com.zybooks.coolcheckers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private static CheckersGame mGame;
    int switcher = 1;
    String click_move = "empty";
    GridView gridView;
    CheckersGame board = new CheckersGame();
    public GamePiece[] copyGamePieces;
    private GridLayout mCheckerBoard;
    public static GamePiece[] mPieces;
    public static BoardSpace[] mBoardSpaces;
    public static boolean gameOver;
    public static playerTurn mPlayerTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mGame = new CheckersGame();

        startGame();

        mCheckerBoard = findViewById(R.id.CheckerGameBoard);


    }

    private void onBoardSpaceClick(View view)
    {
        Toast.makeText(MainActivity.this, "Button click", Toast.LENGTH_LONG).show();
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
        int pieceX;
        int pieceY;
        int spaceX;
        int spaceY;
        GamePiece[] temp = new GamePiece[24];
        while (gameOver == false)
        {
            printBoard();

            System.out.println("player turn: " + mPlayerTurn);
            System.out.println("enter piece x: ");
            pieceX = scan.nextInt();
            System.out.println("enter piece y: ");
            pieceY = scan.nextInt();
            System.out.println("enter space y: ");
            spaceX = scan.nextInt();
            System.out.println("enter space y: ");
            spaceY = scan.nextInt();


            temp = mGame.move(mPlayerTurn, mPieces, getPieceWithPosition(pieceX, pieceY), getBoardSpaceWithPosition(spaceX, spaceY));

            //checks to see if any new moves were made with the given inputs.
            //If so then changes the players turn to next player
            if (temp == mGame.move(playerTurn.BLACK, mPieces, getPieceWithPosition(pieceX, pieceY), getBoardSpaceWithPosition(spaceX, spaceY)))
            {
                mPieces = temp;
                mPlayerTurn = (mPlayerTurn == playerTurn.RED) ? playerTurn.BLACK : playerTurn.RED;
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