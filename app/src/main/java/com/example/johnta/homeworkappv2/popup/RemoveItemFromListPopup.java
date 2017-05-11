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

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = bundle.getInt("position");
        TextView text = (TextView) findViewById(R.id.popup_removeItemFromArray_textView1);
        text.setText("Please confirm that you would like to remove " + bundle.getString("thing"));
    }

    public void onClickDoStuff(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putBoolean("delete", v.getId() == R.id.popup_removeItemFromArray_remove);
        bundle.putInt("position", position);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
