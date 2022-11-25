package com.example.universityogranizer.controllers.v1;

import com.example.universityogranizer.api.v1.model.StudentDTO;
import com.example.universityogranizer.teacherservice.TeacherServiceFacade;
import com.example.universityogranizer.teacherservice.dto.TeacherDTO;
import com.example.universityogranizer.teacherservice.dto.TeacherListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    TeacherServiceFacade teacherServiceFacade;


    public TeacherController(TeacherServiceFacade teacherServiceFacade) {
            this.teacherServiceFacade=teacherServiceFacade;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TeacherListDTO getListOfCustomers(){
        return new TeacherListDTO(teacherServiceFacade.getAllTeachers());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public TeacherDTO getStudentById(@PathVariable Long id){
        return teacherServiceFacade.findTeacherById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherDTO createNewStudent(@RequestBody TeacherDTO teacherDTO){
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
    public Page<TeacherDTO> loadStudentsPage(Pageable pageable) {
        return teacherServiceFacade.findAllPage(pageable);
    }

    @GetMapping(path = "/sorted")
    public List<TeacherDTO> loadStudentsSorted(Sort sort) {
        return teacherServiceFacade.findAllSorted(sort);
    }

//    @PostMapping({"/{idT}/{idS}"})
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public void addTeacherToStudent(@PathVariable Long idT, @PathVariable Long idS){
//        teacherDao.addStudentToTeacher(idT,idS);
//    }

//    @GetMapping({"/{idT}/{idS}"})
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public void deleteTeacherFromStudent(@PathVariable Long idT, @PathVariable Long idS){
//        teacherDao.deleteTeacherFromStudent(idT,idS);
//    }

    @GetMapping(path = "/getTeacherStudents/{id}")
    public List<StudentDTO> getStudents(@PathVariable Long id) {
        return teacherServiceFacade.getStudents(id);
    }

    @GetMapping(path = "/getTeacher/{firstname}and{lastname}")
    public TeacherListDTO getTeachersOfNameAndLastname(@PathVariable String firstname, @PathVariable String lastname) {
        return teacherServiceFacade.getTeachers(firstname,lastname);
    }
}
