package com.example.johnta.homeworkappv2.adapters;

/**
 * Created by johnta on 4/6/17.
 */

public class AssignmentStructure {

    private String classname;
    private String description;

    /**
     * Constructor that takes the user input and stores it into the instance variables as a new object
     * @param classname - name of the class
     * @param descriptionOfAssignment - description of the assignment
     */
    public AssignmentStructure(String classname, String descriptionOfAssignment) {
        this.classname = classname;
        description = descriptionOfAssignment;
    }

    /**
     * Empty Constructor
     */
    public AssignmentStructure() {

    }

    public void setClassname (String classname)
    {
        this.classname = classname;
    }

    public void setDescription (String descriptionOfAssignment)
    {
        description = descriptionOfAssignment;
    }

    public String getClassname ()
    {
        return classname;
    }

    public String getDescription ()
    {
        return description;
    }
    
}
