package org.kshrd.homework002.controller;

import org.kshrd.homework002.model.entity.Courses;
import org.kshrd.homework002.model.entity.Instructor;
import org.kshrd.homework002.model.request.CourseRequest;
import org.kshrd.homework002.model.response.CourseResponse;
import org.kshrd.homework002.model.response.InstructorResponse;
import org.kshrd.homework002.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/vi/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //get all courses
    @GetMapping("all")
    public ResponseEntity<CourseResponse<List<Courses>>> getAllCourses(@RequestParam(name = "page",defaultValue = "1") Integer page,
                                                                       @RequestParam(name = "size",defaultValue = "5") Integer size){
        CourseResponse<List<Courses>> response = CourseResponse.<List<Courses>>builder()
                .message("get book is successfully")
                .payload(courseService.getAllCourses(page,size))
                .httpStatus(HttpStatus.OK)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return ResponseEntity.ok(response);
    }

    //get course by id
    @GetMapping("{id}")
    public ResponseEntity<CourseResponse<Courses>> getCourseById(@PathVariable("id") Integer courseId){
        CourseResponse<Courses> response = null;
        if (courseService.getCourseById(courseId) != null){
            response = CourseResponse.<Courses>builder()
                    .message("get course by id is successfully")
                    .payload(courseService.getCourseById(courseId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }else {
            response = CourseResponse.<Courses>builder()
                    .message("get course by id is not successfully")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();

            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(response);
    }

    //Delete object course by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CourseResponse<Courses>> deleteCourseById(@PathVariable("id") Integer courseId){
        CourseResponse<Courses> response =null;
        if (courseService.deleteCourseById(courseId)){
            response = CourseResponse.<Courses>builder()
                    .message("Delete instructor by id successfully")
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }else {
            response = CourseResponse.<Courses>builder()
                    .message("Delete course id is not successfully")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(response);
    }

    //post course
    @PostMapping()
    public ResponseEntity<CourseResponse<Courses>> postCourse(@RequestBody CourseRequest courseRequest){
        CourseResponse<Courses> response = null;
        Integer insertId = courseService.postCourse(courseRequest);
        if(insertId !=null){
            response = CourseResponse.<Courses>builder()
                    .message("post course is successfully")
                    .payload(courseService.getCourseById(insertId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }else {
            response = CourseResponse.<Courses>builder()
                    .message("post course is not successfully")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(response);
    }

    // update course
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse<Courses>> updateCourse(@RequestBody CourseRequest courseRequest,
                                                                @PathVariable("id") Integer courseId){
        Integer updateId = courseService.updateCourseById(courseRequest, courseId);
        CourseResponse<Courses> response = null;
        if (updateId!=null){
            response = CourseResponse.<Courses>builder()
                    .message("Update course is successfully")
                    .payload(courseService.getCourseById(updateId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }else {
            response = CourseResponse.<Courses>builder()
                    .message("Update course is not successfully")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(response);
    }
}
