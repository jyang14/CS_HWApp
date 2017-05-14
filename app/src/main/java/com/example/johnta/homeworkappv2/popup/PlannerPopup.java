package com.example.johnta.homeworkappv2.popup;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;

import com.example.johnta.homeworkappv2.HelperWrapper;
import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.adapters.AssignmentStructure;
import com.example.johnta.homeworkappv2.firebase.FirebaseWrapper;

/**
 * Created by johnta on 4/3/17.
 */

public class PlannerPopup extends Activity {

    private EditText classEditTest;
    private EditText assignementEditText;

    /**
     * Creates the popup_planner activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_planner);

        classEditTest = (EditText) findViewById(R.id.name_of_class);
        assignementEditText = (EditText) findViewById(R.id.class_assignment);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.6));
    }

    /**
     * Adds user input to FireBase
     * @param v Current view
     */
    public void onClickEditList(View v) {

        String className = classEditTest.getText().toString();
        String assignmentName = assignementEditText.getText().toString();
        AssignmentStructure assignment = new AssignmentStructure(className, assignmentName);
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
        finish();
        super.onBackPressed();
    }
}
