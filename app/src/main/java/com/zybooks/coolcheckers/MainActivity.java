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

public class MainActivity extends AppCompatActivity {

    int switcher = 1;
    String click_move = "empty";
    GridView gridView;
    CheckersGameModel board = new CheckersGameModel();
    private CheckersGameModel mGame;
    private GridLayout mCheckerBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int h = metrics.heightPixels;
        int w = metrics.widthPixels;
        System.out.println(h);
        System.out.println(w);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GamePieces all_pieces[] = new GamePieces[24];
        for (int i = 0; i < 12; i++) {
            all_pieces[i] = board.black_pieces[i];
            all_pieces[i+12] = board.red_pieces[i];
        }
        String myvec[] = new String[64];
        myvec = board.vec_string();
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(this, myvec));
        Context context = getApplicationContext();
        CharSequence text = "Red Player Starts!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

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