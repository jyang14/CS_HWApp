package com.example.johnta.homeworkappv2.popup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.activities.CloudActivity;
import com.example.johnta.homeworkappv2.backend.PlaySound;
import com.example.johnta.homeworkappv2.firebase.FirebaseWrapper;
import com.example.johnta.homeworkappv2.firebase.handler.GroupJoinedHandler;

public class CreateAGroup extends AppCompatActivity implements GroupJoinedHandler {

    PlaySound play;

    /**
     * Starts activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        play.playSound();
    }

    /**
     * Create group
     * @param view Current view
     */
    public void onCreateGroup(View view) {
        play.playSound();
        FirebaseWrapper.getInstance(this).createGroup(((EditText) findViewById(R.id.editText7)).getText().toString(), this);
    }

    /**
     * Tester to see if user has joined a group
     * @param success boolean for success
     */
    @Override
    public void handleGroupJoined(boolean success) {
        if (success) {
            startActivity(new Intent(this, CloudActivity.class));
            finish();
        } else
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Invalid UUID")
                    .setNeutralButton("OK", null).create().show();
    }

    /**
     * Return to previous activity
     * @param view Current view
     */
    public void onClickCancel(View view) {
        play.playSound();
        finish();
        super.onBackPressed();
    }
}
