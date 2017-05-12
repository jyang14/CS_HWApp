package com.example.johnta.homeworkappv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.johnta.homeworkappv2.popup.ButtonSoundPopup;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void buttonSound (View v) {
        startActivity(new Intent(SettingsActivity.this, ButtonSoundPopup.class));
    }

    public void toCreditsScreen (View v) {

    }

    public void changeBackgroundColor (View v) {

    }
}
