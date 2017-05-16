package com.example.johnta.homeworkappv2.firebase.data;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by johnta on 5/13/2017.
 */
public class User {

    /**
     * The name of user.
     */
    public String name;
    /**
     * The email of the user.
     */
    public String email;
    /**
     * The user assignmentss.
     */
    public List<String> assignments;
    /**
     * The group of the user.
     */
    public long group;

    /**
     * The url of the user.
     */
    public String url;

    /**
     * Gets the MD5 hash of the email for indexing
     * Assumes that one has an email and cannot change their email address for a given account
     * Thanks Jinchao for this
     *
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

    /**
     * Instantiates a new User.
     *
     * @param name  the name
     * @param email the email
     */
    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    /**
     * Empty constructor for Firebase
     */
    public User(){}
}
