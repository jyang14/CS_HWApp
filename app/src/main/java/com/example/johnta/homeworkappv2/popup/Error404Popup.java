package com.example.johnta.homeworkappv2.popup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.backend.HelperWrapper;

public class Error404Popup extends Activity {

    /**
     * Starts activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_error404);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.6), (int) (height * 0.3));
    }

    /**
     * Return to previous activity
     * @param view Current view
     */
    public void onClickReturn(View view) {
        super.onBackPressed();
    }

    /**
     * Go to create a new URL view
     * @param view Current view
     */
    public void newURL(View view) {
        startActivity(new Intent(Error404Popup.this, CreateScheduleURLPopup.class));
        super.onBackPressed();
    }
    /**
     * Overrides onResume and calls the HelperWrapper.setBackgroundColor method
     */
    @Override
    public void onResume() {
        super.onResume();
        HelperWrapper.setBackgroundColorWindow(this);
    }
}
