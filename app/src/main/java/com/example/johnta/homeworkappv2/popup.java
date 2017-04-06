package com.example.johnta.homeworkappv2;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;

/**
 * Created by johnta on 4/3/17.
 */

public class popup extends Activity {

    private EditText txtInput;
    private EditText input_assignment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.plannerpopup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));

        txtInput = (EditText)findViewById(R.id.name_of_class);
        input_assignment = (EditText)findViewById(R.id.class_assignment);
    }

    public void onClickEditList (View v) {
        String newItem = txtInput.getText().toString();
        PlannerActivity.addItemToArray(newItem);
        super.onBackPressed();
    }
}
