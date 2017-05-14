package com.example.johnta.homeworkappv2.firebase;

import android.util.Log;
import android.widget.ListView;

import com.example.johnta.homeworkappv2.adapters.AssignmentAdapter;
import com.example.johnta.homeworkappv2.firebase.data.Assignment;
import com.example.johnta.homeworkappv2.firebase.data.Group;
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

    final DatabaseReference assignmentRef;
    final DatabaseReference usersRef;
    final DatabaseReference groupsRef;
    final DatabaseReference uuidRef;


    FirebaseDatabase firebaseDatabase;
    User user;
    Group group;

    DataWrapper() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        assignmentRef = firebaseDatabase.getReference("Assignments");
        usersRef = firebaseDatabase.getReference("Users");
        groupsRef = firebaseDatabase.getReference("Groups");
        uuidRef = firebaseDatabase.getReference("UUID");
    }

    /**
     * Adds item to the listView upon completion of user input
     *
     * @param assignment the assignment to be added
     */
    @Override
    public void addAssignmentToDatabase(Assignment assignment) {
        assignmentRef.child(assignment.hash()).setValue(assignment);
    }

    /**
     * Adds assignment to user's list of assignments
     *
     * @param assignment the assignment to be added
     */
    @Override
    public void addAssignmentToUser(Assignment assignment) {
        // Just in case others may want to upload the same assignment
        // Got to build up that hash map
        addAssignmentToDatabase(assignment);

        if (user != null) {
            if (user.assignments == null)
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
     * @param assignment assignment
     * @deprecated DO NOT CALL
     */
    @Override
    public void removeItem(Assignment assignment) {
        DatabaseReference assignmentRef = this.assignmentRef;
        assignmentRef.child(assignment.hash()).removeValue();
    }

    @Override
    public void removeAssignmentFromUser(Assignment assignment) {
        if (user != null && user.assignments != null) {
            user.assignments.remove(assignment.hash());
            updateUser();
        }
    }

    @Override
    public void removeAssignmentFromGroup(Assignment assignment) {
        if (group != null && group.assignments != null) {
            group.assignments.remove(assignment.hash());
            updateGroup();
        }
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
                Assignment assignment = dataSnapshot.getValue(Assignment.class);

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
    public void createGroup(final String name) {
        if (user == null) {
            Log.w(TAG, "ERROR USER NOT INITIALIZED.");
            return;
        }
        if (name == null) {
            Log.w(TAG, "ERROR NAME NOT INITIALIZED.");
            return;
        }
        uuidRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long p = dataSnapshot.child("p").getValue(Long.class); // Prime number
                long g = dataSnapshot.child("g").getValue(Long.class); // Number coprime with p
                long n = dataSnapshot.child("n").getValue(Long.class); // UUID

                n = (g * n) % p; // Max number of groups is 99990
                dataSnapshot.child("n").getRef().setValue(n);
                n += 100000 * (user.hashCode() % 10); // Just in case there is a data race

                user.group = n;
                group = new Group(name, n, user);
                updateGroup();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "ERROR: UUID generator could not be accessed");
            }
        });
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public Group getGroup() {
        return group;
    }

    @Override
    public void updateUser() {
        if (user == null) {
            Log.w(TAG, "INVALID STATE NULL USER");
            return;
        }

        usersRef.child(user.hashEmail()).setValue(user);
    }

    @Override
    public void updateGroup() {
        if (group == null) {
            Log.w(TAG, "INVALID STATE NULL GROUP");
            return;
        }

        groupsRef.child(String.valueOf(group.UUID)).setValue(group);
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

        final List<Assignment> temp = new ArrayList<>();

        for (String hash : hashes) {

            assignmentRef.child(hash).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Assignment assignment = dataSnapshot.getValue(Assignment.class);
                    temp.add(assignment);

                    if (temp.size() == user.assignments.size()) {
                        temp.removeAll(Collections.singleton(null));
                        assignmentHandler.handleAssignments(temp);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w(TAG, "ERROR: Assignment not retrieved");
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
    public void getGroupAssignments(AssignmentHandler assignmentHandler) {
        if (group == null) {
            Log.w(TAG, "ERROR GROUP NOT INITIALIZED.");
            return;
        }

        getAssignments(group.assignments, assignmentHandler);
    }

}
