package com.example.universityogranizer.studentclient.dto;

import com.example.universityogranizer.teacherservice.dto.TeacherDTO;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    @Size(min = 3)
    private String firstname;
    @Size(min = 3)
    private String lastname;
    @Min(18)
    private Integer age;
    @Email
    private String email;
    private String faculty;
    private Set<TeacherDTO> teachers;

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

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Set<TeacherDTO> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<TeacherDTO> teachers) {
        this.teachers = teachers;
    }
}
