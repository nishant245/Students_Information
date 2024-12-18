package com.nishant.accounts.service;

import com.nishant.accounts.dto.StudentsDto;

public interface IStudentsService {
    void createAccount(StudentsDto studentsDto);

    StudentsDto fetchAccount(String mobileNumber);

    boolean updateStudent(StudentsDto studentsDto);
    boolean deleteStudents(String mobileNumber);
}
