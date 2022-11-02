package com.zybooks.coolcheckers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CheckersGameModel mGame;
    private GridLayout mCheckerBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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