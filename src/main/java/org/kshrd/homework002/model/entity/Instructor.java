package org.kshrd.homework002.model.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Instructor {
    private Integer instructorId;
    private String instructorName;
    private String instructEmail;
}
