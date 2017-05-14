package com.example.johnta.homeworkappv2.firebase.handler;

import com.example.johnta.homeworkappv2.adapters.AssignmentStructure;

import java.util.List;

/**
 * Created by jinch on 5/13/2017.
 */

public interface AssignmentHandler {
    void handleAssignments(List<AssignmentStructure> assignments);
}
