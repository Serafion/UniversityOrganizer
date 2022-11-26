package com.example.universityogranizer.teacherservice.repository;

import com.example.universityogranizer.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {


    List<Teacher> findAllByPersonNameAndSurname(String personName, String surname);

}
