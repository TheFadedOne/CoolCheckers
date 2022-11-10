package com.zybooks.coolcheckers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.view.Menu;
import android.util.DisplayMetrics;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ConcurrentModificationException;

//This is ABranch
public class MainActivity extends AppCompatActivity {

    CheckersGameModel board = new CheckersGameModel();
    private CheckersGameModel mGame;
    private GridLayout mCheckerBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // GamePiece all_pieces[] = new GamePiece[24];

        /* for (int i = 0; i < 12; i++) {
            all_pieces[i] = board.black_pieces[i];
            all_pieces[i+12] = board.red_pieces[i];
        } */

        mGame = new CheckersGameModel();

        mCheckerBoard = findViewById(R.id.CheckerGameBoard);

        // Add the same click handler to all grid buttons
        for (int i = 0; i < mCheckerBoard.getChildCount(); i++) {
            Button gridButton = (Button) mCheckerBoard.getChildAt(i);
            gridButton.setOnClickListener(this::onBoardSpaceClick);

            if (savedInstanceState == null) {
                startGame();
            }
        }
    }

    private void onBoardSpaceClick(View view)
    {
        Toast.makeText(MainActivity.this, "Button click", Toast.LENGTH_LONG).show();
    }


    //starts the game
    private void startGame()
    {
        mGame.newGame();
    }



}