package com.example.johnta.homeworkappv2.firebase;

import android.content.Context;
import android.content.Intent;

import com.example.johnta.homeworkappv2.firebase.handler.SignedInHandler;

/**
 * Created by jinch on 5/13/2017.
 */
interface AuthInterface {

    /**
     * Creates intent for sign in.
     */
    void signIn();

    /**
     * Signs out.
     * TODO
     */
    void signOut();

    /**
     * Sign in on intent result.
     *
     * @param requestCode     the request code
     * @param data            the data
     * @param signedInHandler the signed in handler
     */
    void signInOnIntentResult(int requestCode, Intent data, SignedInHandler signedInHandler);

    /**
     * Sets context.
     *
     * @param context the context
     */
    void setContext(Context context);

}
