package com.example.johnta.homeworkappv2.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.popup.RemoveItemFromListPopup;

import java.util.ArrayList;

/**
 * Created by johnta on 4/6/17.
 */

public class AssignmentAdapter extends ArrayAdapter<AssignmentStructure> {

    Activity context;

    public AssignmentAdapter(Activity context, ArrayList<AssignmentStructure> listOfInformation) {
        super(context, 0, listOfInformation);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final AssignmentStructure named = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.backup_editlistitems, parent, false);
        }

        TextView name_of_class = (TextView) convertView.findViewById(R.id.name_of_class);
        TextView class_assignment = (TextView) convertView.findViewById(R.id.homework_description);

        name_of_class.setText(named.name_of_the_class);
        class_assignment.setText(named.description_class_assignment);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentThing = new Intent(context, RemoveItemFromListPopup.class);

                Bundle bundleThing = new Bundle();
                bundleThing.putString("thing",named.description_class_assignment);
                bundleThing.putInt("position",position);
                intentThing.putExtras(bundleThing);

                //context.startActivity(intentThing);

                context.startActivityForResult(intentThing,123);


                Log.i("PlannerActivity", "Item has been clicked!!!");
            }
        });

        return convertView;
    }
}
