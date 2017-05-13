package com.example.johnta.homeworkappv2.firebase;

import android.util.Log;

import com.example.johnta.homeworkappv2.firebase.data.User;
import com.example.johnta.homeworkappv2.firebase.handler.SignedInHandler;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

//TODO Pretty up code
final class LoginListener implements ValueEventListener {

    private static final String TAG = "LOGIN";


    private final DataWrapper database;
    private final SignedInHandler signedInHandler;
    private final FirebaseUser firebaseUser;

    public LoginListener(DataWrapper database, FirebaseUser user, SignedInHandler signedInHandler) {
        this.database = database;
        this.signedInHandler = signedInHandler;
        firebaseUser = user;
    }

    @Override
    public void onDataChange(final DataSnapshot authSnapshot) {
        Log.w(TAG, "Starting process");

        User user = authSnapshot.getValue(User.class);

        if (user != null) {
            database.user = user;
            signedInHandler.onSignInSuccess();
        } else {
            user = new User(firebaseUser.getDisplayName(), firebaseUser.getEmail());
            database.user = user;
            database.updateUser();
            signedInHandler.onSignInSuccess();
        }
    }

    @Override
    public void onCancelled(DatabaseError error) {
        Log.w(TAG, "Failed to create get auth.", error.toException());

    }

}
