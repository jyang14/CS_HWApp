package com.example.johnta.homeworkappv2.popup;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;

import com.example.johnta.homeworkappv2.PlannerActivity;
import com.example.johnta.homeworkappv2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by johnta on 4/3/17.
 */

public class PlannerPopup_popup extends Activity {

    private EditText txtInput;
    private EditText input_assignment;

    DatabaseReference mDatabase;
    FirebaseDatabase mFirebase;

    //DatabaseReference mDatabase;
    //FirebaseDatabase mFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_planner);

        mFirebase = FirebaseDatabase.getInstance();
        mDatabase = mFirebase.getReference();

        txtInput = (EditText) findViewById(R.id.name_of_class);
        input_assignment = (EditText) findViewById(R.id.class_assignment);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));
    }

    public void onClickEditList (View v) {

        txtInput = (EditText)findViewById(R.id.name_of_class);
        input_assignment = (EditText)findViewById(R.id.class_assignment);

        String newItem = txtInput.getText().toString();
        String newItem_2 = input_assignment.getText().toString();
        PlannerActivity.addItemToArray(newItem,newItem_2);

        mDatabase.child("Class_Name").push().setValue(txtInput.getText().toString());
        mDatabase.child("Assignment_Name").push().setValue(input_assignment.getText().toString());

        super.onBackPressed();
    }
}
