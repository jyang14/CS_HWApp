package com.example.johnta.homeworkappv2.firebase.handler;

import com.example.johnta.homeworkappv2.firebase.data.Assignment;

import java.util.List;

/**
 * Created by jinch on 5/13/2017.
 */
public interface AssignmentHandler {
    /**
     * Handle assignments.
     *
     * @param assignments the assignments
     */
    void handleAssignments(List<Assignment> assignments);
}
