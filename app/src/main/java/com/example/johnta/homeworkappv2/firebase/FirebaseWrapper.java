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

    /**
     * Private constructor
     */
    private FirebaseWrapper() {
        dataWrapper = new DataWrapper();
    }

    /**
     *
     * @param activity
     * @return
     */
    public static FirebaseWrapper getInstance(Context activity) {
        if (instance == null)
            instance = new FirebaseWrapper();
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
}
