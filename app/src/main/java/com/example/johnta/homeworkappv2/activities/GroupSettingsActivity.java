package com.example.johnta.homeworkappv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.firebase.FirebaseWrapper;
import com.example.johnta.homeworkappv2.popup.ErrorGeneralPopup;

public class GroupSettingsActivity extends AppCompatActivity {

    private long uuidlong;
    private String groupName, uuid;

    /**
     * Start activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_settings);

        if (FirebaseWrapper.getInstance(this).getGroup() == null) {
            finish();
            startActivity(new Intent(this, ErrorGeneralPopup.class));
        } else {
            uuidlong = FirebaseWrapper.getInstance(this).getGroup().UUID;
            uuid = String.valueOf(uuidlong);

            groupName = FirebaseWrapper.getInstance(this).getGroup().name;
        }

        TextView groupNameTextView = (TextView)findViewById(R.id.groupSettings_name_textView);
        groupNameTextView.setText(groupName);

        TextView iD = (TextView)findViewById(R.id.groupSettings_ID_textView);
        iD.setText(uuid);

    }

    /**
     * Return to previous activity
     * @param view Current View
     */
    public void onClickReturn (View view) {
        finish();
        super.onBackPressed();
    }


}
