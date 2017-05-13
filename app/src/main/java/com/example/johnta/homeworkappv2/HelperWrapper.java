package com.example.johnta.homeworkappv2;

import android.app.Activity;
import android.graphics.Color;

/**
 * Created by johnta on 5/13/17.
 */

public class HelperWrapper {

    /**
     * Sets the background color of the activity, dependent on what the default is
     * or what the user has determined in the settings
     * @param activity
     */
    public static void setBackgroundColorWindow(Activity activity) {
        String color = BackgroundColorSelector.getStringColor();

        if (color == null) {
            color = "#107FC9";
        }

        activity.getWindow().getDecorView().setBackgroundColor(Color.parseColor(color));
    }
}
