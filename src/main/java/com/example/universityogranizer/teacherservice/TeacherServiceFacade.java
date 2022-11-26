package com.example.universityogranizer.teacherservice;

import com.example.universityogranizer.domain.Student;
import com.example.universityogranizer.domain.Teacher;
import com.example.universityogranizer.studentclient.dto.StudentDTO;
import com.example.universityogranizer.teacherservice.dto.TeacherDTO;
import com.example.universityogranizer.teacherservice.repository.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class TeacherServiceFacade {

    TeacherDao teacherDao;
    TeacherMapperImpl teacherMapper;
    TeacherRepository teacherRepository;

    public TeacherServiceFacade(TeacherDao teacherDao, TeacherMapperImpl teacherMapper, TeacherRepository teacherRepository) {
        this.teacherDao = teacherDao;
        this.teacherMapper = teacherMapper;
        this.teacherRepository = teacherRepository;
    }

    public Teacher saveNewTeacher(Teacher teacher) {
        return teacherDao.saveNewTeacher(teacher);
    }

    public TeacherDTO findTeacherById(Long id) {
        return teacherDao.findTeacherById(id);
    }

    public Teacher addStudentToTeacher(Long idT, Student student) {
        return teacherDao.addStudentToTeacher(idT, student);
    }

    public void deleteTeacherById(Long id) {
        teacherDao.deleteTeacherById(id);
    }

    public List<TeacherDTO> getAllTeachers() {
        return teacherDao.getAllTeachers();
    }

    public TeacherDTO createNewTeacher(TeacherDTO teacherDTO) {

        return teacherDao.createNewTeacher(teacherDTO);
    }

    public TeacherDTO saveTeacherByDTO(Long id, TeacherDTO teacherDTO) {
        return teacherDao.saveTeacherByDTO(id, teacherDTO);
    }

    public Page<TeacherDTO> findAllPage(Pageable pageable) {
        return teacherDao.findAllPage(pageable);
    }

    public List<TeacherDTO> findAllSorted(Sort sort) {
        return teacherDao.findAllSorted(sort);

    }

    public void deleteTeacherFromStudent(Long idT, Student student) {
        teacherDao.deleteTeacherFromStudent(idT, student);
    }

    public List<StudentDTO> getStudents(Long id) {
        return teacherDao.getStudents(id);
    }

    public List<TeacherDTO> getTeachers(String firstname, String lastname) {
        return teacherDao.getTeachers(firstname, lastname);
    }
}
