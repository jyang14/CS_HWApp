package com.example.johnta.homeworkappv2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.johnta.homeworkappv2.adapters.AssignmentAdapter;
import com.example.johnta.homeworkappv2.adapters.AssignmentStructure;
import com.example.johnta.homeworkappv2.firebase.FirebaseWrapper;
import com.example.johnta.homeworkappv2.popup.PlannerPopup;

import java.util.ArrayList;

import static android.R.id.list;

public class PlannerActivity extends ListActivity {

    private static final String TAG = "PLANNERACTIVITY";

    public static ArrayList<String> arrayList, arrayList_assignments;
    private static ArrayAdapter<String> adapter, adapter_assignments;

    private static int position;

    private static boolean pathWay = true;
    private TextView buttonText;

    private static ArrayList<AssignmentStructure> arrayOfInformation = new ArrayList<>();
    private static AssignmentAdapter assignmentAdapter;
    private static ArrayList<String> arrayFromDatabase = new ArrayList<>();

    /**
     * Oncreate method for the initilization of the PlannerActivity screen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_planner);

        assignmentAdapter = new AssignmentAdapter(this, arrayOfInformation);
        assignmentAdapter.clear();

        final ListView listView = (ListView) findViewById(list);
        listView.setAdapter(assignmentAdapter);

        FirebaseWrapper.getInstance(this).refreshLists(listView, assignmentAdapter);

        // assignmentAdapter = new ArraysIntoOne_backend(this, arrayOfInformation);
        assignmentAdapter.notifyDataSetChanged();
    }

    /**
     * Opens up popup PlannerPopup when clicked to allow users to populate assignments to planner
     * @param v View
     */
    public void onClickAdd(View v) {
        Log.i("PlannerActivity", "Item has been clicked!!!");

        startActivity(new Intent(PlannerActivity.this, PlannerPopup.class));
    }

    public void onClickCopy (View v) {

    }

    /**
     * Checks for the result and sees if the state is correct in order to determine whether or not to delete
     * the specified object in the position
     * @param request request code
     * @param result result code
     * @param data Intent containing data
     */
    @Override
    public void onActivityResult(int request, int result, Intent data) {
        super.onActivityResult(request, result, data);

        if (request == 123 && result == RESULT_OK) {
            Bundle bundle = data.getExtras();
            final int position = bundle.getInt("position");
            boolean delete = bundle.getBoolean("delete");

            if (delete) {
                assignmentAdapter.remove(assignmentAdapter.getItem(position));

            }
        }

    }
}

