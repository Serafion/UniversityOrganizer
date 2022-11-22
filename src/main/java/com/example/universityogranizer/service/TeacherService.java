package com.example.universityogranizer.service;

import com.example.universityogranizer.domain.Student;
import com.example.universityogranizer.domain.Teacher;
import com.example.universityogranizer.repositories.StudentRepository;
import com.example.universityogranizer.repositories.TeacherRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TeacherService {

    private TeacherRepository teacherRepository;
    private StudentRepository studentRepository;

    public TeacherService(TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    public Teacher saveNewTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }

    public Optional<Teacher> findTeacher(Teacher teacher){
        return teacherRepository.findById(teacher.getId());
    }

    public Optional<Teacher> findTeacherById(Long id){
        return teacherRepository.findById(id);
    }

    public Teacher addStudentToTeacher(Teacher teacher, Student student){
        teacher.getStudents().add(student);
        student.getTeachers().add(teacher);
        studentRepository.save(student);
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Teacher teacher){
        teacherRepository.delete(teacher);
    }

    public void deleteTeacherById(Long id){
        teacherRepository.deleteById(id);
    }

    public Teacher findByFirstName(String firstName){
        return teacherRepository.findByPersonName(firstName);
    }

    public Teacher findByLastName(String lastname){
        return teacherRepository.findBySurname(lastname);
    }

    public Teacher findByFirstnameAndLastname(String firstName, String lastname){
        return teacherRepository.findByPersonNameAndSurname(firstName,lastname);
    }
}
