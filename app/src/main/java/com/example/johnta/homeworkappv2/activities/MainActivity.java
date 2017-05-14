package com.example.johnta.homeworkappv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.backend.HelperWrapper;
import com.example.johnta.homeworkappv2.backend.PlaySound;
import com.example.johnta.homeworkappv2.firebase.FirebaseWrapper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private static boolean isUserSignedIn = false;

    PlaySound play;

    /**
     * Creates the main activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DatabaseReference mDatabase;
        FirebaseDatabase mFirebase;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //HelperWrapper.setBackgroundColorWindow(this);

        mFirebase = FirebaseDatabase.getInstance();
        mDatabase = mFirebase.getReference();

        play = new PlaySound(this);
    }

    /**
     * Overrides onResume and calls the HelperWrapper.setBackgroundColor method
     */
    @Override
    public void onResume() {
        super.onResume();
        HelperWrapper.setBackgroundColorWindow(this);

    }

    /**
     * Transition to PlannerActivity
     * @param v Current View
     */
    public void toPlannerScreen(View v) {
        play.playSound();
        startActivity(new Intent(this, PlannerActivity.class));
    }

    /**
     * Transition to CloudActivity
     * @param v Current View
     */
    public void toCloudScreen(View v) {
        play.playSound();
        if (FirebaseWrapper.getInstance(this).getGroup() != null)
            startActivity(new Intent(this, CloudActivity.class));
        else {
            startActivity(new Intent(this, FirstGroupActivity.class));
        }
    }

    /**
     * Transition to ScheduleActivity
     * @param v Current View
     */
    public void toScheduleScreen(View v) {
        play.playSound();
        startActivity(new Intent(this, ScheduleActivity.class));
    }

    /**
     * Transition SettingsActivity
     * @param view Current View
     */
    public void toSettingsScreen(View view) {
        play.playSound();
        startActivity(new Intent(this, SettingsActivity.class));
    }

}
