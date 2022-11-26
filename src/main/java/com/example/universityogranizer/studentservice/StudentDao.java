package com.example.universityogranizer.studentservice;

import com.example.universityogranizer.domain.Student;
import com.example.universityogranizer.domain.Teacher;
import com.example.universityogranizer.studentservice.dto.StudentDTO;
import com.example.universityogranizer.studentservice.exceptions.StudentNotFoundException;
import com.example.universityogranizer.studentservice.repository.StudentRepository;
import com.example.universityogranizer.teacherservice.TeacherMapperImpl;
import com.example.universityogranizer.teacherservice.dto.TeacherDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentDao {

    private final StudentRepository studentRepository;
    private final StudentMapperImpl studentMapper;

    public StudentDao(StudentRepository studentRepository, StudentMapperImpl studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentDTO findStudentById(Long id) {
        return studentMapper.studentToStudentDTO(studentRepository.findById(id).orElseThrow(StudentNotFoundException::new));
    }


    public Student addTeacherToStudent(Long idS, Teacher teacher) {
        Student student = studentRepository.findById(idS).orElseThrow(StudentNotFoundException::new);
        student.getTeachers().add(teacher);
        return studentRepository.save(student);
    }


    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }


    public List<StudentDTO> getAllStudents() {
        return studentRepository
                .findAll()
                .stream()
                .map(studentMapper::studentToStudentDTO)
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


    public void deleteTeacherFromStudent(Long idS, Teacher teacher) {
        Student student = studentRepository.findById(idS).orElseThrow(StudentNotFoundException::new);
        student.getTeachers().remove(teacher);
        studentRepository.save(student);
    }

    public List<TeacherDTO> getTeachers(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        return student.getTeachers().stream().map(new TeacherMapperImpl()::teacherToTeacherDTO).collect(Collectors.toList());

    }

    public List<StudentDTO> getStudent(String firstname, String lastname) {
        return studentRepository.findAllByPersonNameAndSurname(firstname, lastname).stream().map(studentMapper::studentToStudentDTO).collect(Collectors.toList());
    }

    public Student findStudentDataById(Long idS) {
        return studentRepository.findById(idS).orElseThrow(StudentNotFoundException::new);
    }

//    public Student saveNewStudent(Student student) {
//        return studentRepository.save(student);
//    }
}
