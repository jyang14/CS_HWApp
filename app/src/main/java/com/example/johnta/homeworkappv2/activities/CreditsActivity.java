package com.example.johnta.homeworkappv2.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.backend.PlaySound;

public class CreditsActivity extends AppCompatActivity {

    PlaySound play;

    /**
     * Start activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_credits);

        play = new PlaySound(this);
    }

    /**
     * Return to previous activity
     * @param view Current view
     */
    public void returnToPrevious (View view) {
        play.playSound();
        super.onBackPressed();
    }
}
