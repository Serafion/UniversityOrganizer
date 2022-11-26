package com.example.universityogranizer.controllers.v1;

import com.example.universityogranizer.studentservice.exceptions.StudentNotFoundException;
import com.example.universityogranizer.teacherservice.exceptions.TeacherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UniversityOrganizerExceptionHandler {

    @ExceptionHandler({TeacherNotFoundException.class,
                        StudentNotFoundException.class})
    public ResponseEntity<String> f(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
