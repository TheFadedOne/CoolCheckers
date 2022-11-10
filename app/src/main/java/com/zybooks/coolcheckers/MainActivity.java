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
        //DisplayMetrics metrics = new DisplayMetrics();
        //getWindowManager().getDefaultDisplay().getMetrics(metrics);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        int h = metrics.heightPixels;
        int w = metrics.widthPixels;
        System.out.println(h);
        System.out.println(w);
        GamePiece all_pieces[] = new GamePiece[24];

        for (int i = 0; i < 12; i++) {
            all_pieces[i] = board.black_pieces[i];
            all_pieces[i+12] = board.red_pieces[i];
        }
        */

        mGame = new CheckersGameModel();

        mCheckerBoard = findViewById(R.id.CheckerGameBoard);

        // Add the same click handler to all grid buttons
        for (int i = 0; i < mCheckerBoard.getChildCount(); i++) {
            Button gridButton = (Button) mCheckerBoard.getChildAt(i);
            gridButton.setOnClickListener(this::onBoardSpaceClick);

            if (savedInstanceState == null) {
                setupGame();
                playGame();
            }
        }
    }

    private void onBoardSpaceClick(View view)
    {
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


    //starts the game
    private void setupGame()
    {
        mGame.newGame();

    }


    public void playGame()
    {
      //  while(mGame.checkGameOver() == false)
        {

        }
    }


}