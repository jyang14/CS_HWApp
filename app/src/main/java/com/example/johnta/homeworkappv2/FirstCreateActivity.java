package com.example.johnta.homeworkappv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

public class FirstCreateActivity extends AppCompatActivity {

    private RelativeLayout layout_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstcreate);

        layout_main = (RelativeLayout)findViewById(R.id.relativeLayoutFirstCreate);
        layout_main.setVisibility(View.VISIBLE);
    }

    public void onSignInUser(View view) {
        layout_main.setVisibility(View.GONE);
        startActivity(new Intent(FirstCreateActivity.this,LoginPopup_popup.class));
    }

    public void onCreateNewUser(View view) {
        startActivity(new Intent(FirstCreateActivity.this,NewUserPopup_popup.class));
    }

}
