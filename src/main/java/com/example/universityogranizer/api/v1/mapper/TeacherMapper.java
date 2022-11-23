package com.example.universityogranizer.api.v1.mapper;

import com.example.universityogranizer.api.v1.model.TeacherDTO;
import com.example.universityogranizer.domain.Teacher;


public interface TeacherMapper {


    TeacherDTO teacherToTeacherDTO (Teacher teacher);

    Teacher teacherDTOToTeacher(TeacherDTO teacherDTO);
}