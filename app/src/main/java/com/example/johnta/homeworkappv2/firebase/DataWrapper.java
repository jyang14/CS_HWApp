package com.example.johnta.homeworkappv2.firebase;

import android.widget.ListView;

import com.example.johnta.homeworkappv2.adapters.AssignmentAdapter;
import com.example.johnta.homeworkappv2.adapters.AssignmentStructure;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by jinch on 5/13/2017.
 */

class DataWrapper implements DataInterface {

    FirebaseDatabase firebaseDatabase;

    DataWrapper() {
        firebaseDatabase = FirebaseDatabase.getInstance();

    }


    @Override
    public void addItemToArray(String className, String assignmentName) {
        DatabaseReference mDatabase = firebaseDatabase.getReference();

        AssignmentStructure assignment = new AssignmentStructure(className, assignmentName);
        //assignmentAdapter.add(assignment);

        mDatabase.child("Assignment").push().setValue(assignment);

        //assignmentAdapter.notifyDataSetChanged();
        //Log.v(TAG, assignmentAdapter.toString());
    }

    @Override
    public void refreshLists(final ListView listView, final AssignmentAdapter assignmentAdapter) {
        DatabaseReference assignmentRef = firebaseDatabase.getReference("Assignment");
        assignmentRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                AssignmentStructure assignment = dataSnapshot.getValue(AssignmentStructure.class);

                assignmentAdapter.add(assignment);
                //assignmentAdapter.notifyDataSetChanged();

                //assignmentAdapter.add(assignment);
                listView.setAdapter(assignmentAdapter);
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
}
