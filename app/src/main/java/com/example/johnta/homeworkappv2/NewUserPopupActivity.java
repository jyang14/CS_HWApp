package com.example.johnta.homeworkappv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class NewUserPopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_new_user);
    }

    public void onClickTransitionToMain(View view) {
        startActivity(new Intent(NewUserPopupActivity.this, MainActivity.class));

    }

    public void onClickGoBackToPrimary(View view) {
        startActivity(new Intent(NewUserPopupActivity.this, FirstCreateActivity.class));

    }
}
