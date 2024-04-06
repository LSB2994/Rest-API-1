package org.kshrd.homework002.service.ServiceImp;

import org.kshrd.homework002.model.entity.Instructor;
import org.kshrd.homework002.model.request.InstructorRequest;
import org.kshrd.homework002.repository.InstructorRepository;
import org.kshrd.homework002.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImp implements InstructorService {
    private final InstructorRepository instructorRepository;

    public InstructorServiceImp(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> getAllInstructor(Integer page,Integer size) {
        return instructorRepository.getInstructor(page,size);
    }

    @Override
    public Instructor getInstructorById(Integer instructorId) {
        return instructorRepository.getInstructorById(instructorId);
    }

    @Override
    public boolean deleteInstructorById(Integer instructorId) {
        return instructorRepository.deleteInstructorId(instructorId);
    }

    @Override
    public Integer insertInstructor(InstructorRequest instructorRequest) {
        return instructorRepository.saveInstructor(instructorRequest);
    }

    @Override
    public Integer updateInstructorById(InstructorRequest instructorRequest, Integer instructorId) {
        return instructorRepository.updateInstructorById(instructorRequest,instructorId);
    }
}
