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

import com.example.johnta.homeworkappv2.adapters.AssignmentAdapter;
import com.example.johnta.homeworkappv2.adapters.AssignmentStruct;
import com.example.johnta.homeworkappv2.popup.PlannerPopup;

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

    private static ArrayList<AssignmentStruct> arrayOfInformation = new ArrayList<AssignmentStruct>();
    private static AssignmentAdapter assignmentAdapter;
    private static ArrayList<String> arrayFromDatabase = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        popUpWindow = new PopupWindow(this);
        mainLayout = new LinearLayout(this);

        setContentView(R.layout.activity_planner);

        assignmentAdapter = new AssignmentAdapter(this, arrayOfInformation);

        ListView listView = (ListView) findViewById(list);
        listView.setAdapter(assignmentAdapter);

        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);

                startActivity(new Intent(PlannerActivity.this, DeleteObjectFromArray.class));

                DeleteObjectFromArray delObject = new DeleteObjectFromArray(assignmentAdapter);

                delObject.deleteObject(assignmentAdapter, position);
                ArrayList<String> fun = new ArrayList<String>();
            }
        });*/


    }

    public void onClickEdit (View v) {
        Log.i("PlannerActivity","Item has been clicked!!!");

        startActivity(new Intent(PlannerActivity.this,PlannerPopup.class));
    }

    public static void addItemToArray (String itemToAdd, String itemToAdd_2) {
        AssignmentStruct newItem = new AssignmentStruct(itemToAdd, itemToAdd_2);
        assignmentAdapter.add(newItem);

        assignmentAdapter.notifyDataSetChanged();
        System.out.print(assignmentAdapter.toString());


    }

    public void onClickCopy (View v) {

    }

    @Override
    protected void onListItemClick (ListView list, View v, int position, long id) {
        super.onListItemClick(list,v,position,id);
        startActivity(new Intent(PlannerActivity.this,PlannerPopup.class));
        Log.i("PlannerActivity","Item has been clicked!!!");
    }

}

