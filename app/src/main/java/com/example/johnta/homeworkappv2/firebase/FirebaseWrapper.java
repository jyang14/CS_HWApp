package com.example.johnta.homeworkappv2.firebase;

import android.content.Context;
import android.widget.ListView;

import com.example.johnta.homeworkappv2.adapters.AssignmentAdapter;
import com.example.johnta.homeworkappv2.adapters.AssignmentStructure;

/**
 * Created by johnta on 5/12/17.
 */

public class FirebaseWrapper implements DataInterface, AuthInterface {

    private static FirebaseWrapper instance;

    private final DataWrapper dataWrapper;

    private FirebaseWrapper() {
        dataWrapper = new DataWrapper();

    }

    public static FirebaseWrapper getInstance(Context activity) {
        if (instance == null)
            instance = new FirebaseWrapper();
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
}
