package com.example.johnta.homeworkappv2.firebase;

import android.content.Context;
import android.content.Intent;

import com.example.johnta.homeworkappv2.firebase.handler.SignedInHandler;

/**
 * Created by jinch on 5/13/2017.
 */

interface AuthInterface {

    void signIn();

    void signOut();

    void signInOnIntentResult(int requestCode, Intent data, SignedInHandler signedInHandler);

    void setContext(Context context);

}
