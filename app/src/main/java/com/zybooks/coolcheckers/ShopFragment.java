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
        int colorId = sharedPref.getInt("color", R.color.dark_square);

        int radioId = R.id.radio_brown;

        if (colorId == R.color.dirty_green) {
            radioId = R.id.radio_dirty_green;
        }
        else if (colorId == R.color.gray) {
            radioId = R.id.radio_gray;
        }
        else if (colorId == R.color.blue) {
            radioId = R.id.radio_blue;
        }

        RadioButton radio = rootView.findViewById(radioId);
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

        if (view.getId() == R.id.radio_dirty_green) {

            colorId = R.color.dirty_green;

        } else if (view.getId() == R.id.radio_gray) {

            colorId = R.color.gray;

        } else if (view.getId() == R.id.radio_blue) {

            colorId = R.color.blue;
        }

        SharedPreferences sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("color", colorId);
        editor.apply();
    }
}