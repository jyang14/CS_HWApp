package com.example.johnta.homeworkappv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.popup.CreateAGroup;
import com.example.johnta.homeworkappv2.popup.JoinAGroup;

public class FirstGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_first_group);
    }

    public void onCreateGroup(View view) {
        startActivity(new Intent(this, CreateAGroup.class));
        finish();
    }

    public void onJoinGroup(View view) {
        startActivity(new Intent(this, JoinAGroup.class));
        finish();
    }
}
