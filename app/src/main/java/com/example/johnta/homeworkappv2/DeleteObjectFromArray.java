package com.example.johnta.homeworkappv2;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.johnta.homeworkappv2.adapters.AssignmentAdapter;

public class DeleteObjectFromArray extends Activity {

    /* Constructor */
    public DeleteObjectFromArray(AssignmentAdapter assignmentAdapter) {

    }

    public DeleteObjectFromArray() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_object_from_array);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.6));
    }

    public void deleteObject(AssignmentAdapter assignmentAdapter, int position) {

    }
}
