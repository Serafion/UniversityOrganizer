package com.example.universityogranizer.studentservice;

import com.example.universityogranizer.domain.Student;
import com.example.universityogranizer.domain.Teacher;
import com.example.universityogranizer.studentservice.dto.StudentDTO;
import com.example.universityogranizer.teacherservice.dto.TeacherDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class StudentServiceFacade {

    StudentDao studentDao;

    StudentMapperImpl studentMapper;

    public StudentServiceFacade(StudentDao studentDao, StudentMapperImpl studentMapper) {
        this.studentDao = studentDao;
        this.studentMapper = studentMapper;
    }

//    public Student saveNewStudent(Student student) {
//        return studentDao.saveNewStudent(student);
//    }

    public StudentDTO findStudentById(Long id) {
        return studentDao.findStudentById(id);
    }

    public Student addTeacherToStudent(Long idS, Teacher teacher) {
        return studentDao.addTeacherToStudent(idS, teacher);
    }

    public void deleteStudentById(Long id) {
        studentDao.deleteStudentById(id);
    }

    public List<StudentDTO> getAllStudents() {
        return studentDao.getAllStudents();
    }

    public StudentDTO createNewStudent(StudentDTO studentDTO) {

        return studentDao.createNewStudent(studentDTO);
    }

    public StudentDTO saveStudentByDTO(Long id, StudentDTO studentDTO) {
        return studentDao.saveStudentByDTO(id, studentDTO);
    }

    public Page<StudentDTO> findAllPage(Pageable pageable) {
        return studentDao.findAllPage(pageable);
    }

    public List<StudentDTO> findAllSorted(Sort sort) {
        return studentDao.findAllSorted(sort);

    }

    public void deleteTeacherFromStudent(Long idS, Teacher teacher) {
        studentDao.deleteTeacherFromStudent(idS,teacher);
    }

    public List<TeacherDTO> getTeachers(Long id) {
        return studentDao.getTeachers(id);
    }

    public List<StudentDTO> getStudents(String firstname, String lastname) {
        return studentDao.getStudent(firstname, lastname);
    }

    public Student findStudentDataById(Long idS) {
        return studentDao.findStudentDataById(idS);
    }
}
