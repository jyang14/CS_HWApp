package com.example.johnta.homeworkappv2.firebase;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.johnta.homeworkappv2.firebase.handler.SignedInHandler;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Created by jinch on 5/13/2017.
 */

class AuthWrapper implements AuthInterface, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "FirebaseAuth";
    private final int RC_SIGN_IN = 9001;
    private final GoogleApiClient mGoogleApiClient;
    private final FirebaseAuth firebaseAuth;
    private final AuthListener authListener;

    private Context context;

    AuthWrapper(final Context context, AuthListener authListener) {
        firebaseAuth = FirebaseAuth.getInstance();

        this.context = context;
        this.authListener = authListener;

        firebaseAuth.addAuthStateListener(authListener);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken("514323657877-ivrum85hhh940t24ot1mmu074qdk89q2.apps.googleusercontent.com").requestEmail().build();
        mGoogleApiClient = new GoogleApiClient.Builder(context).enableAutoManage(((AppCompatActivity) context) /* FragmentActivity */, this /* OnConnectionFailedListener */).addApi(com.google.android.gms.auth.api.Auth.GOOGLE_SIGN_IN_API, gso).build();
        mGoogleApiClient.connect();
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential);
    }

    @Override
    public void signIn() {
        Log.d(TAG, "Starting Intent");
        Intent signInIntent = com.google.android.gms.auth.api.Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        ((AppCompatActivity) context).startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    public void signOut() {
        //TODO
        if (mGoogleApiClient.isConnected()) {
            com.google.android.gms.auth.api.Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            if (firebaseAuth != null) {
                                // Firebase sign out
                                firebaseAuth.signOut();
                                firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
                                    @Override
                                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                                        if (firebaseAuth.getCurrentUser() == null)
//                                            signedOutHandler.onSignOut();
                                    }
                                });

                            }
                        }
                    });
        } else {
            mGoogleApiClient.connect();
        }

    }

    @Override
    public void signInOnIntentResult(int requestCode, Intent data, SignedInHandler signedInHandler) {
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = com.google.android.gms.auth.api.Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.v(TAG, String.valueOf(result.isSuccess()));
            Log.v(TAG, result.getStatus().toString());
            if (result.isSuccess()) {
                firebaseAuthWithGoogle(result.getSignInAccount());
                authListener.setSignedInHandler(signedInHandler);
            }
        }
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.w(TAG, "onConnectionFailed:" + connectionResult);
    }
}
