package org.kshrd.homework002.controller;

import org.kshrd.homework002.model.entity.Instructor;
import org.kshrd.homework002.model.request.InstructorRequest;
import org.kshrd.homework002.model.response.InstructorResponse;
import org.kshrd.homework002.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    //get all instructor by set page and size
    @GetMapping
    public ResponseEntity<InstructorResponse<List<Instructor>>> getAllInstructor(@RequestParam(name = "page",defaultValue = "1") Integer page,
                                                                                 @RequestParam(name = "size",defaultValue = "5") Integer size){
        InstructorResponse<List<Instructor>> response = InstructorResponse.<List<Instructor>>builder()
                .message("get data instructor success")
                .payload(instructorService.getAllInstructor(page,size))
                .httpStatus(HttpStatus.OK)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return ResponseEntity.ok(response);
    }

    //get instructor by id
    @GetMapping("/{id}")
    public ResponseEntity<InstructorResponse<Instructor>> getInstructorById(@PathVariable("id") Integer instructorId){
        InstructorResponse<Instructor> response = null;
        if (instructorService.getInstructorById(instructorId) != null){
            response = InstructorResponse.<Instructor>builder()
                    .message("get instructor by id is successfully")
                    .payload(instructorService.getInstructorById(instructorId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }else {
            response = InstructorResponse.<Instructor>builder()
                    .message("get instructor by id is not successfully")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();

            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(response);
    }

    //Delete object instructor by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<InstructorResponse<Instructor>> deleteInstructor(@PathVariable("id") Integer instructorId){
        InstructorResponse<Instructor> response =null;
        if (instructorService.deleteInstructorById(instructorId)){
            response = InstructorResponse.<Instructor>builder()
                    .message("Delete instructor by id successfully")
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }else {
            response = InstructorResponse.<Instructor>builder()
                    .message("Delete instructor id is not successfully")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(response);
    }

    //inset new instructor
    @PostMapping()
    public ResponseEntity<InstructorResponse<Instructor>> postNewInstructor(@RequestBody InstructorRequest instructorRequest){
        InstructorResponse<Instructor> response  = null;
        Integer insertId = instructorService.insertInstructor(instructorRequest);
        if (insertId!=null){
            response = InstructorResponse.<Instructor>builder()
                    .message("post instructor is successfully")
                    .payload(instructorService.getInstructorById(insertId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }else {
            response = InstructorResponse.<Instructor>builder()
                    .message("post instructor is not successfully")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(response);
    }

    //update instructor
    @PutMapping("/{id}")
    public ResponseEntity<InstructorResponse<Instructor>> updateInstructor(@RequestBody InstructorRequest instructorRequest,
                                                                           @PathVariable("id") Integer instructorId){
        Integer updateId = instructorService.updateInstructorById(instructorRequest,instructorId);
        InstructorResponse<Instructor> response = null;
        if (updateId!=null){
            response = InstructorResponse.<Instructor>builder()
                    .message("Update instructor is successfully")
                    .payload(instructorService.getInstructorById(updateId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }else {
            response = InstructorResponse.<Instructor>builder()
                    .message("Update instructor is not successfully")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(response);
    }
}
