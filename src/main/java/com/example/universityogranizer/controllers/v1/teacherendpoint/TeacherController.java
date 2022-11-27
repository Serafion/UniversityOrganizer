package com.example.universityogranizer.controllers.v1.teacherendpoint;

import com.example.universityogranizer.domain.Student;
import com.example.universityogranizer.domain.Teacher;
import com.example.universityogranizer.studentservice.StudentServiceFacade;
import com.example.universityogranizer.studentservice.dto.StudentDTO;
import com.example.universityogranizer.teacherservice.TeacherServiceFacade;
import com.example.universityogranizer.teacherservice.dto.TeacherDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get list of teachers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found records")})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TeacherDTO> getListOfTeachers(){
        return teacherServiceFacade.getAllTeachers();
    }

    @Operation(summary = "Get teacher by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found records"),
            @ApiResponse(responseCode = "404", description = "Record not found")})
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public TeacherDTO getTeacherById(@PathVariable Long id){
        return teacherServiceFacade.findTeacherById(id);
    }


    @Operation(summary = "Save new teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Record created"),})
    @PostMapping(headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherDTO createNewTeacher(@RequestBody TeacherDTO teacherDTO){
        return teacherServiceFacade.createNewTeacher(teacherDTO);
    }

    @Operation(summary = "Update a teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Record updated"),})
    @PutMapping(value = {"/{id}"}, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public TeacherDTO updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherDTO){
        return teacherServiceFacade.saveTeacherByDTO(id,teacherDTO);
    }

    @Operation(summary = "Delete a teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Record deleted")})
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteTeacher(@PathVariable Long id){
       teacherServiceFacade.deleteTeacherById(id);
    }

    @Operation(summary = "Load teacher page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Got pages")})
    @GetMapping(value = {"page"}, headers = "Accept=application/json")
    public Page<TeacherDTO> loadTeacherPage(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page,size);
        return teacherServiceFacade.findAllPage(pageable);
    }

    @Operation(summary = "Load teacher sorted view")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found records"),
            @ApiResponse(responseCode = "404", description = "Record not found")})
    @GetMapping(path = "/sorted", headers = "Accept=application/json")
    public List<TeacherDTO> loadTeacherSorted(@RequestBody Sort sort) {
        return teacherServiceFacade.findAllSorted(sort);
    }

    @Operation(summary = "Add student to teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added student")})
    @PostMapping(value = {"/addStudent/{idT}+{idS}"}, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addTeacherToStudent(@PathVariable Long idT, @PathVariable Long idS){
        Student student = studentServiceFacade.findStudentDataById(idS);
        Teacher teacher = teacherServiceFacade.findTeacherDataById(idT);
        teacherServiceFacade.addStudentToTeacher(idT,student);
        studentServiceFacade.addTeacherToStudent(idS,teacher);
    }

    @Operation(summary = "Delete student from teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted student from teacher")})
    @GetMapping({"/deleteStudent/{idT}-{idS}"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTeacherFromStudent(@PathVariable Long idT, @PathVariable Long idS){
        Student student = studentServiceFacade.findStudentDataById(idS);
        Teacher teacher = teacherServiceFacade.findTeacherDataById(idT);
        teacherServiceFacade.deleteTeacherFromStudent(idT,student);
        studentServiceFacade.deleteTeacherFromStudent(idS,teacher);
    }

    @Operation(summary = "Get list of students asigned to teacher by teacher id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found records")})
    @GetMapping(path = "/getTeacherStudents/{id}")
    public List<StudentDTO> getStudents(@PathVariable Long id) {
        return teacherServiceFacade.getStudents(id);
    }

    @Operation(summary = "Find teacher by first name and last name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found records"),
            @ApiResponse(responseCode = "404", description = "Record not found")})
    @GetMapping(path = "/getTeacher/{firstname}and{lastname}")
    public List<TeacherDTO> getTeachersOfNameAndLastname(@PathVariable String firstname, @PathVariable String lastname) {
        return teacherServiceFacade.getTeachers(firstname,lastname);
    }
}
