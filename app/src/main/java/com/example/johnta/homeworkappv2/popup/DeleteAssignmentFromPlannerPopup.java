package com.example.johnta.homeworkappv2.popup;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.johnta.homeworkappv2.backend.HelperWrapper;
import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.adapters.AssignmentAdapter;

public class DeleteAssignmentFromPlannerPopup extends Activity {

    /**
     * Constructor
     * @param assignmentAdapter the adapter for the main class
     */
    public DeleteAssignmentFromPlannerPopup(AssignmentAdapter assignmentAdapter) {

    }

    /**
     * Empty constructor
     */
    public DeleteAssignmentFromPlannerPopup() {

    }

    /**
     * Creates the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_object_from_array);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.6));
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
