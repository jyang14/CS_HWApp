package com.example.johnta.homeworkappv2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.johnta.homeworkappv2.popup.ButtonSoundPopup;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private static boolean isUserSignedIn = false;

    MediaPlayer mp;

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

        mFirebase = FirebaseDatabase.getInstance();
        mDatabase = mFirebase.getReference();
    }

    public void toPlannerScreen(View v) {
        playSound();
        startActivity(new Intent(MainActivity.this, PlannerActivity.class));
    }

    public void toCloudScreen(View v) {
        playSound();
        startActivity(new Intent(MainActivity.this, CloudActivity.class));
    }

    public void toScheduleScreen(View v) {
        playSound();
        startActivity(new Intent(MainActivity.this, ScheduleActivity.class));
    }

    public void toSettingsScreen(View view) {
        playSound();
        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
    }

    public void playSound () {

        if (ButtonSoundPopup.getTestCaseValue()) {
            mp.start();
        }
    }
}
