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
     *
     * @param assignment the assignment to be added
     */
    void addAssignmentToDatabase(Assignment assignment);


    /**
     * Adds assignment to user's list of assignments
     *
     * @param assignment the assignment to be added
     */
    void addAssignmentToUser(Assignment assignment);

    /**
     * Add assignment to group.
     *
     * @param assignment the assignment
     */
    void addAssignmentToGroup(Assignment assignment);

    /**
     * Removes the assignment from the database
     *
     * @param assignment assignment
     * @deprecated DO NOT CALL
     */
    void removeItem(Assignment assignment);

    /**
     * Remove assignment from user.
     *
     * @param assignment the assignment
     */
    void removeAssignmentFromUser(Assignment assignment);

    /**
     * Remove assignment from group.
     *
     * @param assignment the assignment
     */
    void removeAssignmentFromGroup(Assignment assignment);

    /**
     * On group changes.
     *
     * @param assignmentHandler the assignment handler
     */
    void onGroupChanges(AssignmentHandler assignmentHandler);

    /**
     * Create group.
     *
     * @param name               the name
     * @param groupJoinedHandler the group joined handler
     */
    void createGroup(String name, GroupJoinedHandler groupJoinedHandler);

    /**
     * Join group.
     *
     * @param uuid               the uuid
     * @param groupJoinedHandler the group joined handler
     */
    void joinGroup(long uuid, GroupJoinedHandler groupJoinedHandler);

    /**
     * Copy group to user.
     */
    void copyGroupToUser();

    /**
     * Copy user to group.
     */
    void copyUserToGroup();

    /**
     * Gets user.
     *
     * @return the user
     */
    User getUser();

    /**
     * Gets group.
     *
     * @return the group
     */
    Group getGroup();

    /**
     * Update user.
     */
    void updateUser();

    /**
     * Update group.
     */
    void updateGroup();

    /**
     * Gets assignments.
     *
     * @param hashes            the hashes
     * @param assignmentHandler the assignment handler
     */
    void getAssignments(List<String> hashes, AssignmentHandler assignmentHandler);

    /**
     * Gets user assignments.
     *
     * @param assignmentHandler the assignment handler
     */
    void getUserAssignments(AssignmentHandler assignmentHandler);

    /**
     * Gets group assignments.
     *
     * @param assignmentHandler the assignment handler
     */
    void getGroupAssignments(AssignmentHandler assignmentHandler);

    /**
     * Sets url.
     *
     * @param url the url
     */
    void setUrl(String url);

    /**
     * Gets url.
     *
     * @return the url
     */
    String getUrl();
}
