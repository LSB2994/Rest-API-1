package org.kshrd.homework002.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kshrd.homework002.model.entity.Courses;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentRequest {
    private String studentName;
    private String email;
    private String phoneNumber;
    List<Integer> courses = new ArrayList<>();
}
