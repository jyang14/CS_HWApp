package com.example.johnta.homeworkappv2.popup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.backend.HelperWrapper;
import com.example.johnta.homeworkappv2.backend.PlaySound;
import com.example.johnta.homeworkappv2.firebase.FirebaseWrapper;
import com.example.johnta.homeworkappv2.firebase.data.Assignment;

/**
 * Created by johnta on 4/3/17.
 */

public class AddAssignmentToPlannerPopup extends Activity {

    private EditText classEditTest;
    private EditText assignmentEditText;

    PlaySound play;

    /**
     * Creates the popup_planner activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_add_assignment_to_planner);

        classEditTest = (EditText) findViewById(R.id.name_of_class);
        assignmentEditText = (EditText) findViewById(R.id.class_assignment);

        play = new PlaySound (this);

        /*
        txtInput = (EditText) findViewById(R.id.name_of_class);
        input_assignment = (EditText) findViewById(R.id.class_assignment);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.6));
        */
    }

    /**
     * Adds user input to FireBase
     * @param v Current view
     */
    public void onClickEditList(View v) {
        play.playSound();

        String className = classEditTest.getText().toString();
        String assignmentName = assignmentEditText.getText().toString();
        Assignment assignment = new Assignment(className, assignmentName);
        FirebaseWrapper.getInstance(this).addAssignmentToUser(assignment);
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

    /**
     * Cancels the addition of the item and returns to previous activity
     * @param v Current view
     */
    public void onClickCancel(View v) {
        play.playSound();

        finish();
        super.onBackPressed();
    }
}
