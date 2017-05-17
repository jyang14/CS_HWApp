package com.example.johnta.homeworkappv2.popup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.firebase.FirebaseWrapper;

public class CopyAssignmentsPopup extends Activity {

    /**
     * Starts activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_copy_all_assignments);
    }

    /**
     * Copy user planner to group planner
     * @param view Current view
     */
    public void onClickCopy (View view) {
        FirebaseWrapper.getInstance(this).copyUserToGroup();
        finish();
        super.onBackPressed();
    }

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
