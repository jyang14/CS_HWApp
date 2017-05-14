package com.example.johnta.homeworkappv2.popup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.backend.BackgroundColorSelector;
import com.example.johnta.homeworkappv2.backend.HelperWrapper;

public class ChangeBackgroundColorPopup extends AppCompatActivity {

    private static String lightYellowHexCode = "#FFFFE0";
    private static String lightGrayHexCode = "#D3D3D3";
    private static String skyBlueHexCode = "#87CEEB";
    private static String whiteHexCode = "#ffffff";
    private static String blueHexCode = "#107FC9";

    /**
     * Initilizes the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_change_background_color);

        /*
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));
        */
    }

    /**
     * On click of yellow button, call static method setStringColor
     * @param view Current View
     */
    public void changeBackgroundToLightYellow(View view) {
        Log.i("he", "help");

        BackgroundColorSelector.setStringColor(lightYellowHexCode);
    }

    /**
     * On click of light gray button, call static method setStringColor
     * @param view Current view
     */
    public void changeBackgroundToLightGray(View view) {
        BackgroundColorSelector.setStringColor(lightGrayHexCode);
    }

    /**
     * On click of sky blue button, call static method setStringColor
     * @param view Current view
     */
    public void changeBackgroundToSkyBlue(View view) {
        BackgroundColorSelector.setStringColor(skyBlueHexCode);
    }

    /**
     * On click of white button, call static method setStringColor
     * @param view Current view
     */
    public void changeBackgroundToWhite(View view) {
        BackgroundColorSelector.setStringColor(whiteHexCode);
    }

    /**
     * On click of blue button, call static method setStringColor
     * @param view Current view
     */
    public void changeBackgroundToBlue(View view) {
        BackgroundColorSelector.setStringColor(blueHexCode);
    }

    /**
     * Overrides onResume and calls the HelperWrapper.setBackgroundColor method
     */
    @Override
    public void onResume() {
        super.onResume();
        HelperWrapper.setBackgroundColorWindow(this);
    }


    public void goToCancel(View view) {
        finish();
        super.onBackPressed();
    }


}
