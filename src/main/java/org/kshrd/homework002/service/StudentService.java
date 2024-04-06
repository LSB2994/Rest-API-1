package org.kshrd.homework002.service;

import org.kshrd.homework002.model.entity.Student;
import org.kshrd.homework002.model.request.StudentRequest;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudent(Integer page,Integer size);
    Student getStudentById(Integer studentId);
    Integer postStudent(StudentRequest studentRequest);

    Integer updateStudentById(StudentRequest studentRequest,Integer studentId);
    boolean deleteStudentById(Integer studentId);
}
