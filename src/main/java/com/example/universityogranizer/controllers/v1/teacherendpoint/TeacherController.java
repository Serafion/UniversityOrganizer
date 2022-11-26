package com.example.universityogranizer.controllers.v1.teacherendpoint;

import com.example.universityogranizer.domain.Student;
import com.example.universityogranizer.domain.Teacher;
import com.example.universityogranizer.studentservice.StudentServiceFacade;
import com.example.universityogranizer.studentservice.dto.StudentDTO;
import com.example.universityogranizer.teacherservice.TeacherServiceFacade;
import com.example.universityogranizer.teacherservice.dto.TeacherDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherServiceFacade teacherServiceFacade;
    private final StudentServiceFacade studentServiceFacade;




    public TeacherController(TeacherServiceFacade teacherServiceFacade, StudentServiceFacade studentServiceFacade) {
            this.teacherServiceFacade=teacherServiceFacade;
            this.studentServiceFacade=studentServiceFacade;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TeacherDTO> getListOfTeachers(){
        return teacherServiceFacade.getAllTeachers();
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public TeacherDTO getStudentById(@PathVariable Long id){
        return teacherServiceFacade.findTeacherById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherDTO createNewTeacher(@RequestBody TeacherDTO teacherDTO){
        return teacherServiceFacade.createNewTeacher(teacherDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public TeacherDTO updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherDTO){
        return teacherServiceFacade.saveTeacherByDTO(id,teacherDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id){
       teacherServiceFacade.deleteTeacherById(id);
    }

    @GetMapping(path = "/page")
    public Page<TeacherDTO> loadTeacherPage(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "3") int size) {

        Pageable pageable = PageRequest.of(page,size);
        return teacherServiceFacade.findAllPage(pageable);
    }

    @GetMapping(path = "/sorted")
    public List<TeacherDTO> loadStudentsSorted(@RequestBody Sort sort) {
        return teacherServiceFacade.findAllSorted(sort);
    }

    @PostMapping({"/addStudent/{idT}+{idS}"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addTeacherToStudent(@PathVariable Long idT, @PathVariable Long idS){
        Student student = studentServiceFacade.findStudentDataById(idS);
        Teacher teacher = teacherServiceFacade.findTeacherDataById(idT);
        teacherServiceFacade.addStudentToTeacher(idT,student);
        studentServiceFacade.addTeacherToStudent(idS,teacher);
    }

    @GetMapping({"/deleteStudent/{idT}-{idS}"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTeacherFromStudent(@PathVariable Long idT, @PathVariable Long idS){
        Student student = studentServiceFacade.findStudentDataById(idS);
        Teacher teacher = teacherServiceFacade.findTeacherDataById(idT);
        teacherServiceFacade.deleteTeacherFromStudent(idT,student);
        studentServiceFacade.deleteTeacherFromStudent(idS,teacher);
    }

    @GetMapping(path = "/getTeacherStudents/{id}")
    public List<StudentDTO> getStudents(@PathVariable Long id) {
        return teacherServiceFacade.getStudents(id);
    }

    @GetMapping(path = "/getTeacher/{firstname}and{lastname}")
    public List<TeacherDTO> getTeachersOfNameAndLastname(@PathVariable String firstname, @PathVariable String lastname) {
        return teacherServiceFacade.getTeachers(firstname,lastname);
    }
}
