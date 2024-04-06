package org.kshrd.homework002.service.ServiceImp;

import org.kshrd.homework002.model.entity.Courses;
import org.kshrd.homework002.model.request.CourseRequest;
import org.kshrd.homework002.repository.CourseRepository;
import org.kshrd.homework002.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImp implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImp(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Courses> getAllCourses(Integer page, Integer size) {
        return courseRepository.getCourse(page,size);
    }

    @Override
    public Courses getCourseById(Integer courseId) {
        return courseRepository.getCourseById(courseId);
    }

    @Override
    public boolean deleteCourseById(Integer courseId) {
        return courseRepository.deleteCourseById(courseId);
    }

    @Override
    public Integer postCourse(CourseRequest courseRequest) {
        return courseRepository.insertCourse(courseRequest);
    }

    @Override
    public Integer updateCourseById(CourseRequest courseRequest, Integer courseId) {
        return courseRepository.updateCourse(courseRequest,courseId);
    }

}
