package com.example.johnta.homeworkappv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.backend.HelperWrapper;
import com.example.johnta.homeworkappv2.backend.PlaySound;
import com.example.johnta.homeworkappv2.firebase.FirebaseWrapper;
import com.example.johnta.homeworkappv2.firebase.handler.SignedInHandler;

public class FirstCreateActivity extends AppCompatActivity implements SignedInHandler, View.OnClickListener {

    PlaySound play;

    /**
     * Starts the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstcreate);

        //RelativeLayout layout_main = (RelativeLayout) findViewById(R.id.relativeLayoutFirstCreate);
        //layout_main.setVisibility(View.VISIBLE);

        findViewById(R.id.sign_in_button).setOnClickListener(this);

        play = new PlaySound(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FirebaseWrapper.getInstance(this).signInOnIntentResult(requestCode, data, this);
    }

    /**
     * Tests whether the user is signed in or not
     * @param v Current view
     */
    @Override
    public void onClick(View v) {
        play.playSound();
        FirebaseWrapper.getInstance(this).signIn();
    }


    /**
     * If user is signed in, then kill this activity and start the main activity
     */
    @Override
    public void onSignInSuccess() {
        Log.v("LOGIN", "Login Success");
        startActivity(new Intent(this, MainActivity.class));
        finish();
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
