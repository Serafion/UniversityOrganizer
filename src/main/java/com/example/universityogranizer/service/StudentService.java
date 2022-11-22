package com.example.universityogranizer.service;

import com.example.universityogranizer.domain.Student;
import com.example.universityogranizer.domain.Teacher;
import com.example.universityogranizer.repositories.StudentRepository;
import com.example.universityogranizer.repositories.TeacherRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentService {

    private TeacherRepository teacherRepository;
    private StudentRepository studentRepository;

    public StudentService(TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    public Student saveNewTeacher(Student student){
        return studentRepository.save(student);
    }

    public Student updateTeacher(Student student){
        return studentRepository.save(student);
    }

    public Optional<Student> findTeacher(Student student){
        return studentRepository.findById(student.getId());
    }

    public Optional<Student> findTeacherById(Long id){
        return studentRepository.findById(id);
    }

    public Student addStudentToTeacher(Teacher teacher, Student student){
        teacher.getStudents().add(student);
        student.getTeachers().add(teacher);
        teacherRepository.save(teacher);
        return studentRepository.save(student);
    }

    public void deleteTeacher(Student student){
        studentRepository.delete(student);
    }

    public void deleteTeacherById(Long id){
        studentRepository.deleteById(id);
    }

    public Student findByFirstName(String firstName){
        return studentRepository.findByPersonName(firstName);
    }

    public Student findByLastName(String lastname){
        return studentRepository.findBySurname(lastname);
    }

    public Student findByFirstnameAndLastname(String firstName, String lastname){
        return studentRepository.findByPersonNameAndSurname(firstName,lastname);
    }


}
