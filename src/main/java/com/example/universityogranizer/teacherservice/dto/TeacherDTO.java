package com.example.universityogranizer.teacherservice.dto;

import com.example.universityogranizer.studentservice.dto.StudentDTO;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class TeacherDTO {

    @Size(min = 3)
    private String firstname;
    @Size(min = 3)
    private String lastname;
    @Min(18)
    private Integer age;
    @Email
    private String email;
    private String subject;
    private Set<StudentDTO> students;

    public TeacherDTO() {
    }

    public TeacherDTO(String firstname, String lastname, Integer age, String email, String subject, Set<StudentDTO> students) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.subject = subject;
        this.students = students;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Set<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentDTO> students) {
        this.students = students;
    }
}
