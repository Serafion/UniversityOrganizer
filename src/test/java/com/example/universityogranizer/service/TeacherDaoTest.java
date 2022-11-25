package com.example.universityogranizer.service;

import com.example.universityogranizer.domain.Student;
import com.example.universityogranizer.domain.Teacher;
import com.example.universityogranizer.exeptions.TeacherNotFoundException;
import com.example.universityogranizer.teacherservice.TeacherServiceConfiguration;
import com.example.universityogranizer.teacherservice.TeacherServiceFacade;
import com.example.universityogranizer.teacherservice.dto.TeacherDTO;
import com.example.universityogranizer.teacherservice.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TeacherDaoTest {


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
        TeacherServiceFacade teacherServiceFacade = new TeacherServiceConfiguration().teacherServiceFacadeTest(teacherRepository);
        Teacher teacher = new Teacher(("math"),new HashSet<>());
        teacher.setAge(22);
        teacher.setEmail("makak@makak.pl");
        teacher.setSurname("Alabama");
        teacher.setPersonName("Adam");

        //When
        Teacher teacher1 = teacherServiceFacade.saveNewTeacher(teacher);

        //Then
        assertThat(teacher1.getSubject()).isEqualTo(teacher.getSubject());
        assertThat(teacher1.getAge()).isEqualTo(teacher.getAge());
        assertThat(teacher1.getPersonName()).isEqualTo(teacher.getPersonName());
     }

    @Test
    @DisplayName("Should not save an entity and throw exception")
    void should_not_save_entity(){
        //Given
        TeacherServiceFacade teacherServiceFacade = new TeacherServiceConfiguration().teacherServiceFacadeTest(teacherRepository);
        Teacher teacher = new Teacher(("math"),new HashSet<>());
        teacher.setAge(12);
        teacher.setEmail("makak@makak.pl");
        teacher.setSurname("Alabama");
        teacher.setPersonName("A");

        //When
        Teacher teacher1 = teacherServiceFacade.saveNewTeacher(teacher);

        //Then
        assertThat(teacher1.getSubject()).isEqualTo(teacher.getSubject());
        assertThat(teacher1.getAge()).isEqualTo(teacher.getAge());
        assertThat(teacher1.getPersonName()).isEqualTo(teacher.getPersonName());
    }

    @Test
    @DisplayName("Should find a saved teacher by id")
    void should_find_entity_by_id(){
        //Given
        TeacherServiceFacade teacherServiceFacade = new TeacherServiceConfiguration().teacherServiceFacadeTest(teacherRepository);
        Teacher teacher = new Teacher(("math"),new HashSet<>());
        teacher.setAge(16);
        teacher.setEmail("makak@makak.pl");
        teacher.setSurname("Alabama");
        teacher.setPersonName("Adam");
        Teacher teacher1 = teacherServiceFacade.saveNewTeacher(teacher);

        //When
        TeacherDTO foundTeacher = teacherServiceFacade.findTeacherById(teacher1.getId());

        //Then
        assertThat(foundTeacher.getAge()).isEqualTo(16);
    }

    @Test
    @DisplayName("Should save new teacher")
    void saveNewTeacher() {
        //Given
        TeacherServiceFacade teacherServiceFacade = new TeacherServiceConfiguration().teacherServiceFacadeTest(teacherRepository);
        Teacher teacher = new Teacher(("math"),new HashSet<>());
        teacher.setAge(22);
        teacher.setEmail("makak@makak.pl");
        teacher.setSurname("Alabama");
        teacher.setPersonName("Adam");

        //When
        Teacher teacher1 = teacherServiceFacade.saveNewTeacher(teacher);

        //Then
        assertThat(teacher1.getSubject()).isEqualTo(teacher.getSubject());
        assertThat(teacher1.getAge()).isEqualTo(teacher.getAge());
        assertThat(teacher1.getPersonName()).isEqualTo(teacher.getPersonName());
    }

    @Test
    void findTeacherById() {
        //Given
        TeacherServiceFacade teacherServiceFacade = new TeacherServiceConfiguration().teacherServiceFacadeTest(teacherRepository);
        Teacher teacher = new Teacher(("math"),new HashSet<>());
        teacher.setAge(22);
        teacher.setEmail("makak@makak.pl");
        teacher.setSurname("Alabama");
        teacher.setPersonName("Adam");
        Teacher teacher1 = teacherServiceFacade.saveNewTeacher(teacher);

        //When
        TeacherDTO teacher2 = teacherServiceFacade.findTeacherById(teacher1.getId());

        //Then
        assertThat(teacher2.getSubject()).isEqualTo(teacher.getSubject());
        assertThat(teacher2.getAge()).isEqualTo(teacher.getAge());
        assertThat(teacher2.getFirstname()).isEqualTo(teacher.getPersonName());
    }

    @Test
    void addStudentToTeacher() {
        //Given
        TeacherServiceFacade teacherServiceFacade = new TeacherServiceConfiguration().teacherServiceFacadeTest(teacherRepository);
        Teacher teacher = new Teacher(("math"),new HashSet<>());
        teacher.setAge(22);
        teacher.setEmail("makak@makak.pl");
        teacher.setSurname("Alabama");
        teacher.setPersonName("Adam");
        Teacher teacher1 = teacherServiceFacade.saveNewTeacher(teacher);
        Student student = new Student();



        //When
        Teacher teacher2 = teacherServiceFacade.addStudentToTeacher(teacher1.getId(),student);

        //Then
        assertThat(teacher2.getStudents().size()).isPositive();
    }

    @Test
    void deleteTeacherById() {
        //Given
        TeacherServiceFacade teacherServiceFacade = new TeacherServiceConfiguration().teacherServiceFacadeTest(teacherRepository);
        Teacher teacher = new Teacher(("math"),new HashSet<>());
        teacher.setAge(22);
        teacher.setEmail("makak@makak.pl");
        teacher.setSurname("Alabama");
        teacher.setPersonName("Adam");
        Teacher teacher1 = teacherServiceFacade.saveNewTeacher(teacher);

        //When
        teacherServiceFacade.deleteTeacherById(teacher1.getId());


        //Then
        assertThatExceptionOfType(TeacherNotFoundException.class).isThrownBy(() -> teacherServiceFacade.findTeacherById(teacher1.getId()));
    }

    @Test
    void getAllTeachers() {
        //Given
        TeacherServiceFacade teacherServiceFacade = new TeacherServiceConfiguration().teacherServiceFacadeTest(teacherRepository);
        Teacher teacher = new Teacher(("math"),new HashSet<>());
        Teacher teacher1 = new Teacher("bath", new HashSet<>());
        Teacher teacher2 = new Teacher("Construction",new HashSet<>());
        teacherServiceFacade.saveNewTeacher(teacher);
        teacherServiceFacade.saveNewTeacher(teacher1);
        teacherServiceFacade.saveNewTeacher(teacher2);

        //When
        List<TeacherDTO> teacherDTOList = teacherServiceFacade.getAllTeachers();


        //Then
        assertThat(teacherDTOList.size()).isEqualTo(3);
    }

    @Test
    void createNewTeacher() {

    }

    @Test
    void saveTeacherByDTO() {
    }

    @Test
    void findAllPage() {
    }

    @Test
    void findAllSorted() {
    }

    @Test
    void deleteTeacherFromStudent() {
    }

    @Test
    void getStudents() {
    }

    @Test
    void getTeachers() {
    }
