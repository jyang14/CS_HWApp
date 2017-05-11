package com.example.johnta.homeworkappv2.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.popup.PlannerPopup_popup;

import java.util.ArrayList;

/**
 * Created by johnta on 4/6/17.
 */

public class AssignmentAdapter extends ArrayAdapter<AssignmentStruct> {

    Context context;

    public AssignmentAdapter(Context context, ArrayList<AssignmentStruct> listOfInformation) {
        super(context, 0, listOfInformation);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AssignmentStruct named = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.editlistitems, parent, false);
        }

        TextView name_of_class = (TextView) convertView.findViewById(R.id.name_of_class);
        TextView class_assignment = (TextView) convertView.findViewById(R.id.homework_description);

        name_of_class.setText(named.name_of_the_class);
        class_assignment.setText(named.description_class_assignment);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, PlannerPopup_popup.class));
                Log.i("PlannerActivity", "Item has been clicked!!!");
            }
        });

        return convertView;
    }
}
