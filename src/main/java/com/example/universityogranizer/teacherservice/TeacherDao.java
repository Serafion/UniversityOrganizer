package com.example.universityogranizer.teacherservice;

import com.example.universityogranizer.api.v1.mapper.StudentMapperImpl;
import com.example.universityogranizer.api.v1.model.StudentDTO;
import com.example.universityogranizer.teacherservice.dto.TeacherDTO;
import com.example.universityogranizer.teacherservice.dto.TeacherListDTO;
import com.example.universityogranizer.domain.Student;
import com.example.universityogranizer.domain.Teacher;
import com.example.universityogranizer.exeptions.TeacherNotFoundException;
import com.example.universityogranizer.repositories.StudentRepository;
import com.example.universityogranizer.teacherservice.repository.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
class TeacherDao {

    private TeacherRepository teacherRepository;
    private TeacherMapperImpl teacherMapper;

    public TeacherDao(TeacherRepository teacherRepository,TeacherMapperImpl teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    public Teacher saveNewTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }

    public TeacherDTO findTeacherById(Long id){
        return teacherMapper.teacherToTeacherDTO(teacherRepository.findById(id).orElseThrow(TeacherNotFoundException::new));
    }

    public Teacher addStudentToTeacher(Long idT, Student student){
        Teacher teacher = teacherRepository.findById(idT).orElseThrow(TeacherNotFoundException::new);
        teacher.getStudents().add(student);
        student.getTeachers().add(teacher);
        return teacherRepository.save(teacher);
    }

    public void deleteTeacherById(Long id){
        teacherRepository.deleteById(id);
    }
    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository
                .findAll()
                .stream()
                .map(student -> {
                    TeacherDTO teacherDTO = teacherMapper.teacherToTeacherDTO(student);
                    return teacherDTO;
                })
                .collect(Collectors.toList());
    }

    public TeacherDTO createNewTeacher(TeacherDTO teacherDTO) {

        return saveAndReturnDTO(teacherMapper.teacherDTOToTeacher(teacherDTO));
    }

    private TeacherDTO saveAndReturnDTO(Teacher teacher) {
        Teacher savedTeacher = teacherRepository.save(teacher);

        return teacherMapper.teacherToTeacherDTO(savedTeacher);
    }

    public TeacherDTO saveTeacherByDTO(Long id, TeacherDTO teacherDTO) {
        Teacher teacher = teacherMapper.teacherDTOToTeacher(teacherDTO);
        teacher.setId(id);
        return saveAndReturnDTO(teacher);
    }

    public Page<TeacherDTO> findAllPage(Pageable pageable) {

        return teacherRepository.findAll(pageable).map(teacherMapper::teacherToTeacherDTO);
    }

    public List<TeacherDTO> findAllSorted(Sort sort) {
        return teacherRepository.findAll(sort).stream().map(teacherMapper::teacherToTeacherDTO).collect(Collectors.toList());

    }

    public void deleteTeacherFromStudent(Long idT, Student student) {
        Teacher teacher = teacherRepository.findById(idT).orElseThrow(TeacherNotFoundException::new);
        teacher.getStudents().remove(student);
        student.getTeachers().remove(teacher);
        teacherRepository.save(teacher);
    }

    public List<StudentDTO> getStudents(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(TeacherNotFoundException::new);
        return teacher.getStudents().stream().map(new StudentMapperImpl()::studentToStudentDTO).collect(Collectors.toList());
    }

    public TeacherListDTO getTeachers(String firstname, String lastname) {
        return new TeacherListDTO(teacherRepository.findAllByPersonNameAndSurname(firstname, lastname).stream().map(teacherMapper::teacherToTeacherDTO).collect(Collectors.toList()));
    }
}
