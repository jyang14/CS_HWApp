package com.example.johnta.homeworkappv2.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.backend.HelperWrapper;
import com.example.johnta.homeworkappv2.firebase.FirebaseWrapper;
import com.example.johnta.homeworkappv2.popup.ButtonSoundPopup;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private static boolean isUserSignedIn = false;

    MediaPlayer mp;

    /**
     * Creates the main activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DatabaseReference mDatabase;
        FirebaseDatabase mFirebase;

        /*
        //NEEDS TO RETRIEVE STATE
       if (isUserSignedIn == false) {
           startActivity(new Intent(MainActivity.this, FirstCreateActivity.class));
       }
       */

        mp = MediaPlayer.create(this, R.raw.water_drop_sound);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //HelperWrapper.setBackgroundColorWindow(this);

        mFirebase = FirebaseDatabase.getInstance();
        mDatabase = mFirebase.getReference();
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
     *
     * @param v Current View
     */
    public void toPlannerScreen(View v) {
        playSound();
        startActivity(new Intent(this, PlannerActivity.class));
    }

    /**
     * Transition to CloudActivity
     *
     * @param v Current View
     */
    public void toCloudScreen(View v) {
        playSound();
        if (FirebaseWrapper.getInstance(this).getGroup() != null)
            startActivity(new Intent(this, CloudActivity.class));
        else {
            startActivity(new Intent(this, FirstGroupActivity.class));
        }
    }

    /**
     * Transition to ScheduleActivity
     *
     * @param v Current View
     */
    public void toScheduleScreen(View v) {
        playSound();
        startActivity(new Intent(this, ScheduleActivity.class));
    }

    /**
     * Transition SettingsActivity
     *
     * @param view Current View
     */
    public void toSettingsScreen(View view) {
        playSound();
        startActivity(new Intent(this, SettingsActivity.class));
    }

    /**
     * Play Button Sound
     */
    public void playSound() {
        if (ButtonSoundPopup.getTestCaseValue()) {
            mp.start();
        }
    }
}
