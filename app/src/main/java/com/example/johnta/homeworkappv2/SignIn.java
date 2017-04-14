package com.example.johnta.homeworkappv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void onSignInUser(View view) {

    }

    public void onCreateNewUser(View view) {
        startActivity(new Intent(SignIn.this,login_popup.class));
    }

}
