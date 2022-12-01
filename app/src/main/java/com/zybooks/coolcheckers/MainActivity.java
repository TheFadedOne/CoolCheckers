package com.zybooks.coolcheckers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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


        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();

            AppBarConfiguration appBarConfig = new AppBarConfiguration.Builder(
                    R.id.navigation_game, R.id.navigation_shop, R.id.navigation_rules)
                    .build();

            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);
            NavigationUI.setupWithNavController(navView, navController);
        }

    }


}