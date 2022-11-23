package com.example.universityogranizer.repositories;

import com.example.universityogranizer.domain.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findByPersonName(String personName);

    List<Teacher> findAllByPersonNameAndSurname(String personName, String surname);

    Teacher findBySurname(String surname);

    Page<Teacher> findTeacherBySurname(String lastName, Pageable pageable);

}