//    @Test
//    @DisplayName("Should not find a non existent teacher by id")
//    void should_not_find_entity_by_id(){
//        //Given
//        Teacher teacher = new Teacher(("math"),new HashSet<>());
//        teacher.setAge(16);
//        teacher.setEmail("makak@makak.pl");
//        teacher.setSurname("Alabama");
//        teacher.setPersonName("Adam");
//        teacher.setId(1000L);
//
//        //When && Then
//        assertThatExceptionOfType(TeacherNotFoundException.class).isThrownBy(teacherService.findTeacherById(teacher.getId()));
//
//    }
//
//    @Test
//    @DisplayName("Should delete a saved teacher by id")
//    void should_delete_a_teacher(){
//        //Given
//        Teacher teacher = new Teacher(("math"),new HashSet<>());
//        teacher.setAge(19);
//        teacher.setEmail("makak@makak.pl");
//        teacher.setSurname("Alabama");
//        teacher.setPersonName("Adam");
//        Teacher teacher1 = teacherService.saveNewTeacher(teacher);
//
//        //When
//        Optional<Teacher> foundTeacher = teacherService.findTeacherById(teacher1.getId());
//
//        //Then
//        assertThat(foundTeacher).isPresent();
//
//        //Given
//        teacherService.deleteTeacher(foundTeacher.get());
//
//        //When&&Then
//        assertThat(teacherRepository.findAll().size()).isEqualTo(0);
//    }

}