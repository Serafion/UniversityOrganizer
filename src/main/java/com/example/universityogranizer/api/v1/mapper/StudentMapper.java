package com.example.universityogranizer.api.v1.mapper;

import com.example.universityogranizer.studentclient.dto.StudentDTO;
import com.example.universityogranizer.domain.Student;

public interface StudentMapper {


    StudentDTO studentToStudentDTO (Student student);

    Student studentDTOtoStudent(StudentDTO studentDTO);
}
