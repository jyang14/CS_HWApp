package com.example.johnta.homeworkappv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.johnta.homeworkappv2.adapters.AssignmentAdapter;
import com.example.johnta.homeworkappv2.adapters.AssignmentStructure;
import com.example.johnta.homeworkappv2.popup.CloudPopup;

import java.util.ArrayList;

import static android.R.id.list;

public class CloudActivity extends AppCompatActivity {

    private AssignmentAdapter assignmentAdapterCloud;
    private static ArrayList<AssignmentStructure> arrayOfInformationCloud = new ArrayList<AssignmentStructure>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud);

        assignmentAdapterCloud = new AssignmentAdapter(this, arrayOfInformationCloud);

        ListView listView = (ListView) findViewById(list);
        listView.setAdapter(assignmentAdapterCloud);
    }

    public void onClickEdit (View v) {
        startActivity(new Intent(CloudActivity.this, CloudPopup.class));
    }

    public void onClickCopyToPlanner (View v) {

    }

    public void addItemToArray (String itemToAdd, String itemToAdd_2) {
        AssignmentStructure newItemCloud = new AssignmentStructure(itemToAdd, itemToAdd_2);
        assignmentAdapterCloud.add(newItemCloud);

        assignmentAdapterCloud.notifyDataSetChanged();
        System.out.print(assignmentAdapterCloud.toString());
    }
}
