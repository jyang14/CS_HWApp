package com.example.johnta.homeworkappv2.popup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.backend.PlaySound;
import com.example.johnta.homeworkappv2.firebase.data.Assignment;
import com.example.johnta.homeworkappv2.backend.HelperWrapper;
import com.example.johnta.homeworkappv2.firebase.FirebaseWrapper;

public class AddAssignmentToCloudPopup extends Activity {

    private EditText txtInput;
    private EditText input_assignment;

    private String nameOfClassCloud, classAssignmentCloud;

    PlaySound play;

    /**
     * Starts the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_add_assignment_to_cloud);

        /*
        txtInput = (EditText) findViewById(R.id.name_of_class);
        input_assignment = (EditText) findViewById(R.id.class_assignment);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));
        */

        play = new PlaySound(this);
    }

    /**
     * User clicks button "cancel" to return to previous screen
     * @param v
     */
    public void onClickCancel (View v) {
        play.playSound();

        finish();
        super.onBackPressed();
    }

    /**
     * Adds user input to FireBase
     * @param v
     */
    public void onClickAdd (View v) {
        txtInput = (EditText)findViewById(R.id.popup_cloudPlanner_name_of_class);
        input_assignment = (EditText)findViewById(R.id.popup_cloudPlanner_assignment_name);
        nameOfClassCloud = txtInput.getText().toString();
        classAssignmentCloud = input_assignment.getText().toString();

        Assignment assignment = new Assignment(nameOfClassCloud,classAssignmentCloud);
        FirebaseWrapper.getInstance(this).addAssignmentToGroup(assignment);
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
