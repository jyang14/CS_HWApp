package com.example.johnta.homeworkappv2.popup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.johnta.homeworkappv2.activities.FirstCreateActivity;
import com.example.johnta.homeworkappv2.activities.MainActivity;
import com.example.johnta.homeworkappv2.R;

public class LoginPopup extends AppCompatActivity {

    /**
     * Starts activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_login);
    }

    /**
     * Go to main activity
     * @param view Current activity
     */
    public void onClickTransitionToMain(View view) {
        startActivity(new Intent(LoginPopup.this, MainActivity.class));

    }

    /**
     * Return to previous activity
     * @param view Current activity
     */
    public void goBack(View view) {
        startActivity(new Intent(LoginPopup.this, FirstCreateActivity.class));

    }
}
