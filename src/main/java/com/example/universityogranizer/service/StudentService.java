package com.example.universityogranizer.service;

import com.example.universityogranizer.api.v1.mapper.StudentMapper;
import com.example.universityogranizer.api.v1.mapper.TeacherMapperImpl;
import com.example.universityogranizer.api.v1.model.StudentDTO;
import com.example.universityogranizer.api.v1.model.TeacherDTO;
import com.example.universityogranizer.domain.Student;
import com.example.universityogranizer.domain.Teacher;
import com.example.universityogranizer.exeptions.StudentNotFoundException;
import com.example.universityogranizer.exeptions.TeacherNotFoundException;
import com.example.universityogranizer.repositories.StudentRepository;
import com.example.universityogranizer.repositories.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StudentService {

    private TeacherRepository teacherRepository;
    private StudentRepository studentRepository;

    private StudentMapper studentMapper;

    public StudentService(TeacherRepository teacherRepository, StudentRepository studentRepository, StudentMapper studentMapper) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public Student saveNewTeacher(Student student) {
        return studentRepository.save(student);
    }

    public Student updateTeacher(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> findTeacher(Student student) {
        return studentRepository.findById(student.getId());
    }

    public StudentDTO findStudentById(Long id) {
        return studentMapper.studentToStudentDTO(studentRepository.findById(id).orElseThrow(StudentNotFoundException::new));
    }

    public Student addTeacherToStudent(Long idT, Long idS) {
        Student student = studentRepository.findById(idS).orElseThrow(StudentNotFoundException::new);
        Teacher teacher = teacherRepository.findById(idT).orElseThrow(TeacherNotFoundException::new);
        teacher.getStudents().add(student);
        student.getTeachers().add(teacher);
        teacherRepository.save(teacher);
        return studentRepository.save(student);
    }


    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }


    public List<StudentDTO> getAllStudents() {
        return studentRepository
                .findAll()
                .stream()
                .map(student -> {
                    StudentDTO studentDTO = studentMapper.studentToStudentDTO(student);
                    return studentDTO;
                })
                .collect(Collectors.toList());
    }

    public StudentDTO createNewStudent(StudentDTO studentDTO) {

        return saveAndReturnDTO(studentMapper.studentDTOtoStudent(studentDTO));
    }

    private StudentDTO saveAndReturnDTO(Student student) {
        Student savedStudent = studentRepository.save(student);

        return studentMapper.studentToStudentDTO(savedStudent);
    }

    public StudentDTO saveStudentByDTO(Long id, StudentDTO studentDTO) {
        Student student = studentMapper.studentDTOtoStudent(studentDTO);
        student.setId(id);
        return saveAndReturnDTO(student);
    }

    public Page<StudentDTO> findAllPage(Pageable pageable) {

        return studentRepository.findAll(pageable).map(studentMapper::studentToStudentDTO);
    }

    public List<StudentDTO> findAllSorted(Sort sort) {
        return studentRepository.findAll(sort).stream().map(studentMapper::studentToStudentDTO).collect(Collectors.toList());
    }

    public void deleteTeacherFromStudent(Long idT, Long idS) {
        Student student = studentRepository.findById(idS).orElseThrow(StudentNotFoundException::new);
        Teacher teacher = teacherRepository.findById(idT).orElseThrow(TeacherNotFoundException::new);
        teacher.getStudents().remove(student);
        student.getTeachers().remove(teacher);
        teacherRepository.save(teacher);
        studentRepository.save(student);


    }

    public List<TeacherDTO> getTeachers(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        return student.getTeachers().stream().map(new TeacherMapperImpl()::teacherToTeacherDTO).collect(Collectors.toList());

    }

    public StudentDTO getStudent(String firstname, String lastname) {
        return studentMapper.studentToStudentDTO(studentRepository.findByPersonNameAndSurname(firstname, lastname));
    }
}
