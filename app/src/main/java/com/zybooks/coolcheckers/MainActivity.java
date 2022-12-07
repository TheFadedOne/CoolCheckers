package com.zybooks.coolcheckers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    public Menu mMenu;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.board_menu, menu);
        mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        View gameBoard = findViewById(R.id.board);
        Drawable standard = getResources().getDrawable(R.drawable.checkerboard);
        Drawable green = getResources().getDrawable(R.drawable.checkerboardgreen);
        Drawable ice = getResources().getDrawable(R.drawable.checkerboardice);
        Drawable ruby = getResources().getDrawable(R.drawable.checkerboardruby);


        // Determine which menu option was chosen
        if (item.getItemId() == R.id.board_stardard) {

            return true;
        }
        else if (item.getItemId() == R.id.board_green) {
            gameBoard.setBackground(green);
            return true;
        }
        else if (item.getItemId() == R.id.board_ice) {
            gameBoard.setBackground(ice);
            return true;
        }
        else if (item.getItemId() == R.id.board_ruby) {
            gameBoard.setBackground(ruby);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}