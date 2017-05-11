package com.example.johnta.homeworkappv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

public class Error404_popup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error404_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.6),(int)(height*0.3));
    }

    public void onClickReturn(View view) {
        super.onBackPressed();
    }

    public void newURL (View view) {
        startActivity(new Intent(Error404_popup.this, MakeScheduleURL_popup.class));
        super.onBackPressed();
    }
}
