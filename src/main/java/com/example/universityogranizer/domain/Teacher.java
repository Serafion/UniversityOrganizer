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
public class Teacher extends Person{

    String subject;

    @ManyToMany
    @JoinTable(name = "student_teacher",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    Set<Student> students;

    public Teacher() {
        if(students==null){
            students=new HashSet<>();
        }
    }

    public Teacher(String subject, Set<Student> students) {
        this.subject = subject;
        this.students = students;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        return subject != null ? subject.equals(teacher.subject) : teacher.subject == null;
    }

    @Override
    public int hashCode() {
        return subject != null ? subject.hashCode() : 0;
    }
}
