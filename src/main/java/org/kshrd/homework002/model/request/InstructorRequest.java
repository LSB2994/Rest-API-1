package org.kshrd.homework002.model.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InstructorRequest {
    private String instructorName;
    private String email;
}
