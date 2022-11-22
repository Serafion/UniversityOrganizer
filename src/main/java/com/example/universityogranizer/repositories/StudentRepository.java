package com.example.universityogranizer.repositories;

import com.example.universityogranizer.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    Student findByPersonName(String personName);

    Student  findByPersonNameAndSurname(String personName, String surname);

    Student findBySurname(String surname);
}
