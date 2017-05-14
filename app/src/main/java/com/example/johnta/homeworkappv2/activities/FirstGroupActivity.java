package com.example.johnta.homeworkappv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.backend.PlaySound;
import com.example.johnta.homeworkappv2.popup.CreateAGroup;
import com.example.johnta.homeworkappv2.popup.GroupHelpPopup;
import com.example.johnta.homeworkappv2.popup.JoinAGroup;

public class FirstGroupActivity extends AppCompatActivity {

    PlaySound play;

    /**
     * Starts activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_first_group);

        play = new PlaySound(this);
    }

    /**
     * Called when user clicks create group
     * @param view Current view
     */
    public void onCreateGroup(View view) {
        play.playSound();

        startActivity(new Intent(this, CreateAGroup.class));
        finish();
    }

    /**
     * Called when user clicks join group
     * @param view Current view
     */
    public void onJoinGroup(View view) {
        play.playSound();

        startActivity(new Intent(this, JoinAGroup.class));
        finish();
    }

    /**
     * Called when user clicks what is a group
     * @param view Current view
     */
    public void toQuestionMark (View view) {
        play.playSound();

        startActivity(new Intent(this, GroupHelpPopup.class));
        finish();
    }
}
