package com.example.johnta.homeworkappv2.firebase;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ListView;

import com.example.johnta.homeworkappv2.adapters.AssignmentAdapter;
import com.example.johnta.homeworkappv2.adapters.AssignmentStructure;
import com.example.johnta.homeworkappv2.firebase.data.User;
import com.example.johnta.homeworkappv2.firebase.handler.AssignmentHandler;
import com.example.johnta.homeworkappv2.firebase.handler.SignedInHandler;
import com.google.firebase.database.ChildEventListener;

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
     *
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
     * @param assignment the assignment to be added
     */
    @Override
    public void addAssignmentToDatabase(AssignmentStructure assignment) {
        dataWrapper.addAssignmentToDatabase(assignment);
    }

    /**
     * Adds assignment to user's list of assignments
     *
     * @param assignment the assignment to be added
     */
    @Override
    public void addAssignmentToUser(AssignmentStructure assignment) {
        dataWrapper.addAssignmentToUser(assignment);
    }

    /**
     * Removes the assignment from the database
     *
     * @deprecated DO NOT CALL
     * @param assignment assignment
     */
    @Override
    public void removeItem(AssignmentStructure assignment) {
        dataWrapper.removeItem(assignment);
    }

    /**
     * Method that takes the listViw and its adapter and calls refreshLists() method
     * @param listView listViw of the original activity
     * @param assignmentAdapter the listView's adapter
     */
    @Override
    public void refreshLists(ListView listView, AssignmentAdapter assignmentAdapter) {
        dataWrapper.refreshLists(listView, assignmentAdapter);
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
    public void updateUser() {
        dataWrapper.updateUser();
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
    public void addUserAssignmentListener(ChildEventListener listener) {
        dataWrapper.addUserAssignmentListener(listener);
    }
}
