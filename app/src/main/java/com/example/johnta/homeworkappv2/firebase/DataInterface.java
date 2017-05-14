package com.example.johnta.homeworkappv2.firebase;

import com.example.johnta.homeworkappv2.firebase.data.Assignment;
import com.example.johnta.homeworkappv2.firebase.data.Group;
import com.example.johnta.homeworkappv2.firebase.data.User;
import com.example.johnta.homeworkappv2.firebase.handler.AssignmentHandler;
import com.example.johnta.homeworkappv2.firebase.handler.GroupJoinedHandler;

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

    void addAssignmentToGroup(Assignment assignment);

    /**
     * Removes the assignment from the database
     *
     * @deprecated DO NOT CALL
     * @param assignment assignment
     */
    void removeItem(Assignment assignment);

    void removeAssignmentFromUser(Assignment assignment);

    void removeAssignmentFromGroup(Assignment assignment);

    void onGroupChanges(AssignmentHandler assignmentHandler);

    void createGroup(String name, GroupJoinedHandler groupJoinedHandler);

    void joinGroup(long uuid, GroupJoinedHandler groupJoinedHandler);

    void copyGroupToUser();

    void copyUserToGroup();

    User getUser();

    Group getGroup();

    void updateUser();

    void updateGroup();

    void getAssignments(List<String> hashes, AssignmentHandler assignmentHandler);

    void getUserAssignments(AssignmentHandler assignmentHandler);

    void getGroupAssignments(AssignmentHandler assignmentHandler);
}
