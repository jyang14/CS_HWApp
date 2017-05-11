package com.example.johnta.homeworkappv2.popup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.johnta.homeworkappv2.FirstCreateActivity;
import com.example.johnta.homeworkappv2.MainActivity;
import com.example.johnta.homeworkappv2.R;

public class LoginPopup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_login);
    }

    public void onClickTransitionToMain(View view) {
        startActivity(new Intent(LoginPopup.this, MainActivity.class));

    }

    public void goBack(View view) {
        startActivity(new Intent(LoginPopup.this, FirstCreateActivity.class));

    }
}
