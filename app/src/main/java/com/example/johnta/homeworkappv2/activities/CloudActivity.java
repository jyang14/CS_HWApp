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
import com.example.johnta.homeworkappv2.firebase.FirebaseWrapper;
import com.example.johnta.homeworkappv2.firebase.data.Assignment;
import com.example.johnta.homeworkappv2.firebase.handler.AssignmentHandler;
import com.example.johnta.homeworkappv2.popup.CloudPopup;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class CloudActivity extends AppCompatActivity implements AssignmentHandler {

    private static final String TAG = "CLOUDACTIVITY";

    private AssignmentAdapter assignmentAdapter;

    /**
     * Starts the activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cloud);

        assignmentAdapter = new AssignmentAdapter(this, new ArrayList<Assignment>());
        assignmentAdapter.clear();

        final ListView listView = (ListView) findViewById(list);
        listView.setAdapter(assignmentAdapter);
        assignmentAdapter.notifyDataSetChanged();

        FirebaseWrapper.getInstance(this).onGroupChanges(this);

        FirebaseWrapper.getInstance(this).getGroupAssignments(this);

    }

    /**
     * Overrides onResume and calls the HelperWrapper.setBackgroundColor method
     */
    @Override
    public void onResume() {
        super.onResume();
        HelperWrapper.setBackgroundColorWindow(this);
        FirebaseWrapper.getInstance(this).getGroupAssignments(this);
    }

    /**
     * Called when user clicks "add"
     *
     * @param v current view
     */
    public void onClickAdd(View v) {
        startActivity(new Intent(CloudActivity.this, CloudPopup.class));
    }

    public void onClickCopyToPlanner(View v) {
        FirebaseWrapper.getInstance(this).copyGroupToUser();
    }

    /**
     * Handled by internal caller
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
                assignmentAdapter.notifyDataSetChanged();
                FirebaseWrapper.getInstance(this).removeAssignmentFromGroup(assignment);
            }
        }
    }

    @Override
    public void handleAssignments(List<Assignment> assignments) {
        Log.v(TAG, "Handling Assignments");
        assignmentAdapter.clear();
        assignmentAdapter.addAll(assignments);
        assignmentAdapter.notifyDataSetChanged();
    }
}
