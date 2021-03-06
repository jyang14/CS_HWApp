package com.example.johnta.homeworkappv2.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.backend.HelperWrapper;

public class PersonalScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_schedule);
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
