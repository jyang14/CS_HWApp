package com.example.johnta.homeworkappv2.backend;

/**
 * Created by johnta on 5/13/17.
 * Static class that determines the color of the background, dependent on which button was clicked.
 */

public class BackgroundColorSelector {

    private static String color;

    /**
     * Set the color
     * @param hexCode Color code, in hex
     */
    public static void setStringColor (String hexCode) {
        color = hexCode;
    }

    /**
     * Get the color
     * @return Color, in hex
     */
    public static String getStringColor () {
        return color;
    }
}
