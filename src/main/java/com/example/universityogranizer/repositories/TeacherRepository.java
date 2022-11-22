package com.example.universityogranizer.repositories;

import com.example.universityogranizer.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findByPersonName(String personName);

    Teacher findByPersonNameAndSurname(String personName, String surname);

    Teacher findBySurname(String surname);

}
