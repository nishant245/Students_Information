package com.nishant.accounts.service.impl;


import com.nishant.accounts.dto.StudentsDto;
import com.nishant.accounts.entity.Students;
import com.nishant.accounts.exception.ResourceNotFoundException;
import com.nishant.accounts.exception.StudentAlreadyExistsException;
import com.nishant.accounts.mapper.StudentsMapper;
import com.nishant.accounts.repo.StudentsRepo;
import com.nishant.accounts.service.IStudentsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//@AllArgsConstructor
public class StudentsServiceImpl implements IStudentsService {

    @Autowired
    private StudentsRepo studentsRepo;

    public StudentsServiceImpl(StudentsRepo studentsRepo) {
        this.studentsRepo = studentsRepo;
    }

    @Override
    public void createAccount(StudentsDto studentsDto) {
        Students student = StudentsMapper.mapToStudents(studentsDto, new Students());
        Optional<Students> optionalStudents = studentsRepo.findByMobileNumber(studentsDto.getMobileNumber());
        if(optionalStudents.isPresent()) {
            throw new StudentAlreadyExistsException("student already registered with given mobileNumber "
                    +studentsDto.getMobileNumber());
        }
        Students savedStudent= studentsRepo.save(student);
    }

    @Override
    public StudentsDto fetchAccount(String mobileNumber) {
        Students student = studentsRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Student", "mobileNumber", mobileNumber)
        );
        StudentsDto studentsDto=  StudentsMapper.mapToStudentsDto(student, new StudentsDto());

        return studentsDto;
    }

    @Override
    public boolean updateStudent(StudentsDto studentsDto) {
        boolean isUpdated = false;
        if (studentsDto != null) {
            Students students = studentsRepo.findById(studentsDto.getMobileNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Student", "MobileNumber", studentsDto.getMobileNumber().toString())
            );
            StudentsMapper.mapToStudents(studentsDto, students);
            studentsRepo.save(students);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteStudents(String mobileNumber) {
        Students students= studentsRepo.findById(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Student", "mobileNumber", mobileNumber)
        );
        studentsRepo.deleteById(students.getMobileNumber());

        return true;
    }
}
