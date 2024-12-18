package com.nishant.accounts.mapper;

import com.nishant.accounts.dto.StudentsDto;
import com.nishant.accounts.entity.Students;

public class StudentsMapper {
    public static StudentsDto mapToStudentsDto(Students students, StudentsDto studentsDto){
        studentsDto.setEmail(students.getEmail());
        studentsDto.setName(students.getName());
        studentsDto.setMobileNumber(students.getMobileNumber());

        return studentsDto;
    }

    public static Students mapToStudents(StudentsDto studentsDto, Students students){
        students.setEmail(studentsDto.getEmail());
        students.setName(studentsDto.getName());
        students.setMobileNumber(studentsDto.getMobileNumber());
        return students;
    }
}
