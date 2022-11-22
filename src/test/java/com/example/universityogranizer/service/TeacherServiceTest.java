package com.example.universityogranizer.service;

import com.example.universityogranizer.domain.Teacher;
import com.example.universityogranizer.repositories.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@Import(TeacherService.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TeacherServiceTest {

    @Autowired
    TeacherService teacherService;

    @Autowired
    TeacherRepository teacherRepository;

    @BeforeEach
    void reset(){
        teacherRepository.deleteAll();
    }
    @Test
    @DisplayName("Should save an entity")
    void should_save_entity(){
        //Given
        Teacher teacher = Teacher.builder().subject("math").students(new HashSet<>()).build();
        teacher.setAge(22);
        teacher.setEmail("makak@makak.pl");
        teacher.setSurname("Alabama");
        teacher.setPersonName("Adam");

        //When
        Teacher teacher1 = teacherService.saveNewTeacher(teacher);

        //Then
        assertThat(teacher1.getSubject()).isEqualTo(teacher.getSubject());
        assertThat(teacher1.getAge()).isEqualTo(teacher.getAge());
        assertThat(teacher1.getPersonName()).isEqualTo(teacher.getPersonName());
     }

    @Test
    @DisplayName("Should not save an entity and throw exception")
    void should_not_save_entity(){
        //Given
        Teacher teacher = Teacher.builder().subject("math").students(new HashSet<>()).build();
        teacher.setAge(12);
        teacher.setEmail("makak@makak.pl");
        teacher.setSurname("Alabama");
        teacher.setPersonName("A");

        //When
        Teacher teacher1 = teacherService.saveNewTeacher(teacher);

        //Then
        assertThat(teacher1.getSubject()).isEqualTo(teacher.getSubject());
        assertThat(teacher1.getAge()).isEqualTo(teacher.getAge());
        assertThat(teacher1.getPersonName()).isEqualTo(teacher.getPersonName());
    }

    @Test
    @DisplayName("Should find a saved teacher")
    void should_find_entity(){
        //Given
        Teacher teacher = Teacher.builder().subject("math").students(new HashSet<>()).build();
        teacher.setAge(16);
        teacher.setEmail("makak@makak.pl");
        teacher.setSurname("Alabama");
        teacher.setPersonName("A");
        Teacher teacher1 = teacherService.saveNewTeacher(teacher);

        //When
        Optional<Teacher> foundTeacher = teacherService.findTeacher(teacher1);

        //Then
        assertThat(foundTeacher).isPresent();
    }

    @Test
    @DisplayName("Should find a saved teacher")
    void should_not_find_entity(){
        //Given
        Teacher teacher = Teacher.builder().subject("math").students(new HashSet<>()).build();
        teacher.setAge(16);
        teacher.setEmail("makak@makak.pl");
        teacher.setSurname("Alabama");
        teacher.setPersonName("Adam");
        teacher.setId(1000L);

        //When
        Optional<Teacher> foundTeacher = teacherService.findTeacher(teacher);

        //Then
        assertThat(foundTeacher).isEmpty();
    }

    @Test
    @DisplayName("Should find a saved teacher by id")
    void should_find_entity_by_id(){
        //Given
        Teacher teacher = Teacher.builder().subject("math").students(new HashSet<>()).build();
        teacher.setAge(16);
        teacher.setEmail("makak@makak.pl");
        teacher.setSurname("Alabama");
        teacher.setPersonName("Adam");
        Teacher teacher1 = teacherService.saveNewTeacher(teacher);

        //When
        Optional<Teacher> foundTeacher = teacherService.findTeacherById(teacher1.getId());

        //Then
        assertThat(foundTeacher).isPresent();
    }
    @Test
    @DisplayName("Should not find a non existent teacher by id")
    void should_not_find_entity_by_id(){
        //Given
        Teacher teacher = Teacher.builder().subject("math").students(new HashSet<>()).build();
        teacher.setAge(16);
        teacher.setEmail("makak@makak.pl");
        teacher.setSurname("Alabama");
        teacher.setPersonName("Adam");
        teacher.setId(1000L);

        //When
        Optional<Teacher> foundTeacher = teacherService.findTeacherById(teacher.getId());

        //Then
        assertThat(foundTeacher).isEmpty();
    }

    @Test
    @DisplayName("Should delete a saved teacher by id")
    void should_delete_a_teacher(){
        //Given
        Teacher teacher = Teacher.builder().subject("math").students(new HashSet<>()).build();
        teacher.setAge(19);
        teacher.setEmail("makak@makak.pl");
        teacher.setSurname("Alabama");
        teacher.setPersonName("Adam");
        Teacher teacher1 = teacherService.saveNewTeacher(teacher);

        //When
        Optional<Teacher> foundTeacher = teacherService.findTeacherById(teacher1.getId());

        //Then
        assertThat(foundTeacher).isPresent();

        //Given
        teacherService.deleteTeacher(foundTeacher.get());

        //When&&Then
        assertThat(teacherRepository.findAll().size()).isEqualTo(0);
    }

}