package com.example.johnta.homeworkappv2.popup;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.example.johnta.homeworkappv2.R;

public class ButtonSoundPopup extends Activity {

    TextView buttonText;
    String buttonText2;

    private static boolean testCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_button_sound);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));
        buttonText = ((TextView)findViewById(R.id.popup_buttonSound_button));

        if (buttonText.getText().toString().equals("Enable Sound")) {
            testCase = true;
        } else {
            testCase = false;
        }
    }

    public void controlSound(View v) {

        buttonText2 = ((TextView)findViewById(R.id.popup_buttonSound_button)).getText().toString();

        if (buttonText2.equals("Enable Sound")) {
            buttonText.setText("Disable Sound");
            testCase = false;
        } else {
            buttonText.setText("Enable Sound");
            testCase = true;
        }
    }

    public static boolean getTestCaseValue () {
        return testCase;
    }


}
