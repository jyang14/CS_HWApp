package com.example.johnta.homeworkappv2.popup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.backend.HelperWrapper;

public class CopyAssignmentPlannerToHubPopup extends AppCompatActivity {

    private int position;

    /**
     * Starts activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_copy_assignment_planner_to_hub);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = bundle.getInt("position");
        TextView text = (TextView) findViewById(R.id.popup_copyAssignmentPlanner_textView);
        text.setText("Please confirm that you would like to transfer: " +bundle.getString("classname")
                + " - " + bundle.getString("thing"));

    }

    /**
     * Determine which button has been pressed and then call method setResult
     * @param v Current view
     */
    public void onClickDoStuff(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putBoolean("transfer", v.getId() == R.id.popup_copyAssignmentToHub_transfer);
        bundle.putInt("position", position);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    /**
     * Return to previous activity
     * @param view Current view
     */
    public void onClickReturn(View view) {
        finish();
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
