package org.kshrd.homework002.model.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Student {
    private Integer studentId;
    private String studentName;
    private String studentEmail;
    private String phoneNumber;
    private List<Courses> course = new ArrayList<>();
}
