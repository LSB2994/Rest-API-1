package org.kshrd.homework002.model.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseRequest {
    private String courseName;
    private String description;
    private Integer instructorId;
}
