package com.example.johnta.homeworkappv2.firebase;

import android.content.Context;
import android.content.Intent;
import android.widget.ListView;

import com.example.johnta.homeworkappv2.adapters.AssignmentAdapter;
import com.example.johnta.homeworkappv2.adapters.AssignmentStructure;
import com.example.johnta.homeworkappv2.firebase.data.User;
import com.example.johnta.homeworkappv2.firebase.handler.SignedInHandler;

/**
 * Created by johnta on 5/12/17.
 */

public class FirebaseWrapper implements DataInterface, AuthInterface {

    private static FirebaseWrapper instance;

    private final DataWrapper dataWrapper;
    private final AuthWrapper authWrapper;

    /**
     * Private constructor
     */
    private FirebaseWrapper(Context context) {
        dataWrapper = new DataWrapper();
        authWrapper = new AuthWrapper(context, new AuthListener(dataWrapper));
    }

    /**
     *
     * @param activity
     * @return
     */
    public static FirebaseWrapper getInstance(Context activity) {
        if (instance == null)
            instance = new FirebaseWrapper(context);
        instance.setContext(context);
        return instance;
    }

    /**
     * Takes the two strings of user input and calls the addItemToArray method
     * @param className name of the class name of class
     * @param assignmentName description of assignment  description of assignment
     */
    @Override
    public void addItemToArray(String className, String assignmentName) {
        dataWrapper.addItemToArray(className, assignmentName);
    }

    /**
     * Method that takes the object of assignmentStructure and calls removeItem()
     * @param assignmentStructure assignment
     */
    @Override
    public void removeItem(AssignmentStructure assignmentStructure) {
        dataWrapper.removeItem(assignmentStructure);
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
}
