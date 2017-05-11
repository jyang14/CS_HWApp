package com.example.johnta.homeworkappv2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.id.list;

public class PlannerActivity extends ListActivity {

    public static ArrayList<String> arrayList, arrayList_assignments;
    private static ArrayAdapter<String> adapter, adapter_assignments;
    private static EditText txtInput;
    private static TextView tvMsg;
    private static PopupWindow popUpWindow;
    private static LinearLayout mainLayout;

    private static int position;

    private static ArrayList<NameAssignments_backend> arrayOfInformation = new ArrayList<NameAssignments_backend>();
    private static ArraysIntoOne_backend bigAdapter;
    private static ArrayList<String> arrayFromDatabase = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        popUpWindow = new PopupWindow(this);
        mainLayout = new LinearLayout(this);

        setContentView(R.layout.activity_planner);

        bigAdapter = new ArraysIntoOne_backend(this, arrayOfInformation);

        ListView listView = (ListView) findViewById(list);
        listView.setAdapter(bigAdapter);

        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);

                startActivity(new Intent(PlannerActivity.this, DeleteObjectFromArray.class));

                DeleteObjectFromArray delObject = new DeleteObjectFromArray(bigAdapter);

                delObject.deleteObject(bigAdapter, position);
                ArrayList<String> fun = new ArrayList<String>();
            }
        });*/


    }

    public void onClickEdit (View v) {
        Log.i("PlannerActivity","Item has been clicked!!!");

        startActivity(new Intent(PlannerActivity.this,PlannerPopup_popup.class));
    }

    public static void addItemToArray (String itemToAdd, String itemToAdd_2) {
        NameAssignments_backend newItem = new NameAssignments_backend(itemToAdd, itemToAdd_2);
        bigAdapter.add(newItem);

        bigAdapter.notifyDataSetChanged();
        System.out.print(bigAdapter.toString());


    }

    public void onClickCopy (View v) {

    }

    protected void onListItemClick (ListView list, View v, int position, long id) {
        startActivity(new Intent(PlannerActivity.this,PlannerPopup_popup.class));
        Log.i("PlannerActivity","Item has been clicked!!!");
    }

}

