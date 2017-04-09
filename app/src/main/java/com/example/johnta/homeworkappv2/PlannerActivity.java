package com.example.johnta.homeworkappv2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    private static ArrayList<name_assignments> arrayOfInformation = new ArrayList<name_assignments>();
    private static Arrays_Into_One bigAdapter;

    private String [] items = {"Physics","Humanities","Math","STEM"};
    private String [] list_of_assignments = {"Notes","Reading","Problems","Engineering"};
    private String [][] twoDimensionalItems = {{"Physics","Notes"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        popUpWindow = new PopupWindow(this);
        mainLayout = new LinearLayout(this);

        setContentView(R.layout.activity_planner);

        bigAdapter = new Arrays_Into_One(this, arrayOfInformation);

        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(bigAdapter);

    }

    public void onClickEdit (View v) {
        startActivity(new Intent(PlannerActivity.this,popup.class));
    }

    public static void addItemToArray (String itemToAdd, String itemToAdd_2) {

        name_assignments newUser = new name_assignments(itemToAdd, itemToAdd_2);
        bigAdapter.add(newUser);
        bigAdapter.notifyDataSetChanged();
    }

}

