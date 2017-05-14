package com.example.johnta.homeworkappv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.adapters.AssignmentAdapter;
import com.example.johnta.homeworkappv2.backend.HelperWrapper;
import com.example.johnta.homeworkappv2.backend.PlaySound;
import com.example.johnta.homeworkappv2.firebase.FirebaseWrapper;
import com.example.johnta.homeworkappv2.firebase.data.Assignment;
import com.example.johnta.homeworkappv2.firebase.handler.AssignmentHandler;
import com.example.johnta.homeworkappv2.popup.AddAssignmentToPlannerPopup;

import java.util.ArrayList;
import java.util.List;

public class PlannerActivity extends AppCompatActivity implements AssignmentHandler {

    private static final String TAG = "PLANNERACTIVITY";

    private AssignmentAdapter assignmentAdapter;

    PlaySound play;

    /**
     * Oncreate method for the initilization of the PlannerActivity screen
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_planner);

        assignmentAdapter = new AssignmentAdapter(this, new ArrayList<Assignment>());
        assignmentAdapter.clear();

        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(assignmentAdapter);
        assignmentAdapter.notifyDataSetChanged();

        FirebaseWrapper.getInstance(this).getUserAssignments(this);

        //FirebaseWrapper.getInstance(this).onGroupChanges(listView, assignmentAdapter);
        // assignmentAdapter = new ArraysIntoOne_backend(this, arrayOfInformation);

        play = new PlaySound(this);
    }

    /**
     * Opens up popup AddAssignmentToPlannerPopup when clicked to allow users to populate assignments to planner
     *
     * @param v View
     */
    public void onClickAdd(View v) {
        play.playSound();
        startActivity(new Intent(PlannerActivity.this, AddAssignmentToPlannerPopup.class));
    }

    /**
     * Copy planner stuff to main planner hub
     * @param v
     */
    public void onClickCopy(View v) {
        play.playSound();
        FirebaseWrapper.getInstance(this).copyUserToGroup();
    }

    /**
     * Checks for the result and sees if the state is correct in order to determine whether or not to delete
     * the specified object in the position
     *
     * @param request request code
     * @param result  result code
     * @param data    Intent containing data
     */
    @Override
    public void onActivityResult(int request, int result, Intent data) {
        super.onActivityResult(request, result, data);

        if (result == RESULT_OK) {
            if (request == 123) {
                Bundle bundle = data.getExtras();
                final int position = bundle.getInt("position");
                boolean delete = bundle.getBoolean("delete");

                if (delete) {
                    Assignment assignment = assignmentAdapter.getItem(position);
                    assignmentAdapter.remove(assignment);
                    FirebaseWrapper.getInstance(this).removeAssignmentFromUser(assignment);
                }
            }

            if (request == 987) {
                Bundle bundle = data.getExtras();
                final int position = bundle.getInt("position");
                boolean transfer = bundle.getBoolean("transfer");

                if (transfer) {
                    Assignment assignment = assignmentAdapter.getItem(position);

                    if (this instanceof PlannerActivity) {
                        FirebaseWrapper.getInstance(this).addAssignmentToGroup(assignment);
                    }
                }
            }
        }
    }

    /**
     * Overrides onResume and calls the HelperWrapper.setBackgroundColor method
     */
    @Override
    public void onResume() {
        super.onResume();
        HelperWrapper.setBackgroundColorWindow(this);
        FirebaseWrapper.getInstance(this).getUserAssignments(this);
    }

    @Override
    public void handleAssignments(List<Assignment> assignments) {
        Log.v(TAG, "Handling Assignments");
        assignmentAdapter.clear();
        assignmentAdapter.addAll(assignments);
        assignmentAdapter.notifyDataSetChanged();
    }
}

