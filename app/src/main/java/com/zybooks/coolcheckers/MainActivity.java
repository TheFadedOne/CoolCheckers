package com.zybooks.coolcheckers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

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
        while (gameOver == false)
        {
            printPieces();

            mPieces = mGame.move(mPlayerTurn, mPieces, getPieceWithPosition(3, 3), getBoardSpaceWithPosition(4, 4));
            mPieces = mGame.move(playerTurn.BLACK, mPieces, getPieceWithPosition(6, 6), getBoardSpaceWithPosition(5, 5));
            mPieces = mGame.move(mPlayerTurn, mPieces, getPieceWithPosition(4, 4), getBoardSpaceWithPosition(6, 6));

            printPieces();

            gameOver = true;
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