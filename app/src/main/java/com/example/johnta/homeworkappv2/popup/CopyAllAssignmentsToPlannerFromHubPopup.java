package com.example.johnta.homeworkappv2.popup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.firebase.FirebaseWrapper;

public class CopyAllAssignmentsToPlannerFromHubPopup extends AppCompatActivity {

    /**
     * Starts activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_copy_all_assignments_to_planner);
    }

    /**
     * Copy cloud assignments to individual planner
     * @param view Current View
     */
    public void onClickCopyCloud (View view) {
        FirebaseWrapper.getInstance(this).copyGroupToUser();
        finish();
        super.onBackPressed();
    }

    /**
     * Return to previous activity
     * @param view Current view
     */
    public void onClickCancel (View view) {
        finish();
        super.onBackPressed();
    }

}
