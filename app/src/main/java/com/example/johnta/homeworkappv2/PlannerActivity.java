package com.example.johnta.homeworkappv2;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class PlannerActivity extends ListActivity {

    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private EditText txtInput;
    private TextView tvMsg;
    private PopupWindow popUpWindow;
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        popUpWindow = new PopupWindow(this);
        mainLayout = new LinearLayout(this);

        setContentView(R.layout.activity_planner);

        ListView listView = (ListView)findViewById(android.R.id.list);

        String [] items = {"bob"};

        arrayList = new ArrayList<>(Arrays.asList(items));

        adapter = new ArrayAdapter<String>(this,R.layout.editlistitems,R.id.name_of_class,arrayList);

        listView.setAdapter(adapter);

        txtInput = (EditText)findViewById(R.id.name_of_class);



    }

    public void makeEditable(View v) {

    }

    public void onClickEdit (View v) {
    /*
        popUpWindow.showAtLocation(mainLayout, Gravity.CENTER,100,100);
        popUpWindow.update(50,50,50,50);
        tvMsg = new TextView(this);
        tvMsg.setText("Hi this is pop up window...");
        */


        String newItem = txtInput.getText().toString();
        arrayList.add(newItem);
        adapter.notifyDataSetChanged();


    }


}

