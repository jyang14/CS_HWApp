package com.example.johnta.homeworkappv2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.johnta.homeworkappv2.popup.Error404_popup;
import com.example.johnta.homeworkappv2.popup.MakeScheduleURL_popup;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ScheduleActivity extends AppCompatActivity {

    private static String url;
    private static String response;

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
        startActivity(new Intent(ScheduleActivity.this, PersonalSchedule.class));
    }

    public void toWebsiteScheduleURL () {

        try {

            int index = url.indexOf("http:");
            boolean transfer = true;

            if (index != - 1) {
                URL u = new URL(url);
                HttpURLConnection huc = (HttpURLConnection) u.openConnection();
                huc.setRequestMethod("GET");
                huc.connect();
                response = Integer.toString((huc.getResponseCode()));
            }

            if (index == -1) {
                startActivity(new Intent(ScheduleActivity.this, Error404_popup.class));
            } else if (!(response.equals("200"))) {
                startActivity(new Intent(ScheduleActivity.this, Error404_popup.class));
            } else {
                goToUrl(url);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

       // Log.i("John100",response);

    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

}
