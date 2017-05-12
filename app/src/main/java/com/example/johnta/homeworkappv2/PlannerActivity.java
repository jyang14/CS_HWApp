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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.R.id.list;

public class PlannerActivity extends ListActivity {

    private static final String TAG = "PLANNERACTIVITY";

    public static ArrayList<String> arrayList, arrayList_assignments;
    private static ArrayAdapter<String> adapter, adapter_assignments;

    private static int position;

    private static boolean pathWay = true;
    private TextView buttonText;

    private static ArrayList<AssignmentStructure> arrayOfInformation = new ArrayList<AssignmentStructure>();
    private static AssignmentAdapter assignmentAdapter;
    private static ArrayList<String> arrayFromDatabase = new ArrayList<>();

    static DatabaseReference mDatabase;
    static FirebaseDatabase mFirebase;

    static DatabaseReference mDatabaseForDelete;
    static FirebaseDatabase mFirebaseForDelete;
    private static AssignmentAdapter assignmentAdapterForDelete;

    /**
     * Oncreate method for the initilization of the PlannerActivity screen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirebase = FirebaseDatabase.getInstance();
        mDatabase = mFirebase.getReference("Assignment");

        setContentView(R.layout.activity_planner);

        assignmentAdapter = new AssignmentAdapter(this, arrayOfInformation);
        assignmentAdapter.clear();

        final ListView listView = (ListView) findViewById(list);
        listView.setAdapter(assignmentAdapter);

        FirebaseWrapper.getInstance(this).refreshLists(listView, mDatabase, assignmentAdapter);

        // assignmentAdapter = new ArraysIntoOne_backend(this, arrayOfInformation);
        assignmentAdapter.notifyDataSetChanged();
    }

    private void refreshLists(final ListView listView) {
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                AssignmentStructure assignment = dataSnapshot.getValue(AssignmentStructure.class);

                assignmentAdapter.add(assignment);
                //assignmentAdapter.notifyDataSetChanged();

                //assignmentAdapter.add(assignment);
                listView.setAdapter(assignmentAdapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    /**
     * Adds item to the listView upon completion of user input
     * @param className - name of the class
     * @param assignmentName - description of assignment
     */
    public static void addItemToArray(String className, String assignmentName) {
        mDatabase = mFirebase.getReference();

        AssignmentStructure assignment = new AssignmentStructure(className, assignmentName);
        //assignmentAdapter.add(assignment);

        mDatabase.child("Assignment").push().setValue(assignment);

        //assignmentAdapter.notifyDataSetChanged();
        //Log.v(TAG, assignmentAdapter.toString());
    }

    /**
     * Opens up popup PlannerPopup when clicked to allow users to populate assignments to planner
     * @param v
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
     * @param request
     * @param result
     * @param data
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

