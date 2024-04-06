package org.kshrd.homework002.service;

import org.kshrd.homework002.model.entity.Instructor;
import org.kshrd.homework002.model.request.InstructorRequest;

import java.util.List;

public interface InstructorService {
    List<Instructor> getAllInstructor(Integer page,Integer size);
    Instructor getInstructorById(Integer instructorId);
    boolean deleteInstructorById(Integer instructorId);
    Integer insertInstructor(InstructorRequest instructorRequest);
    Integer updateInstructorById(InstructorRequest instructorRequest,Integer instructorId);
}
