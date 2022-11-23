package com.example.universityogranizer.api.v1.model;

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
