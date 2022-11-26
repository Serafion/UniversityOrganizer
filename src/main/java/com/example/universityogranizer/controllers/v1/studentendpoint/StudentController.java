package com.example.universityogranizer.controllers.v1.studentendpoint;

import com.example.universityogranizer.domain.Student;
import com.example.universityogranizer.domain.Teacher;
import com.example.universityogranizer.studentservice.StudentServiceFacade;
import com.example.universityogranizer.studentservice.dto.StudentDTO;
import com.example.universityogranizer.teacherservice.TeacherServiceFacade;
import com.example.universityogranizer.teacherservice.dto.TeacherDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentServiceFacade studentServiceFacade;
    private final TeacherServiceFacade teacherServiceFacade;

    public StudentController(StudentServiceFacade studentServiceFacade, TeacherServiceFacade teacherServiceFacade) {
        this.studentServiceFacade = studentServiceFacade;
        this.teacherServiceFacade = teacherServiceFacade;
    }

    @GetMapping(headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> getListOfStudents(){
        return studentServiceFacade.getAllStudents();
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO getStudentById(@PathVariable Long id){
        return studentServiceFacade.findStudentById(id);
    }


    @PostMapping(headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO createNewStudent(@RequestBody StudentDTO studentDTO){
        return studentServiceFacade.createNewStudent(studentDTO);
    }

    @PutMapping(value = {"/{id}"}, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO updateCustomer(@PathVariable Long id, @RequestBody StudentDTO studentDTO){
        return studentServiceFacade.saveStudentByDTO(id,studentDTO);
    }

    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id){
        studentServiceFacade.deleteStudentById(id);
    }
    @GetMapping(path = "/page", headers = "Accept=application/json")
    public Page<StudentDTO> loadStudentsPage(Pageable pageable) {
        return studentServiceFacade.findAllPage(pageable);
    }

    @GetMapping(path = "/sorted", headers = "Accept=application/json")
    public List<StudentDTO> loadStudentsSorted(Sort sort) {
        return studentServiceFacade.findAllSorted(sort);
    }

    @PostMapping(value = {"/{idS}/{idT}"}, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addTeacherToStudent(@PathVariable Long idS, @PathVariable Long idT){
        Student student = studentServiceFacade.findStudentDataById(idS);
        Teacher teacher = teacherServiceFacade.findTeacherDataById(idT);
        teacherServiceFacade.addStudentToTeacher(idT,student);
        studentServiceFacade.addTeacherToStudent(idS,teacher);
    }

    @GetMapping({"/{idS}/{idT}"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTeacherToStudent(@PathVariable Long idT, @PathVariable Long idS){
        Student student = studentServiceFacade.findStudentDataById(idS);
        Teacher teacher = teacherServiceFacade.findTeacherDataById(idT);
        teacherServiceFacade.deleteTeacherFromStudent(idT,student);
        studentServiceFacade.deleteTeacherFromStudent(idS,teacher);
    }

    @GetMapping(path = "/getStudentTeachers/{id}")
    public List<TeacherDTO> getTeachers(@PathVariable Long id) {
        return studentServiceFacade.getTeachers(id);
    }

    @GetMapping(path = "/getStudent/{firstname}and{lastname}")
    public List<StudentDTO> getStudentOfName(@PathVariable String firstname, @PathVariable String lastname) {
        return studentServiceFacade.getStudents(firstname,lastname);
    }





}
