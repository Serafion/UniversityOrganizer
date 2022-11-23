package com.example.universityogranizer.repositories;

import com.example.universityogranizer.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    Student findByPersonName(String personName);

    Student  findByPersonNameAndSurname(String personName, String surname);

    Student findBySurname(String surname);
    Page<Student> findStudentBySurname(String lastName, Pageable pageable);

    Page<Student> findAll(Pageable pageable);

    List<Student> findAll(Sort sort);

}
