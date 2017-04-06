package com.example.johnta.homeworkappv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by johnta on 4/6/17.
 */

public class UsersAdapter extends ArrayAdapter<String> {
    public UsersAdapter(Context context, ArrayList<String> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String object_thing = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.editlistitems, parent, false);
        }
        // Lookup view for data population
        TextView name_of_class = (TextView) convertView.findViewById(R.id.name_of_class);
        TextView class_assignment = (TextView) convertView.findViewById(R.id.class_assignment);
        // Populate the data into the template view using the data object
       // name_of_class.setText(object_thing.name);
        //class_assignment.setText(object_thing.hometown);
        // Return the completed view to render on screen
        return convertView;
    }
}
