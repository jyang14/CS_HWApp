package com.example.johnta.homeworkappv2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

public class PlannerActivity extends ListActivity {

    public static ArrayList<String> arrayList, arrayList_assignments;
    private static ArrayAdapter<String> adapter, adapter_assignments;
    private static EditText txtInput;
    private static TextView tvMsg;
    private static PopupWindow popUpWindow;
    private static LinearLayout mainLayout;

    private static ArrayList<NameAssignments_backend> arrayOfInformation = new ArrayList<NameAssignments_backend>();
    private static ArraysIntoOne_backend bigAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        popUpWindow = new PopupWindow(this);
        mainLayout = new LinearLayout(this);

        setContentView(R.layout.activity_planner);

        bigAdapter = new ArraysIntoOne_backend(this, arrayOfInformation);

        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(bigAdapter);
    }

    public void onClickEdit (View v) {
        startActivity(new Intent(PlannerActivity.this,PlannerPopup_popup.class));
    }

    public static void addItemToArray (String itemToAdd, String itemToAdd_2) {

        NameAssignments_backend newUser = new NameAssignments_backend(itemToAdd, itemToAdd_2);
        bigAdapter.add(newUser);
        bigAdapter.notifyDataSetChanged();
    }

    public void onClickCopy (View v) {

    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
        // Then you start a new Activity via Intent

    }
}

