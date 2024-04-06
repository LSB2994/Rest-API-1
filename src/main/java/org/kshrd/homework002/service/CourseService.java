package org.kshrd.homework002.service;

import org.kshrd.homework002.model.entity.Courses;
import org.kshrd.homework002.model.request.CourseRequest;
import org.kshrd.homework002.model.response.CourseResponse;

import java.util.List;

public interface CourseService {
    List<Courses> getAllCourses(Integer page,Integer size);
    Courses getCourseById(Integer courseId);
    boolean deleteCourseById(Integer courseId);
    Integer postCourse(CourseRequest courseRequest);
    Integer updateCourseById(CourseRequest courseRequest, Integer courseId);
}
