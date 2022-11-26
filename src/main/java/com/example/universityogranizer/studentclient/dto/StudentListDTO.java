package com.example.universityogranizer.studentclient.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StudentListDTO {

    List<StudentDTO> students;

    public StudentListDTO(List<StudentDTO> students) {
        this.students = students;
    }
}
