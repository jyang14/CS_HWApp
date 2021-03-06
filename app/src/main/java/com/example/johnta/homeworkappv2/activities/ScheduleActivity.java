package com.example.johnta.homeworkappv2.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.backend.HelperWrapper;
import com.example.johnta.homeworkappv2.backend.PlaySound;
import com.example.johnta.homeworkappv2.firebase.FirebaseWrapper;
import com.example.johnta.homeworkappv2.popup.CreateScheduleURLPopup;
import com.example.johnta.homeworkappv2.popup.DisplayWebsiteURL;
import com.example.johnta.homeworkappv2.popup.Error404Popup;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ScheduleActivity extends AppCompatActivity {

    private static String url;
    private static String response;
    PlaySound play;

    public static void setURL(String url2) {

        url = url2;
    }

    /**
     * Start activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
    }


    public void toWebsite(View view) {

        url = FirebaseWrapper.getInstance(this).getUrl();

        if (url == null) {
            startActivity(new Intent(ScheduleActivity.this, CreateScheduleURLPopup.class));
        } else {
            startActivity(new Intent(ScheduleActivity.this, DisplayWebsiteURL.class));
        }

    }

    public void toPersonalSchedule(View view) {
        startActivity(new Intent(ScheduleActivity.this, PersonalScheduleActivity.class));
    }

    public void toWebsiteScheduleURL() {

        try {

            int index = url.indexOf("http:");
            boolean transfer = true;

            Log.i("jinchao",url);

            if (index != -1) {
                URL u = new URL(url);
                HttpURLConnection huc = (HttpURLConnection) u.openConnection();
                huc.setRequestMethod("GET");

                response = Integer.toString((huc.getResponseCode()));
                Log.i("jinchao","success");
            }

           // Log.i("jinchao",response);

            if (index == -1) {
                startActivity(new Intent(ScheduleActivity.this, Error404Popup.class));
            } else if (!(response.equals("200"))) {
                startActivity(new Intent(ScheduleActivity.this, Error404Popup.class));
            } else {
                goToUrl(url);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            startActivity(new Intent(ScheduleActivity.this, Error404Popup.class));
        } catch (IOException e) {
            e.printStackTrace();
            startActivity(new Intent(ScheduleActivity.this, Error404Popup.class));
        }

        // Log.i("John100",response);

    }

    private void goToUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    /**
     * Overrides onResume and calls the HelperWrapper.setBackgroundColor method
     */
    @Override
    public void onResume() {
        super.onResume();
        HelperWrapper.setBackgroundColorWindow(this);
    }

}
