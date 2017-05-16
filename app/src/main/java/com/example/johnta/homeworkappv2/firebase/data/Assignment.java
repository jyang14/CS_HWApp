package com.example.johnta.homeworkappv2.firebase.data;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by johnta on 4/6/17.
 */
public class Assignment {

    private String classname;
    private String description;

    /**
     * Constructor that takes the user input and stores it into the instance variables as a new object
     *
     * @param classname               - name of the class
     * @param descriptionOfAssignment - description of the assignment
     */
    public Assignment(String classname, String descriptionOfAssignment) {
        this.classname = classname;
        description = descriptionOfAssignment;
    }

    /**
     * Empty Constructor for Firebase
     */
    public Assignment() {

    }

    /**
     * Gets the name of the class
     *
     * @return classname
     */
    public String getClassname() {
        return classname;
    }

    /**
     * Setter for name of class
     *
     * @param classname name of the class to be set
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }

    /**
     * Gets the description of the assignment
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description of the assignment
     *
     * @param descriptionOfAssignment description of the assignment
     */
    public void setDescription(String descriptionOfAssignment) {
        description = descriptionOfAssignment;
    }

    /**
     * Hashing function that returns string instead of int.
     * According to the birthday paradox I am safe as long there will be less than 2^60 entries
     *
     * @return Returns the MD5 of the class
     */
    public String hash() {

        String output;

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(classname.getBytes());
            digest.update((byte)0);
            byte[] hash = digest.digest(description.getBytes());
            BigInteger bigInt = new BigInteger(1, hash);
            output = bigInt.toString(16);

            return output;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "N"+String.format("%X", System.nanoTime());
        }

    }

}
