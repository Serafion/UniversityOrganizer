package com.example.universityogranizer.studentservice;

import com.example.universityogranizer.domain.Student;
import com.example.universityogranizer.domain.Teacher;
import com.example.universityogranizer.studentservice.dto.StudentDTO;
import com.example.universityogranizer.teacherservice.TeacherMapperImpl;
import com.example.universityogranizer.teacherservice.dto.TeacherDTO;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StudentMapperImpl {


    public StudentDTO studentToStudentDTO(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setAge(student.getAge());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setFaculty(student.getFaculty());
        studentDTO.setFirstname(student.getPersonName());
        studentDTO.setLastname(student.getSurname());
        studentDTO.setTeachers(getTeachers(student));
        return studentDTO;
    }


    public Student studentDTOtoStudent(StudentDTO studentDTO) {
        if ( studentDTO == null ) {
            return null;
        }

        Student student = new Student();

        student.setAge(studentDTO.getAge());
        student.setEmail(studentDTO.getEmail());
        student.setFaculty(studentDTO.getFaculty());
        student.setPersonName(studentDTO.getFirstname());
        student.setSurname(studentDTO.getLastname());
        student.setTeachers(getTeachers(studentDTO));

        return student;
    }

    private Set<TeacherDTO> getTeachers(Student student){
        return student.getTeachers().stream().map(new TeacherMapperImpl()::teacherToTeacherDTO).collect(Collectors.toSet());
    }

    private Set<Teacher> getTeachers(StudentDTO studentDTO){
        return studentDTO.getTeachers().stream().map(new TeacherMapperImpl()::teacherDTOToTeacher).collect(Collectors.toSet());
    }
}
