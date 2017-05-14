package com.example.johnta.homeworkappv2.firebase.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnta on 5/13/2017.
 */

public class Group {

    //Thanks to Jinchao for help with this

    public String name;
    public long UUID;

    public List<String> users;
    public List<String> assignments;

    public Group(String name, long UUID, User user){
        this.name = name;
        this.UUID = UUID;
        users = new ArrayList<>();
        users.add(user.hashEmail());
    }

    public Group(){

    }

}
