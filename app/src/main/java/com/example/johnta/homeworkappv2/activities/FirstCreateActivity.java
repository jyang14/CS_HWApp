package com.example.johnta.homeworkappv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.johnta.homeworkappv2.backend.HelperWrapper;
import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.firebase.FirebaseWrapper;
import com.example.johnta.homeworkappv2.firebase.handler.SignedInHandler;

public class FirstCreateActivity extends AppCompatActivity implements SignedInHandler {

    /**
     * Starts the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstcreate);

        FirebaseWrapper.getInstance(this).signIn();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FirebaseWrapper.getInstance(this).signInOnIntentResult(requestCode, data, this);

    }
    public void onSignInUser(View view) {
        //layout_main.setVisibility(View.GONE);
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
