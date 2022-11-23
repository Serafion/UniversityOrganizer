package com.example.universityogranizer.api.v1.mapper;

import com.example.universityogranizer.api.v1.model.StudentDTO;
import com.example.universityogranizer.domain.Student;

public interface StudentMapper {


    StudentDTO studentToStudentDTO (Student student);

    Student studentDTOtoStudent(StudentDTO studentDTO);
}
