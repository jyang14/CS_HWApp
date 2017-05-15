package com.example.johnta.homeworkappv2.popup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.backend.HelperWrapper;
import com.example.johnta.homeworkappv2.firebase.FirebaseWrapper;

import java.util.Locale;

public class DisplayWebsiteURL extends AppCompatActivity {

    private TextView textView;
    private String url;

    /**
     * Starts the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_website_url_popup);

        textView = (TextView)findViewById(R.id.websiteUrl_textView);
        url = FirebaseWrapper.getInstance(this).getUrl();

        if (url == null) {
            url = "";
        }

        textView.setText(Html.fromHtml(String.format(Locale.ENGLISH,"<a href=\"%1$s\">%1$s</a>",url)));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
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
     * Return to previous activity
     * @param view Current view
     */
    public void onClickReturn (View view) {
        finish();
        super.onBackPressed();
    }

    public void createNewUrl (View view) {
        finish();
        startActivity(new Intent(this, CreateScheduleURLPopup.class));
    }

}
