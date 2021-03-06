package com.example.johnta.homeworkappv2.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.johnta.homeworkappv2.R;
import com.example.johnta.homeworkappv2.firebase.data.Assignment;
import com.example.johnta.homeworkappv2.popup.CopyAssignmentPlannerToHubPopup;
import com.example.johnta.homeworkappv2.popup.RemoveItemFromListPopup;

import java.util.ArrayList;

/**
 * Created by johnta on 4/6/17.
 */

public class AssignmentAdapter extends ArrayAdapter<Assignment> {

    private static final String TAG = "ASSIGNMENTADAPTER";

    Activity context;

    public AssignmentAdapter(Activity context, ArrayList<Assignment> listOfInformation) {
        super(context, 0, listOfInformation);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Assignment assignment = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.backend_listview_items_planner, parent, false);
        }

        TextView nameOfClass = (TextView) convertView.findViewById(R.id.name_of_class);
        TextView homeworkDescription = (TextView) convertView.findViewById(R.id.homework_description);

        ImageButton subtractButton = (ImageButton)convertView.findViewById(R.id.backend_listViewItemsPlanner_subtract);
        subtractButton.setImageResource(R.drawable.subtract_icon);

        ImageButton transferButton = (ImageButton)convertView.findViewById(R.id.backend_listViewItemsPlanner_transfer);
        transferButton.setImageResource(R.drawable.transfer_icon);

        nameOfClass.setText(assignment.getClassname());
        homeworkDescription.setText(assignment.getDescription());
       // subtractButton.set

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, RemoveItemFromListPopup.class);

                Bundle bundle = new Bundle();
                bundle.putString("thing", assignment.getDescription());
                bundle.putString("classname",assignment.getClassname());
                bundle.putInt("position", position);
                intent.putExtras(bundle);

                context.startActivityForResult(intent, 123);
            }
        });

        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (context, CopyAssignmentPlannerToHubPopup.class);

                Bundle bundle = new Bundle();
                bundle.putString("thing", assignment.getDescription());
                bundle.putString("classname",assignment.getClassname());
                bundle.putInt("position", position);
                intent.putExtras(bundle);

                context.startActivityForResult(intent, 987);

                /*
                if(context instanceof PlannerActivity){
                    FirebaseWrapper.getInstance(context).addAssignmentToGroup(assignment);
                }else if (context instanceof CloudActivity){
                    FirebaseWrapper.getInstance(context).addAssignmentToUser(assignment);
                }
                */
            }
        });


        return convertView;
    }
}
