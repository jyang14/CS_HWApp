package com.example.johnta.homeworkappv2.firebase;

import android.util.Log;
import android.widget.ListView;

import com.example.johnta.homeworkappv2.adapters.AssignmentAdapter;
import com.example.johnta.homeworkappv2.adapters.AssignmentStructure;
import com.example.johnta.homeworkappv2.firebase.data.User;
import com.example.johnta.homeworkappv2.firebase.handler.AssignmentHandler;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jinch on 5/13/2017.
 */

class DataWrapper implements DataInterface {

    static private final String TAG = "DATAWRAPPER";
    FirebaseDatabase firebaseDatabase;
    User user;
    private DatabaseReference assignmentRef;

    DataWrapper() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        assignmentRef = firebaseDatabase.getReference("Assignments");
    }

    /**
     * Adds item to the listView upon completion of user input
     *
     * @param assignment the assignment to be added
     */
    @Override
    public void addAssignmentToDatabase(AssignmentStructure assignment) {
        assignmentRef.child(assignment.hash()).setValue(assignment);
    }

    /**
     * Adds assignment to user's list of assignments
     *
     * @param assignment the assignment to be added
     */
    @Override
    public void addAssignmentToUser(AssignmentStructure assignment) {
        // Just in case others may want to upload the same assignment
        // Got to build up that hash map
        addAssignmentToDatabase(assignment);

        if (user != null) {
            if(user.assignments == null)
                user.assignments = new ArrayList<>();
            user.assignments.add(assignment.hash());
            updateUser();
        } else {
            Log.w(TAG, "ERROR USER NOT INITIALIZED.");
        }
    }

    /**
     * Removes the assignment from the database
     *
     * @param assignmentStructure assignment
     * @deprecated DO NOT CALL
     */
    @Override
    public void removeItem(AssignmentStructure assignmentStructure) {
        DatabaseReference assignmentRef = this.assignmentRef;
        assignmentRef.child(assignmentStructure.hash()).removeValue();
    }

    /**
     * Reloads the adapter upon onCreate()
     *
     * @param listView          the activitiy's listView
     * @param assignmentAdapter the listView's adapter
     */
    @Override
    public void refreshLists(final ListView listView, final AssignmentAdapter assignmentAdapter) {
        assignmentRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                AssignmentStructure assignment = dataSnapshot.getValue(AssignmentStructure.class);

                assignmentAdapter.add(assignment);
                assignmentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void updateUser() {
        if (user == null) {
            Log.w(TAG, "INVALID STATE NULL USER");
            return;
        }

        firebaseDatabase.getReference("Users").child(user.hashEmail()).setValue(user);
    }

    @Override
    public void getAssignments(List<String> hashes, final AssignmentHandler assignmentHandler) {

        if (hashes == null) {
            Log.v(TAG, "No Assignments. Enjoy your free time."); // Let me be salty
            return;
        } else if (assignmentHandler == null) {
            Log.w(TAG, "ERROR NO HANDLER FOR ASSIGNMENTS");
            return;
        }

        final List<AssignmentStructure> temp = new ArrayList<>();

        for (String hash : hashes) {

            assignmentRef.child(hash).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    AssignmentStructure assignment = dataSnapshot.getValue(AssignmentStructure.class);
                    temp.add(assignment);

                    if (temp.size() == user.assignments.size()) {
                        temp.removeAll(Collections.singleton(null));
                        assignmentHandler.handleAssignments(temp);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w(TAG, "ERROR: Animal not retrieved");
                }
            });

        }
    }

    @Override
    public void getUserAssignments(AssignmentHandler assignmentHandler) {
        if (user == null) {
            Log.w(TAG, "ERROR USER NOT INITIALIZED.");
            return;
        }

        getAssignments(user.assignments, assignmentHandler);
    }

    @Override
    public void addUserAssignmentListener(ChildEventListener listener) {
        //TODO
    }
}
