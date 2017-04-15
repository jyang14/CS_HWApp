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

public class ArraysIntoOne_backend extends ArrayAdapter<NameAssignments_backend> {
    public ArraysIntoOne_backend(Context context, ArrayList<NameAssignments_backend> listOfInformation) {
        super(context, 0, listOfInformation);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        NameAssignments_backend named = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.editlistitems, parent, false);
        }

        TextView name_of_class = (TextView) convertView.findViewById(R.id.name_of_class);
        TextView class_assignment = (TextView) convertView.findViewById(R.id.homework_description);

        name_of_class.setText(named.name_of_the_class);
        class_assignment.setText(named.description_class_assignment);

        return convertView;
    }
}