package com.example.johnta.homeworkappv2.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.adapters.AssignmentAdapter;
import com.example.johnta.homeworkappv2.backend.HelperWrapper;
import com.example.johnta.homeworkappv2.firebase.FirebaseWrapper;
import com.example.johnta.homeworkappv2.firebase.data.Assignment;
import com.example.johnta.homeworkappv2.firebase.handler.AssignmentHandler;
import com.example.johnta.homeworkappv2.popup.PlannerPopup;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class PlannerActivity extends ListActivity implements AssignmentHandler {

    private static final String TAG = "PLANNERACTIVITY";

    private AssignmentAdapter assignmentAdapter;

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

        final ListView listView = (ListView) findViewById(list);
        listView.setAdapter(assignmentAdapter);
        assignmentAdapter.notifyDataSetChanged();

        FirebaseWrapper.getInstance(this).getUserAssignments(this);

        //FirebaseWrapper.getInstance(this).refreshLists(listView, assignmentAdapter);
        // assignmentAdapter = new ArraysIntoOne_backend(this, arrayOfInformation);
    }

    /**
     * Opens up popup PlannerPopup when clicked to allow users to populate assignments to planner
     *
     * @param v View
     */
    public void onClickAdd(View v) {
        startActivity(new Intent(PlannerActivity.this, PlannerPopup.class));
    }

    public void onClickCopy(View v) {

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

        if (request == 123 && result == RESULT_OK) {
            Bundle bundle = data.getExtras();
            final int position = bundle.getInt("position");
            boolean delete = bundle.getBoolean("delete");

            if (delete) {
                Assignment assignment = assignmentAdapter.getItem(position);
                assignmentAdapter.remove(assignment);
                FirebaseWrapper.getInstance(this).removeAssignmentFromUser(assignment);
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

