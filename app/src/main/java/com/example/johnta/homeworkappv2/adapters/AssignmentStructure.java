package com.example.johnta.homeworkappv2.adapters;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by johnta on 4/6/17.
 */

public class AssignmentStructure {

    private String classname;
    private String description;

    /**
     * Constructor that takes the user input and stores it into the instance variables as a new object
     *
     * @param classname               - name of the class
     * @param descriptionOfAssignment - description of the assignment
     */
    public AssignmentStructure(String classname, String descriptionOfAssignment) {
        this.classname = classname;
        description = descriptionOfAssignment;
    }

    /**
     * Empty Constructor for Firebase
     */
    public AssignmentStructure() {

    }

    /**
     * Gets the name of the class
     *
     * @return
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
     * @return
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
     * StackOverflow tells me that SHA-256 collisions are pretty rare.
     * http://stackoverflow.com/questions/4014090/is-it-safe-to-ignore-the-possibility-of-sha-collisions-in-practice
     * Thanks to Jinchao for this
     * @return Returns the SHA-256 of the class
     */
    public String hash() {

        String output;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
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
