package com.example.johnta.homeworkappv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.johnta.homeworkappv2.popup.ButtonSoundPopup;
import com.example.johnta.homeworkappv2.popup.ChangeBackgroundColorPopup;
import com.example.johnta.homeworkappv2.popup.fullscreenpopups.CreditsPopup;

public class SettingsActivity extends AppCompatActivity {

    /**
     * Starts the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    /**
     * Starts the ButtonSoundPopup popup
     * @param v Current view
     */
    public void buttonSound (View v) {
        startActivity(new Intent(SettingsActivity.this, ButtonSoundPopup.class));
    }

    /**
     * Starts the CreditsPopup popup
     * @param v Current view
     */
    public void toCreditsScreen (View v) {
        startActivity(new Intent(SettingsActivity.this, CreditsPopup.class));
    }

    /**
     * Starts the BackgroundColor popup
     * @param v Current view
     */
    public void changeBackgroundColor (View v) {
        startActivity(new Intent(SettingsActivity.this, ChangeBackgroundColorPopup.class));
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
