package com.example.universityogranizer.api.v1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeacherListDTO {

    List<TeacherDTO> teachers;

    public TeacherListDTO(List<TeacherDTO> teachers) {
        this.teachers = teachers;
    }
}
