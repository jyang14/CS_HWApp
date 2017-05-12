package com.example.johnta.homeworkappv2.firebase;

import android.content.Context;

/**
 * Created by johnta on 5/12/17.
 */

class FirebaseWrapper {
    private static final FirebaseWrapper ourInstance = new FirebaseWrapper();

    static FirebaseWrapper getInstance(Context activity) {



        return ourInstance;
    }

    private FirebaseWrapper() {



    }
}
