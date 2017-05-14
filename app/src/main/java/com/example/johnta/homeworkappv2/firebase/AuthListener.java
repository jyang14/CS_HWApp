package com.example.johnta.homeworkappv2.firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.johnta.homeworkappv2.firebase.handler.SignedInHandler;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jinch on 5/11/2017.
 */
class AuthListener implements FirebaseAuth.AuthStateListener {

    private static final String TAG = "AUTHLISTENER";

    private DataWrapper dataWrapper;
    private SignedInHandler signedInHandler;

    AuthListener(DataWrapper dataWrapper) {
        this.dataWrapper = dataWrapper;
    }

    /**
     * Gets the MD5 hash of the email for indexing
     * Assumes that one has an email and cannot change their email address for a given account
     *
     * @param email email address of the user
     * @return hash of the email
     */
    private String hashEmail(String email) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(email.getBytes());
            BigInteger bigInt = new BigInteger(1, hash);
            return bigInt.toString(16);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

    void setSignedInHandler(SignedInHandler signedInHandler) {
        this.signedInHandler = signedInHandler;
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) { // User is signed in

            Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
            String hash = hashEmail(user.getEmail());
            if (hash != null)
                dataWrapper.firebaseDatabase.getReference("Users").child(hash).addListenerForSingleValueEvent(new LoginListener(dataWrapper, user, signedInHandler));
            else
                Log.w(TAG, "hashing was invalid");

        } else {  // User is signed out
            Log.d(TAG, "onAuthStateChanged:signed_out");
        }
    }
}
