package com.example.johnta.homeworkappv2.firebase;

import android.content.Context;
import android.widget.ListView;

import com.example.johnta.homeworkappv2.adapters.AssignmentAdapter;
import com.example.johnta.homeworkappv2.adapters.AssignmentStructure;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by johnta on 5/12/17.
 */

public class FirebaseWrapper {
    private static final FirebaseWrapper ourInstance = new FirebaseWrapper();

    public static FirebaseWrapper getInstance(Context activity) {

        return ourInstance;
    }

    private FirebaseWrapper() {



    }

    public void refreshLists(final ListView listView, DatabaseReference mDatabase, final AssignmentAdapter assignmentAdapter) {
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                AssignmentStructure assignment = dataSnapshot.getValue(AssignmentStructure.class);

                assignmentAdapter.add(assignment);
                //assignmentAdapter.notifyDataSetChanged();

                //assignmentAdapter.add(assignment);
                listView.setAdapter(assignmentAdapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}
