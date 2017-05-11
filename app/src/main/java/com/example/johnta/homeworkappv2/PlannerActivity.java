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
import com.example.johnta.homeworkappv2.adapters.AssignmentStructure;
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

    private static ArrayList<AssignmentStructure> arrayOfInformation = new ArrayList<AssignmentStructure>();
    private static AssignmentAdapter assignmentAdapter;
    private static ArrayList<String> arrayFromDatabase = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        popUpWindow = new PopupWindow(this);
        mainLayout = new LinearLayout(this);

        setContentView(R.layout.activity_planner);

        assignmentAdapter = new AssignmentAdapter(this, arrayOfInformation);

        ListView listView = (ListView) findViewById(list);
        listView.setAdapter(assignmentAdapter);
    }

    public void onClickEdit (View v) {
        Log.i("PlannerActivity","Item has been clicked!!!");

        startActivity(new Intent(PlannerActivity.this,PlannerPopup.class));
    }

    public static void addItemToArray (String className, String assignmentName) {
        AssignmentStructure assignment = new AssignmentStructure(className, assignmentName);
        assignmentAdapter.add(assignment);

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

    @Override
    public void onActivityResult(int request, int result, Intent data) {
        super.onActivityResult(request, result, data);

        if (request == 123 && result == RESULT_OK) {
            Bundle bundle = data.getExtras();
            int position = bundle.getInt("position");
            boolean delete = bundle.getBoolean("delete");

            if (delete) {
                assignmentAdapter.remove(assignmentAdapter.getItem(position));
            }
        }

    }
}

