package org.kshrd.homework002.controller;

import org.kshrd.homework002.model.entity.Courses;
import org.kshrd.homework002.model.entity.Instructor;
import org.kshrd.homework002.model.entity.Student;
import org.kshrd.homework002.model.request.StudentRequest;
import org.kshrd.homework002.model.response.CourseResponse;
import org.kshrd.homework002.model.response.InstructorResponse;
import org.kshrd.homework002.model.response.StudentResponse;
import org.kshrd.homework002.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //get all students
    @GetMapping()
    public ResponseEntity<StudentResponse<List<Student>>> getStudents(@RequestParam(name = "page",defaultValue = "1") Integer page,
                                                                      @RequestParam(name = "size",defaultValue = "5") Integer size) {
        StudentResponse<List<Student>> response = StudentResponse.<List<Student>>builder()
                .message("get data instructor success")
                .payload(studentService.getAllStudent(page, size))
                .httpStatus(HttpStatus.OK)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return ResponseEntity.ok(response);
    }

    //get student by id
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse<Student>> getStudentById(@PathVariable("id") Integer studentId) {
        StudentResponse<Student> response = null;
        if (studentService.getStudentById(studentId) != null) {
            response = StudentResponse.<Student>builder()
                    .message("get student by id is successfully")
                    .payload(studentService.getStudentById(studentId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        } else {
            response = StudentResponse.<Student>builder()
                    .message("get student by id is not successfully")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(response);
    }
    //post student
    @PostMapping()
    public ResponseEntity<?> postStudent(StudentRequest studentRequest) {
        Integer studentId = studentService.postStudent(studentRequest);
        if (studentId != null) {
            return ResponseEntity.ok(studentService.getStudentById(studentId));
        }
        return null;
    }

    //update student
    @PutMapping("{id}")
    public ResponseEntity<StudentResponse<Student>> updateStudent(@RequestBody StudentRequest studentRequest,
                                                                  @PathVariable("id") Integer studentId){
        Integer updateId = studentService.updateStudentById(studentRequest,studentId);
        StudentResponse<Student> response = null;
        if (updateId!=null){
            response = StudentResponse.<Student>builder()
                    .message("Update student is successfully")
                    .payload(studentService.getStudentById(updateId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }else {
            response = StudentResponse.<Student>builder()
                    .message("Update student is not successfully")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(response);
    }

    //delete student
    @DeleteMapping("{id}")
    public ResponseEntity<StudentResponse<Student>> deleteStudentById(@PathVariable("id") Integer studentId){
        StudentResponse<Student> response =null;
        if (studentService.deleteStudentById(studentId)){
            response = StudentResponse.<Student>builder()
                    .message("Delete student by id is successfully")
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }else {
            response = StudentResponse.<Student>builder()
                    .message("Delete student by id is not successfully")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(response);
    }
}
