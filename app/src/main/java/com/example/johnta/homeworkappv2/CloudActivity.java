package com.example.johnta.homeworkappv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.johnta.homeworkappv2.adapters.AssignmentAdapter;
import com.example.johnta.homeworkappv2.adapters.AssignmentStructure;
import com.example.johnta.homeworkappv2.popup.CloudPopup;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.R.id.list;

public class CloudActivity extends AppCompatActivity {

    private static AssignmentAdapter assignmentAdapterCloud;
    private static ArrayList<AssignmentStructure> arrayOfInformationCloud = new ArrayList<AssignmentStructure>();

    static DatabaseReference mDatabase;
    static FirebaseDatabase mFirebase;

    /**
     * Starts the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud);

        assignmentAdapterCloud = new AssignmentAdapter(this, arrayOfInformationCloud);
        ListView listView = (ListView) findViewById(list);
        listView.setAdapter(assignmentAdapterCloud);
    }

    /**
     * Called when user clicks "add"
     * @param v current view
     */
    public void onClickAdd(View v) {
        startActivity(new Intent(CloudActivity.this, CloudPopup.class));
    }

    public void onClickCopyToPlanner(View v) {

    }

    /**
     * Called when user clicks "enter" in popup
     * @param itemToAdd the name of the class
     * @param itemToAdd_2 the description of the assignment itself
     */
    public static void addItemToArray(String itemToAdd, String itemToAdd_2) {
        AssignmentStructure newItemCloud = new AssignmentStructure(itemToAdd, itemToAdd_2);
        assignmentAdapterCloud.add(newItemCloud);

        assignmentAdapterCloud.notifyDataSetChanged();
    }

    /**
     * Handled by internal caller
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
                assignmentAdapterCloud.remove(assignmentAdapterCloud.getItem(position));

            }
        }
    }
}
