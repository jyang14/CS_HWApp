package com.example.johnta.homeworkappv2.firebase;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.johnta.homeworkappv2.firebase.data.Assignment;
import com.example.johnta.homeworkappv2.firebase.data.Group;
import com.example.johnta.homeworkappv2.firebase.data.User;
import com.example.johnta.homeworkappv2.firebase.handler.AssignmentHandler;
import com.example.johnta.homeworkappv2.firebase.handler.GroupJoinedHandler;
import com.example.johnta.homeworkappv2.firebase.handler.SignedInHandler;

import java.util.List;

/**
 * Created by johnta on 5/12/17.
 */

public class FirebaseWrapper implements DataInterface, AuthInterface {

    private static final String TAG = "FIREBASEWRAPPER";

    private static FirebaseWrapper instance;

    private final DataWrapper dataWrapper;
    private final AuthWrapper authWrapper;

    /**
     * Private constructor
     */
    private FirebaseWrapper(Context context) {
        Log.v(TAG, "constructor");
        dataWrapper = new DataWrapper();
        authWrapper = new AuthWrapper(context, new AuthListener(dataWrapper));
    }

    /**
     * @param context
     * @return
     */
    public static FirebaseWrapper getInstance(Context context) {
        if (instance == null)
            instance = new FirebaseWrapper(context);
        instance.setContext(context);
        return instance;
    }

    /**
     * Adds item to the listView upon completion of user input
     *
     * @param assignment the assignment to be added
     */
    @Override
    public void addAssignmentToDatabase(Assignment assignment) {
        dataWrapper.addAssignmentToDatabase(assignment);
    }

    /**
     * Adds assignment to user's list of assignments
     *
     * @param assignment the assignment to be added
     */
    @Override
    public void addAssignmentToUser(Assignment assignment) {
        dataWrapper.addAssignmentToUser(assignment);
    }

    @Override
    public void addAssignmentToGroup(Assignment assignment) {
        dataWrapper.addAssignmentToGroup(assignment);
    }

    /**
     * Removes the assignment from the database
     *
     * @param assignment assignment
     * @deprecated DO NOT CALL
     */
    @Override
    public void removeItem(Assignment assignment) {
        dataWrapper.removeItem(assignment);
    }

    @Override
    public void removeAssignmentFromUser(Assignment assignment) {
        dataWrapper.removeAssignmentFromUser(assignment);
    }

    @Override
    public void removeAssignmentFromGroup(Assignment assignment) {
        dataWrapper.removeAssignmentFromGroup(assignment);
    }

    /**
     * Method that takes the listViw and its adapter and calls onGroupChanges() method
     *
     * @param assignmentHandler
     */
    @Override
    public void onGroupChanges(AssignmentHandler assignmentHandler) {
        dataWrapper.onGroupChanges(assignmentHandler);
    }

    @Override
    public void createGroup(String name, GroupJoinedHandler groupJoinedHandler) {
        dataWrapper.createGroup(name, groupJoinedHandler);
    }

    @Override
    public void joinGroup(long uuid, GroupJoinedHandler groupJoinedHandler) {
        dataWrapper.joinGroup(uuid, groupJoinedHandler);
    }

    @Override
    public void copyGroupToUser() {
        dataWrapper.copyGroupToUser();
    }

    @Override
    public void copyUserToGroup() {
        dataWrapper.copyUserToGroup();
    }

    @Override
    public void signIn() {
        authWrapper.signIn();
    }

    @Override
    public void signOut() {
        authWrapper.signOut();
    }

    @Override
    public void signInOnIntentResult(int requestCode, Intent data, SignedInHandler signedInHandler) {
        authWrapper.signInOnIntentResult(requestCode, data, signedInHandler);
    }

    @Override
    public void setContext(Context context) {
        authWrapper.setContext(context);
    }

    @Override
    public User getUser() {
        return dataWrapper.getUser();
    }

    @Override
    public Group getGroup() {
        return dataWrapper.getGroup();
    }

    @Override
    public void updateUser() {
        dataWrapper.updateUser();
    }

    @Override
    public void updateGroup() {
        dataWrapper.updateGroup();
    }

    @Override
    public void getAssignments(List<String> hashes, AssignmentHandler assignmentHandler) {
        dataWrapper.getAssignments(hashes, assignmentHandler);
    }

    @Override
    public void getUserAssignments(AssignmentHandler assignmentHandler) {
        dataWrapper.getUserAssignments(assignmentHandler);
    }

    @Override
    public void getGroupAssignments(AssignmentHandler assignmentHandler) {
        dataWrapper.getGroupAssignments(assignmentHandler);
    }

    @Override
    public void setUrl(String url) {
        dataWrapper.setUrl(url);
    }

    @Override
    public String getUrl() {
        return dataWrapper.getUrl();
    }

}
