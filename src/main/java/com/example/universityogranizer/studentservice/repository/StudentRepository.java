package com.example.universityogranizer.studentservice.repository;

import com.example.universityogranizer.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {


    List<Student> findAllByPersonNameAndSurname(String firstname, String lastname);
}
