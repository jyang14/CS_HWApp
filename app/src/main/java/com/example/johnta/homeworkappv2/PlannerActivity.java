package com.example.johnta.homeworkappv2;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class PlannerActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_planner);

        String[] list = {"boo","he","lol","ha","hhs","skskd","sjjskd","sdjskaasx","ksksdjk","skdsds","sally","sucks","a lot","lol"
                ,"ha","moo","ping","dong","chong","long"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getListView().getContext(),android.R.layout.simple_list_item_1, list);
        getListView().setAdapter(adapter);
    }

    public void makeEditable(View v) {

    }

    public void onClick (View v) {

    }
}
