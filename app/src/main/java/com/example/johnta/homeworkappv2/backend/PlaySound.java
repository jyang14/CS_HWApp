package com.example.johnta.homeworkappv2.backend;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.popup.ButtonSoundPopup;

/**
 * Created by johnta on 5/14/17.
 */

public class PlaySound {

    MediaPlayer mp;

    /**
     * Constructor that takes the current activity to instantiate an object
     * @param context The current context
     */
    public PlaySound (Context context) {
        mp = MediaPlayer.create(context, R.raw.water_drop_sound);
    }

    /**
     * Method that plays the sound. Calls static method getTestCaseValue() in order to determine whether or not
     * to play the sound
     */
    public void playSound() {
        if (ButtonSoundPopup.getTestCaseValue()) {
            mp.start();
        }
    }
}
