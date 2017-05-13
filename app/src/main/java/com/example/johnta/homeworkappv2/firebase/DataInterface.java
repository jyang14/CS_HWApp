package com.example.johnta.homeworkappv2.firebase;

import android.widget.ListView;

import com.example.johnta.homeworkappv2.adapters.AssignmentAdapter;
import com.example.johnta.homeworkappv2.adapters.AssignmentStructure;

/**
 * Created by jinch on 5/13/2017.
 */

interface DataInterface {

    /**
     * Adds item to the listView upon completion of user input
     * @param className name of the class
     * @param assignmentName description of assignment
     */
    void addItemToArray(String className, String assignmentName);


    /**
     * Removes the assignment from the database
     * @param assignmentStructure assignment
     */
    void removeItem(AssignmentStructure assignmentStructure);

    void refreshLists(ListView listView, AssignmentAdapter assignmentAdapter);
}
