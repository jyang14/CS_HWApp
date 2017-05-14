package com.example.johnta.homeworkappv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.backend.HelperWrapper;

public class NewUserPopupActivity extends AppCompatActivity {

    /**
     * Starts new activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_new_user);
    }

    /**
     * Go to MainActivity
     * @param view Current View
     */
    public void onClickTransitionToMain(View view) {
        startActivity(new Intent(NewUserPopupActivity.this, MainActivity.class));

    }

    /**
     * Return to FirstCreateActivity
     * @param view
     */
    public void onClickGoBackToPrimary(View view) {
        startActivity(new Intent(NewUserPopupActivity.this, FirstCreateActivity.class));

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
