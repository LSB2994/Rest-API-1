package org.kshrd.homework002.model.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudentCourses {
    private Integer studentId;
    private Integer courseId;
}
