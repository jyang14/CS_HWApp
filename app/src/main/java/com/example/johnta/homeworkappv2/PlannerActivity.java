package com.example.johnta.homeworkappv2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
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

    private String [] items = {"Physics","Humanities","Math","STEM"};
    private String [] list_of_assignments = {"Notes","Reading","Problems","Engineering"};
    private String [][] twoDimensionalItems = {{"Physics","Notes"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        popUpWindow = new PopupWindow(this);
        mainLayout = new LinearLayout(this);

        /*
        setContentView(R.layout.activity_planner);

        ListView listView = (ListView)findViewById(android.R.id.list);

        arrayList = new ArrayList<>(Arrays.asList(items));
        arrayList_assignments = new ArrayList<>(Arrays.asList(list_of_assignments));

        adapter = new ArrayAdapter<String>(this,R.layout.editlistitems,R.id.name_of_class,arrayList);
        adapter_assignments = new ArrayAdapter<String>(this,R.layout.editlistitems,R.id.homework_description,arrayList_assignments);

        listView.setAdapter(adapter);


        txtInput = (EditText)findViewById(R.id.name_of_class);
        */


    }

    public void makeEditable(View v) {

    }

    public void onClickEdit (View v) {
        startActivity(new Intent(PlannerActivity.this,popup.class));

    }

    public static void addItemToArray (String itemToAdd) {
        arrayList.add(itemToAdd);
        adapter.notifyDataSetChanged();
    }

}

