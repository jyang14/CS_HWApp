package com.example.johnta.homeworkappv2.popup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.johnta.homeworkappv2.R;

public class RemoveItemFromListPopup extends Activity {

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_remove_item_from_list);

        Intent intentThing = getIntent();
        Bundle bundleThing = intentThing.getExtras();
        position = bundleThing.getInt("position");
        TextView text = (TextView)findViewById(R.id.popup_removeItemFromArray_textView1);
        text.setText("Please confirm that you would like to remove " + bundleThing.getString("thing"));
    }

    public void onClickDoStuff (View v) {
        Intent intentApple = new Intent();
        Bundle bundleOfBananas = new Bundle();
        bundleOfBananas.putBoolean("bananaCrate",v.getId()==R.id.popup_removeItemFromArray_remove);
        bundleOfBananas.putInt("position", position);
        intentApple.putExtras(bundleOfBananas);
        setResult(Activity.RESULT_OK,intentApple);
        finish();
    }

}
