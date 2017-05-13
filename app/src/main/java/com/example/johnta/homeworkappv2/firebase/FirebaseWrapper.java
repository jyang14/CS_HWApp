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

    private FirebaseWrapper(Context context) {
        dataWrapper = new DataWrapper();
        authWrapper = new AuthWrapper(context, new AuthListener(dataWrapper));
    }

    public static FirebaseWrapper getInstance(Context context) {
        if (instance == null)
            instance = new FirebaseWrapper(context);
        instance.setContext(context);
        return instance;
    }

    @Override
    public void addItemToArray(String className, String assignmentName) {
        dataWrapper.addItemToArray(className, assignmentName);
    }

    @Override
    public void removeItem(AssignmentStructure assignmentStructure) {
        dataWrapper.removeItem(assignmentStructure);
    }

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
