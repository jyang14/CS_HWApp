package com.example.johnta.homeworkappv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toPlannerScreen(View v) {
        startActivity(new Intent(MainActivity.this,PlannerActivity.class));
    }

    public void toCloudScreen(View v) {
        startActivity(new Intent(MainActivity.this, CloudActivity.class));
    }



}
