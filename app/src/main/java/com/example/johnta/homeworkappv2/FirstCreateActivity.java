package com.example.johnta.homeworkappv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.johnta.homeworkappv2.firebase.FirebaseWrapper;
import com.example.johnta.homeworkappv2.firebase.handler.SignedInHandler;

public class FirstCreateActivity extends AppCompatActivity implements SignedInHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstcreate);

        RelativeLayout layout_main = (RelativeLayout) findViewById(R.id.relativeLayoutFirstCreate);
        layout_main.setVisibility(View.VISIBLE);
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


    @Override
    public void onSignInSuccess() {
        Log.v("LOGIN", "Login Success");
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
