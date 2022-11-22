package com.example.universityogranizer.domain;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
public class Student extends Person{

    String faculty;
    @ManyToMany
    @JoinTable(name = "student_teacher",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    Set<Teacher> teachers;

    public Student() {
        if(teachers==null){
            teachers=new HashSet<>();
        }
    }

    public Student(String faculty, Set<Teacher> teachers) {
        this.faculty = faculty;
        this.teachers = teachers;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return faculty != null ? faculty.equals(student.faculty) : student.faculty == null;
    }

    @Override
    public int hashCode() {
        return faculty != null ? faculty.hashCode() : 0;
    }
}
