package com.example.universityogranizer.controllers.v1;

import com.example.universityogranizer.api.v1.model.StudentDTO;
import com.example.universityogranizer.api.v1.model.StudentListDTO;
import com.example.universityogranizer.teacherservice.dto.TeacherDTO;
import com.example.universityogranizer.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public StudentListDTO getListOfCustomers(){
        return new StudentListDTO(studentService.getAllStudents());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO getStudentById(@PathVariable Long id){
        return studentService.findStudentById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO createNewStudent(@RequestBody StudentDTO studentDTO){
        return studentService.createNewStudent(studentDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO updateCustomer(@PathVariable Long id, @RequestBody StudentDTO studentDTO){
        return studentService.saveStudentByDTO(id,studentDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id){
        studentService.deleteStudentById(id);
    }
    @GetMapping(path = "/page")
    public Page<StudentDTO> loadStudentsPage(Pageable pageable) {
        return studentService.findAllPage(pageable);
    }

    @GetMapping(path = "/sorted")
    public List<StudentDTO> loadStudentsSorted(Sort sort) {
        return studentService.findAllSorted(sort);
    }

    @PostMapping({"/{idT}/{idS}"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addTeacherToStudent(@PathVariable Long idT, @PathVariable Long idS){
        studentService.addTeacherToStudent(idT,idS);
    }

    @GetMapping({"/{idT}/{idS}"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTeacherToStudent(@PathVariable Long idT, @PathVariable Long idS){
        studentService.deleteTeacherFromStudent(idT,idS);
    }

    @GetMapping(path = "/getStudentTeachers/{id}")
    public List<TeacherDTO> getTeachers(@PathVariable Long id) {
        return studentService.getTeachers(id);
    }

    @GetMapping(path = "/getStudent/{firstname}and{lastname}")
    public StudentDTO getStudentOfName(@PathVariable String firstname, @PathVariable String lastname) {
        return studentService.getStudent(firstname,lastname);
    }





}
