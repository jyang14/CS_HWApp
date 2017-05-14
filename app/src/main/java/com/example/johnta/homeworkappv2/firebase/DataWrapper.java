package com.example.johnta.homeworkappv2.firebase;

import android.util.Log;

import com.example.johnta.homeworkappv2.firebase.data.Assignment;
import com.example.johnta.homeworkappv2.firebase.data.Group;
import com.example.johnta.homeworkappv2.firebase.data.User;
import com.example.johnta.homeworkappv2.firebase.handler.AssignmentHandler;
import com.example.johnta.homeworkappv2.firebase.handler.GroupJoinedHandler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
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

    @Override
    public void addAssignmentToGroup(Assignment assignment) {
        addAssignmentToDatabase(assignment);

        if (group != null) {
            if (group.assignments == null)
                group.assignments = new ArrayList<>();
            group.assignments.add(assignment.hash());
            updateGroup();
        } else {
            Log.w(TAG, "ERROR GROUP NOT INITIALIZED.");
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
     * @param assignmentHandler
     */
    @Override
    public void onGroupChanges(final AssignmentHandler assignmentHandler) {
        groupsRef.child(String.valueOf(group.UUID)).child("assignments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<String>> genericTypeIndicator = new GenericTypeIndicator<List<String>>() {
                };
                List<String> results = dataSnapshot.getValue(genericTypeIndicator);
                if (results == null)
                    results = new ArrayList<>();

                results.removeAll(Collections.singleton(null));
                group.assignments = results;
                getAssignments(results, assignmentHandler);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void createGroup(final String name, final GroupJoinedHandler groupJoinedHandler) {
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
                updateUser();

                groupJoinedHandler.handleGroupJoined(true);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "ERROR: UUID generator could not be accessed");
                groupJoinedHandler.handleGroupJoined(false);
            }
        });
    }

    @Override
    public void joinGroup(final long uuid, final GroupJoinedHandler groupJoinedHandler) {
        groupsRef.child(String.valueOf(uuid)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Group group = dataSnapshot.getValue(Group.class);

                if (group != null) {
                    DataWrapper.this.group = group;
                    user.group = uuid;
                    updateUser();
                    groupJoinedHandler.handleGroupJoined(true);
                } else {
                    groupJoinedHandler.handleGroupJoined(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "ERROR: Could not access group");
                groupJoinedHandler.handleGroupJoined(false);
            }
        });
    }

    @Override
    public void copyGroupToUser() {
        if (user == null || group == null) {
            Log.w(TAG, "INVALID STATE NULL USER OR GROUP");
            return;
        }

        if (user.assignments == null)
            user.assignments = new ArrayList<>();

        if (group.assignments == null)
            group.assignments = new ArrayList<>();

        user.assignments.addAll(group.assignments);
        updateUser();
    }

    @Override
    public void copyUserToGroup() {
        if (user == null || group == null) {
            Log.w(TAG, "INVALID STATE NULL USER OR GROUP");
            return;
        }
        if (user.assignments == null)
            user.assignments = new ArrayList<>();

        if (group.assignments == null)
            group.assignments = new ArrayList<>();

        group.assignments.addAll(user.assignments);
        updateGroup();
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
    public void getAssignments(final List<String> hashes, final AssignmentHandler assignmentHandler) {

        if (hashes == null) {
            Log.v(TAG, "No Assignments. Enjoy your free time."); // Let me be salty
            return;
        } else if (assignmentHandler == null) {
            Log.w(TAG, "ERROR NO HANDLER FOR ASSIGNMENTS");
            return;
        }


        final List<Assignment> temp = new ArrayList<>();
        if (hashes.size() == 0) {
            assignmentHandler.handleAssignments(temp);
            return;
        }
        for (String hash : hashes) {

            assignmentRef.child(hash).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Assignment assignment = dataSnapshot.getValue(Assignment.class);
                    temp.add(assignment);

                    if (temp.size() == hashes.size()) {
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
