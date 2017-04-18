package com.example.johnta.homeworkappv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginPopup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_login);
    }

    public void onClickTransitionToMain(View view) {
        startActivity(new Intent(LoginPopup.this, MainActivity.class));
        finish();
    }

    public void goBack(View view) {
        startActivity(new Intent(LoginPopup.this, FirstCreateActivity.class));
        finish();
    }
}
