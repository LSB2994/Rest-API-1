package org.kshrd.homework002.service.ServiceImp;

import org.kshrd.homework002.model.entity.Student;
import org.kshrd.homework002.model.request.StudentRequest;
import org.kshrd.homework002.repository.StudentRepository;
import org.kshrd.homework002.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImp(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudent(Integer page, Integer size) {
        return studentRepository.getStudent(page,size);
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return studentRepository.getStudentById(studentId);
    }

    @Override
    public Integer postStudent(StudentRequest studentRequest) {
        Integer studentId = studentRepository.saveStudent(studentRequest);
        if (studentId!=null){
            for (Integer courseId:studentRequest.getCourses()){
                studentRepository.saveCourseStudentId(studentId,courseId);;
            }
        }
        return studentId;
    }

    @Override
    public Integer updateStudentById(StudentRequest studentRequest, Integer studentId) {
        return studentRepository.updateStudent(studentRequest,studentId);
    }

    @Override
    public boolean deleteStudentById(Integer studentId) {
        return studentRepository.deleteStudent(studentId);
    }
}
