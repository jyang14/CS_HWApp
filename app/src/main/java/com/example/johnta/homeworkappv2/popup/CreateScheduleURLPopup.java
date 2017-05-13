package com.example.johnta.homeworkappv2.popup;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;

import com.example.johnta.homeworkappv2.HelperWrapper;
import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.ScheduleActivity;

public class CreateScheduleURLPopup extends Activity {

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_make_schedule_url);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.8));
    }

    /**
     * User calls this method by clicking on back arrow
     * @param view
     */
    public void onClickGoBackToBefore(View view) {
        finish();
        super.onBackPressed();

    }

    public void getInput(View view) {
        url = ((EditText) findViewById(R.id.scheduleURL)).getText().toString();
        ScheduleActivity.setURL(url);
        super.onBackPressed();
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
