package com.example.johnta.homeworkappv2;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class DeleteObjectFromArray extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_object_from_array);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));
    }

    /* Constructor */
    public DeleteObjectFromArray (ArraysIntoOne_backend bigAdapter) {

    }

    public void deleteObject (ArraysIntoOne_backend bigAdapter, int position){

    }
}
