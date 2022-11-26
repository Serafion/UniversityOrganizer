package com.example.universityogranizer.studentclient.repository;

import com.example.universityogranizer.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {


    Student  findByPersonNameAndSurname(String personName, String surname);

}
