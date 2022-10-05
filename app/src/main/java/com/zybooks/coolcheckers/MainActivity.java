package com.zybooks.coolcheckers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public CheckersGameModel mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGame = new CheckersGameModel();

        if (savedInstanceState == null)
        {
            mGame.startGame();
        }
    }
}