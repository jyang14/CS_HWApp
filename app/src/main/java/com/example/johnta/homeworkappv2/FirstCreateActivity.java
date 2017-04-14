package com.example.johnta.homeworkappv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FirstCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstcreate);
    }

    public void onSignInUser(View view) {

    }

    public void onCreateNewUser(View view) {
        startActivity(new Intent(FirstCreateActivity.this,NewUserPopup_popup.class));
    }

}
