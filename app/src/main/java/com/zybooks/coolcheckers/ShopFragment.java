package com.zybooks.coolcheckers;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;

public class ShopFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_shop, container, false);
        SharedPreferences sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE);

        //added
        int boardColor = sharedPref.getInt("drawable", R.drawable.checkerboard);
        int board_radio = R.id.radio_brown;

        if(boardColor == R.drawable.checkerboardgreen){
            board_radio = R.id.radio_green;
        }
        else if(boardColor == R.drawable.checkerboardice){
            board_radio = R.id.radio_ice;
        }
        else if(boardColor == R.drawable.checkerboardruby){
            board_radio = R.id.radio_ruby;
        }

        RadioButton radio = rootView.findViewById(board_radio);
        radio.setChecked(true);

        RadioGroup shopRadioGroup = rootView.findViewById(R.id.shop_buttons);
        for (int i = 0; i < shopRadioGroup.getChildCount(); i++) {
            radio = (RadioButton) shopRadioGroup.getChildAt(i);
            radio.setOnClickListener(this::boardChange);
        }

        return rootView;
    }

    private void boardChange(View view) {
        int colorId = R.color.dark_square;

        //added
        int boardColor = R.drawable.checkerboard;

        if(view.getId() == R.id.radio_green){
            boardColor = R.drawable.checkerboardgreen;
        }
        else if(view.getId() == R.id.radio_ice){
            boardColor = R.drawable.checkerboardice;
        }
        else if(view.getId() == R.id.radio_ruby){
            boardColor = R.drawable.checkerboardruby;

        }

        SharedPreferences sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        //added
        editor.putInt("drawable", boardColor);
        editor.apply();

    }
}