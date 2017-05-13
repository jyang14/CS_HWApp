package com.example.johnta.homeworkappv2.popup;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;

import com.example.johnta.homeworkappv2.CloudActivity;
import com.example.johnta.homeworkappv2.R;

public class CloudPopup extends Activity {

    private EditText txtInput;
    private EditText input_assignment;

    private String nameOfClassCloud, classAssignmentCloud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_cloud);

        txtInput = (EditText) findViewById(R.id.name_of_class);
        input_assignment = (EditText) findViewById(R.id.class_assignment);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));
    }

    public void onClickCancel (View v) {
        finish();
        super.onBackPressed();
    }

    public void onClickAdd (View v) {
        txtInput = (EditText)findViewById(R.id.popup_cloudPlanner_name_of_class);
        input_assignment = (EditText)findViewById(R.id.popup_cloudPlanner_assignment_name);
        nameOfClassCloud = txtInput.getText().toString();
        classAssignmentCloud = input_assignment.getText().toString();

        CloudActivity.addItemToArray(nameOfClassCloud, classAssignmentCloud);

        finish();
        super.onBackPressed();
    }
}
