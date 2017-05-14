package com.example.johnta.homeworkappv2.popup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.backend.PlaySound;

public class GroupHelpPopup extends AppCompatActivity {

    PlaySound play;

    /** Creates the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_group_help);
        play = new PlaySound(this);
    }

    /** Return to previous view
     * @param view current view
     */
    public void onClickReturn (View view) {
        play.playSound();
        finish();
        super.onBackPressed();
    }
}
