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

public class Arrays_Into_One extends ArrayAdapter<String> {
    public Arrays_Into_One(Context context, ArrayList<String> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.editlistitems, parent, false);
        }

        TextView name_of_class = (TextView) convertView.findViewById(R.id.name_of_class);
        TextView class_assignment = (TextView) convertView.findViewById(R.id.class_assignment);

        return convertView;
    }
}
