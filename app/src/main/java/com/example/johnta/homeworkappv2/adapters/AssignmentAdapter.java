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

        TextView nameOfClass = (TextView) convertView.findViewById(R.id.name_of_class);
        TextView homeworkDescription = (TextView) convertView.findViewById(R.id.homework_description);

        nameOfClass.setText(named.name_of_the_class);
        homeworkDescription.setText(named.description_class_assignment);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, RemoveItemFromListPopup.class);

                Bundle bundle = new Bundle();
                bundle.putString("thing", named.description_class_assignment);
                bundle.putInt("position", position);
                intent.putExtras(bundle);

                //context.startActivity(intent);

                context.startActivityForResult(intent, 123);


                Log.i("PlannerActivity", "Item has been clicked!!!");
            }
        });

        return convertView;
    }
}
