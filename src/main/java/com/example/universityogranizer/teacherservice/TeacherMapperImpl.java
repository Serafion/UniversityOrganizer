package com.example.universityogranizer.teacherservice;

import com.example.universityogranizer.api.v1.mapper.StudentMapperImpl;
import com.example.universityogranizer.api.v1.model.StudentDTO;
import com.example.universityogranizer.teacherservice.dto.TeacherDTO;
import com.example.universityogranizer.domain.Student;
import com.example.universityogranizer.domain.Teacher;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;


@Component
public class TeacherMapperImpl {


    public TeacherDTO teacherToTeacherDTO(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherDTO teacherDTO = new TeacherDTO();

        teacherDTO.setAge(teacher.getAge());
        teacherDTO.setEmail(teacher.getEmail());
        teacherDTO.setSubject(teacher.getSubject());
        teacherDTO.setFirstname(teacher.getPersonName());
        teacherDTO.setLastname(teacher.getSurname());
        teacherDTO.setStudents(getStudents(teacher));
        return teacherDTO;

    }


    public Teacher teacherDTOToTeacher(TeacherDTO teacherDTO) {
        if ( teacherDTO == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setAge(teacherDTO.getAge());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setSubject(teacherDTO.getSubject());
        teacher.setPersonName(teacherDTO.getFirstname());
        teacher.setSurname(teacherDTO.getLastname());
        teacher.setStudents(getStudents(teacherDTO));
        return teacher;
    }

    private Set<StudentDTO> getStudents(Teacher teacher){
        return teacher.getStudents().stream().map(new StudentMapperImpl()::studentToStudentDTO).collect(Collectors.toSet());
    }

    private Set<Student> getStudents(TeacherDTO teacherDTO){
        return teacherDTO.getStudents().stream().map(new StudentMapperImpl()::studentDTOtoStudent).collect(Collectors.toSet());
    }


}
