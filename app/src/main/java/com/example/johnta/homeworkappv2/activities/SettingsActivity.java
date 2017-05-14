package com.example.johnta.homeworkappv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.johnta.homeworkappv2.backend.HelperWrapper;
import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.backend.PlaySound;
import com.example.johnta.homeworkappv2.popup.ButtonSoundPopup;
import com.example.johnta.homeworkappv2.popup.ChangeBackgroundColorPopup;

public class SettingsActivity extends AppCompatActivity {

    PlaySound play;

    /**
     * Starts the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        play = new PlaySound(this);
    }

    /**
     * Starts the ButtonSoundPopup popup
     * @param v Current view
     */
    public void buttonSound (View v) {
        play.playSound();
        startActivity(new Intent(SettingsActivity.this, ButtonSoundPopup.class));
    }

    /**
     * Starts the CreditsActivity popup
     * @param v Current view
     */
    public void toCreditsScreen (View v) {
        play.playSound();
        startActivity(new Intent(SettingsActivity.this, CreditsActivity.class));
    }

    /**
     * Starts the BackgroundColor popup
     * @param v Current view
     */
    public void changeBackgroundColor (View v) {
        play.playSound();
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
