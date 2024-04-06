package org.kshrd.homework002.model.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Courses {
    private Integer courseId;
    private String courseName;
    private String description;
    private Instructor instructor;
}
