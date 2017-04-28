package com.example.johnta.homeworkappv2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ScheduleActivity extends AppCompatActivity {

    private static String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
    }

    public void toWebsite (View view) {
        if (url == null) {
            startActivity(new Intent(ScheduleActivity.this, MakeScheduleURL_popup.class));
        } else {
            toWebsiteScheduleURL();
        }

    }

    public static void setURL (String url2) {
        url = url2;
    }

    public void toPersonalSchedule (View view) {

    }

    public void toWebsiteScheduleURL () {
        goToUrl (url);
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

}
