package com.nishant.accounts.controller;

import com.nishant.accounts.constants.StudentsConstant;
import com.nishant.accounts.dto.ResponseDto;
import com.nishant.accounts.dto.StudentsDto;
import com.nishant.accounts.service.IStudentsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
//@AllArgsConstructor
@Validated
public class StudentsController {

    @Autowired
    private IStudentsService iStudentsService;

    public StudentsController(IStudentsService iStudentsService) {
        this.iStudentsService = iStudentsService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody StudentsDto studentsDto){
        iStudentsService.createAccount(studentsDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(StudentsConstant.STATUS_201, StudentsConstant.MESSAGE_201));

    }
    @GetMapping("/fetch")
    public ResponseEntity<StudentsDto> fetchStudentDetails(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber){
        StudentsDto studentsDto= iStudentsService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentsDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateStudentDetails(@Valid @RequestBody StudentsDto studentsDto){
        boolean isUpdated= iStudentsService.updateStudent(studentsDto);
        if(isUpdated)
        {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(StudentsConstant.STATUS_200, StudentsConstant.MESSAGE_200));
        }
        else
        {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(StudentsConstant.STATUS_417, StudentsConstant.MESSAGE_417_UPDATE));
        }

    }
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteStudentDetails(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber ){
        boolean isDeleted =iStudentsService.deleteStudents(mobileNumber);
        if(isDeleted)
        {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(StudentsConstant.STATUS_200, StudentsConstant.MESSAGE_200));
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body( new ResponseDto(StudentsConstant.STATUS_417, StudentsConstant.MESSAGE_417_DELETE));
        }
    }

}
