package com.example.johnta.homeworkappv2.firebase.data;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by johnta on 5/13/2017.
 */

public class User {

    public String name;
    public String email;
    public List<String> assignments;
    public long group;

    public String url;

    /**
     * Gets the MD5 hash of the email for indexing
     * Assumes that one has an email and cannot change their email address for a given account
     * Thanks Jinchao for this
     * @return hash of the email
     */
    public String hashEmail(){
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

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    /**
     * Empty constructor for Firebase
     */
    public User(){}
}
