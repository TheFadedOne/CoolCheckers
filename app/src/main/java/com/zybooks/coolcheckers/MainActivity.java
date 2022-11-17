package com.zybooks.coolcheckers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.util.DisplayMetrics;
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
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);


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


    //starts the game
    private void startGame()
    {
        mGame = new CheckersGame();
        mPieces = new GamePiece[24];
        gameOver = false;

        mGame.newGame();
        mPieces = mGame.getPieces();
        mBoardSpaces = mGame.getBoardSpaces();
        mPlayerTurn = playerTurn.BLACK;

        mPieces[6].printPiece();
        playGame();
    }

    /*
     * game loop. game does not end until a player does not have any remaining pieces.
     */
    public static void playGame()
    {
        while (gameOver == false)
        {
            mPieces = mGame.move(mPlayerTurn, mPieces, mPieces[7], mBoardSpaces[8]);
        }
    }

}