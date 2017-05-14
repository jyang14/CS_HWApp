package com.example.johnta.homeworkappv2.firebase;

import android.widget.ListView;

import com.example.johnta.homeworkappv2.adapters.AssignmentAdapter;
import com.example.johnta.homeworkappv2.adapters.AssignmentStructure;
import com.example.johnta.homeworkappv2.firebase.data.User;
import com.example.johnta.homeworkappv2.firebase.handler.AssignmentHandler;
import com.google.firebase.database.ChildEventListener;

import java.util.List;

/**
 * Created by jinch on 5/13/2017.
 */

interface DataInterface {

    /**
     * Adds assignment to Firebase Real-time Database
     * @param assignment the assignment to be added
     */
    void addAssignmentToDatabase(AssignmentStructure assignment);


    /**
     * Adds assignment to user's list of assignments
     * @param assignment the assignment to be added
     */
    void addAssignmentToUser(AssignmentStructure assignment);

    /**
     * Removes the assignment from the database
     *
     * @deprecated DO NOT CALL
     * @param assignmentStructure assignment
     */
    void removeItem(AssignmentStructure assignmentStructure);

    void refreshLists(ListView listView, AssignmentAdapter assignmentAdapter);

    User getUser();

    void updateUser();

    void getAssignments(List<String> hashes, AssignmentHandler assignmentHandler);

    void getUserAssignments(AssignmentHandler assignmentHandler);

    void addUserAssignmentListener(ChildEventListener listener);
}
