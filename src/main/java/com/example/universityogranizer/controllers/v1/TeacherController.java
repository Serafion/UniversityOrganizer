package com.example.universityogranizer.controllers.v1;

import com.example.universityogranizer.api.v1.mapper.TeacherMapperImpl;
import com.example.universityogranizer.api.v1.model.StudentDTO;
import com.example.universityogranizer.api.v1.model.TeacherDTO;
import com.example.universityogranizer.api.v1.model.TeacherListDTO;
import com.example.universityogranizer.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    TeacherService teacherService;

    TeacherMapperImpl teacherMapper;

    public TeacherController(TeacherService teacherService, TeacherMapperImpl teacherMapper) {
        this.teacherService = teacherService;
        this.teacherMapper = teacherMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TeacherListDTO getListOfCustomers(){
        return new TeacherListDTO(teacherService.getAllTeachers());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public TeacherDTO getStudentById(@PathVariable Long id){
        return teacherService.findTeacherById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherDTO createNewStudent(@RequestBody TeacherDTO teacherDTO){
        return teacherService.createNewTeacher(teacherDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public TeacherDTO updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherDTO){
        return teacherService.saveTeacherByDTO(id,teacherDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id){
        teacherService.deleteTeacherById(id);
    }
    @GetMapping(path = "/page")
    public Page<TeacherDTO> loadStudentsPage(Pageable pageable) {
        return teacherService.findAllPage(pageable);
    }

    @GetMapping(path = "/sorted")
    public List<TeacherDTO> loadStudentsSorted(Sort sort) {
        return teacherService.findAllSorted(sort);
    }

    @PutMapping({"/{idT}/{idS}"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addTeacherToStudent(@PathVariable Long idT, @PathVariable Long idS){
        teacherService.addStudentToTeacher(idT,idS);
    }

    @PutMapping({"/{idT}/{idS}"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTeacherFromStudent(@PathVariable Long idT, @PathVariable Long idS){
        teacherService.deleteTeacherFromStudent(idT,idS);
    }

    @GetMapping(path = "/getTeacherStudents/{id}")
    public List<StudentDTO> getStudents(@PathVariable Long id) {
        return teacherService.getStudents(id);
    }

    @GetMapping(path = "/getTeacher/{firstname}and{lastname}")
    public TeacherListDTO getTeachersOfNameAndLastname(@PathVariable String firstname, @PathVariable String lastname) {
        return teacherService.getTeachers(firstname,lastname);
    }
}
