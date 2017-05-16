package com.example.johnta.homeworkappv2.firebase.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnta on 5/13/2017.
 */
public class Group {

    //Thanks to Jinchao for help with this

    /**
     * The namem of group.
     */
    public String name;
    /**
     * The UUID of group.
     */
    public long UUID;

    /**
     * The users of the group.
     */
    public List<String> users;
    /**
     * The group assignments.
     */
    public List<String> assignments;

    /**
     * Instantiates a new Group.
     *
     * @param name the name
     * @param UUID the uuid
     * @param user the user
     */
    public Group(String name, long UUID, User user){
        this.name = name;
        this.UUID = UUID;
        users = new ArrayList<>();
        users.add(user.hashEmail());
    }

    /**
     * Instantiates a new Group.
     *
     * Used by Firebase
     */
    public Group(){

    }

}
