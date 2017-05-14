package com.example.johnta.homeworkappv2.popup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.backend.HelperWrapper;
import com.example.johnta.homeworkappv2.firebase.FirebaseWrapper;

/**
 * Created by johnta on 4/3/17.
 */

public class PlannerPopup extends Activity {

    private EditText txtInput;
    private EditText input_assignment;

    /**
     * Creates the popup_planner activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_planner);

        /*
        txtInput = (EditText) findViewById(R.id.name_of_class);
        input_assignment = (EditText) findViewById(R.id.class_assignment);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.6));
        */
    }

    /**
     * Adds user input to FireBase
     * @param v Current view
     */
    public void onClickEditList(View v) {

        txtInput = (EditText) findViewById(R.id.name_of_class);
        input_assignment = (EditText) findViewById(R.id.class_assignment);

        String newItem = txtInput.getText().toString();
        String newItem_2 = input_assignment.getText().toString();
        FirebaseWrapper.getInstance(this).addItemToArray(newItem, newItem_2);

        super.onBackPressed();
    }

    /**
     * Overrides onResume and calls the HelperWrapper.setBackgroundColor method
     */
    @Override
    public void onResume() {
        super.onResume();
        HelperWrapper.setBackgroundColorWindow(this);
    }

    /**
     * Cancels the addition of the item and returns to previous activity
     * @param v Current view
     */
    public void onClickCancel(View v) {
        finish();
        super.onBackPressed();
    }
}
