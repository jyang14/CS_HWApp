package com.example.johnta.homeworkappv2.popup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.backend.HelperWrapper;

public class ButtonSoundPopup extends Activity {

    TextView buttonText;
    String buttonText2;

    private static boolean testCase = true;
    private static String enableSound = "Enable Sound";
    private static String disableSound = "Disable Sound";

    /**
     * Creates the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_button_sound);

        /*
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));
        buttonText = ((TextView)findViewById(R.id.popup_buttonSound_button));

        if (testCase) {
            buttonText.setText(disableSound);
        } else {
            buttonText.setText(enableSound);
        }
        */

    }

    /**
     * Called when user clicks on enable/disable sound
     * @param view the current view
     */
    public void controlSound(View view) {

        buttonText = ((TextView)findViewById(R.id.popup_buttonSound_button));
        buttonText2 = ((TextView)findViewById(R.id.popup_buttonSound_button)).getText().toString();

        if (testCase) {
            testCase = !testCase;
            buttonText.setText(enableSound);
        } else {
            testCase = !testCase;
            buttonText.setText(disableSound);
        }
    }

    /**
     * Returns the value of the test case
     * @return boolean testCase
     */
    public static boolean getTestCaseValue () {
        return testCase;
    }

    /**
     * Overrides onResume and calls the HelperWrapper.setBackgroundColor method
     */
    @Override
    public void onResume() {
        super.onResume();
        HelperWrapper.setBackgroundColorWindow(this);
    }

    public void onClickCancel (View view) {
        finish();
        super.onBackPressed();
    }

}
