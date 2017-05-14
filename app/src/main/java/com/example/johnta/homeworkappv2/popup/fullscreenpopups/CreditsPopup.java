package com.example.johnta.homeworkappv2.popup.fullscreenpopups;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.johnta.homeworkappv2.R;

public class CreditsPopup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_credits);
    }

    public void returnToPrevious (View view) {
        super.onBackPressed();
    }
}
