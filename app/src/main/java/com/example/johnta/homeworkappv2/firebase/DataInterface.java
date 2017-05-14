package com.example.johnta.homeworkappv2.firebase;

import android.widget.ListView;

import com.example.johnta.homeworkappv2.adapters.AssignmentAdapter;
import com.example.johnta.homeworkappv2.firebase.data.Assignment;
import com.example.johnta.homeworkappv2.firebase.data.Group;
import com.example.johnta.homeworkappv2.firebase.data.User;
import com.example.johnta.homeworkappv2.firebase.handler.AssignmentHandler;

import java.util.List;

/**
 * Created by jinch on 5/13/2017.
 */

interface DataInterface {

    /**
     * Adds assignment to Firebase Real-time Database
     * @param assignment the assignment to be added
     */
    void addAssignmentToDatabase(Assignment assignment);


    /**
     * Adds assignment to user's list of assignments
     * @param assignment the assignment to be added
     */
    void addAssignmentToUser(Assignment assignment);

    /**
     * Removes the assignment from the database
     *
     * @deprecated DO NOT CALL
     * @param assignment assignment
     */
    void removeItem(Assignment assignment);

    void removeAssignmentFromUser(Assignment assignment);

    void removeAssignmentFromGroup(Assignment assignment);

    void refreshLists(ListView listView, AssignmentAdapter assignmentAdapter);

    void createGroup(String name);

    User getUser();

    Group getGroup();

    void updateUser();

    void updateGroup();

    void getAssignments(List<String> hashes, AssignmentHandler assignmentHandler);

    void getUserAssignments(AssignmentHandler assignmentHandler);

    void getGroupAssignments(AssignmentHandler assignmentHandler);
}
