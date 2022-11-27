package com.example.universityorganizer;

import com.example.universityogranizer.DemoApplication;
import com.example.universityogranizer.studentservice.repository.StudentRepository;
import com.example.universityogranizer.teacherservice.dto.TeacherDTO;
import com.example.universityogranizer.teacherservice.repository.TeacherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = DemoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("integration")
public class TeacherIntegrationTest {

    @Container
    private static final MySQLContainer MY_SQL_CONTAINER = new MySQLContainer();

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    @BeforeEach
    void setup(){
        teacherRepository.deleteAll();
        studentRepository.deleteAll();
    }




    @Test
    @DisplayName("Should save and return a list of one teacher")
    void shouldSaveTeacherAndReturnAListofOne() throws Exception {
        //Given
        TeacherDTO teacherDTO = new TeacherDTO("Adam","Lambert",22,"greco@greco.pl","math",new HashSet<>());

        //When
        MvcResult teacherAdded = mockMvc.perform(MockMvcRequestBuilders.post("/teachers").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(teacherDTO))).andReturn();

        //Then
        assertThat(teacherAdded.getResponse().getStatus()).isEqualTo(201);

        //When
        MvcResult teachers = mockMvc.perform(MockMvcRequestBuilders.get("/teachers")).andReturn();

        //Then
        assertThat(teachers.getResponse().getStatus()).isEqualTo(200);
        assertThat(objectMapper.readValue(teachers.getResponse().getContentAsString(), List.class).size()).isEqualTo(1);

    }

    @Test
    @DisplayName("Should return an error message when teacher not in db")
    void shouldReturn404StatusWhenTeacherNotInDB() throws Exception {
        //When
        MvcResult teachers = mockMvc.perform(MockMvcRequestBuilders.get("/teachers/10")).andReturn();

        //Then
        assertThat(teachers.getResponse().getStatus()).isEqualTo(404);

    }

    @Test
    @DisplayName("Should save and return a list of one teacher")
    void shouldReturnATeacherPage() throws Exception {
        //Given
        TeacherDTO teacherDTO = new TeacherDTO("Adam","Lambert",22,"greco@greco.pl","math",new HashSet<>());
        TeacherDTO teacherDTO1 = new TeacherDTO("Axel","Lambert",22,"greco@greco.pl","math",new HashSet<>());
        TeacherDTO teacherDTO2 = new TeacherDTO("Baxter","Lambert",22,"greco@greco.pl","math",new HashSet<>());
        TeacherDTO teacherDTO3 = new TeacherDTO("Tom","Lambert",22,"greco@greco.pl","math",new HashSet<>());
        TeacherDTO teacherDTO4 = new TeacherDTO("Don","Lambert",22,"greco@greco.pl","math",new HashSet<>());

        //When
        MvcResult teacherAdded = mockMvc.perform(MockMvcRequestBuilders.post("/teachers").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(teacherDTO))).andReturn();
        MvcResult teacherAdded1 = mockMvc.perform(MockMvcRequestBuilders.post("/teachers").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(teacherDTO1))).andReturn();
        MvcResult teacherAdded2 = mockMvc.perform(MockMvcRequestBuilders.post("/teachers").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(teacherDTO2))).andReturn();
        MvcResult teacherAdded3 = mockMvc.perform(MockMvcRequestBuilders.post("/teachers").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(teacherDTO3))).andReturn();
        MvcResult teacherAdded4 = mockMvc.perform(MockMvcRequestBuilders.post("/teachers").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(teacherDTO4))).andReturn();

        //Then
        assertThat(teacherAdded.getResponse().getStatus()).isEqualTo(201);
        assertThat(teacherAdded1.getResponse().getStatus()).isEqualTo(201);
        assertThat(teacherAdded2.getResponse().getStatus()).isEqualTo(201);
        assertThat(teacherAdded3.getResponse().getStatus()).isEqualTo(201);
        assertThat(teacherAdded4.getResponse().getStatus()).isEqualTo(201);


        //When
        MvcResult teachers = mockMvc.perform(MockMvcRequestBuilders.get("/teachers/page").param("page","1").param("size","3")).andReturn();

        //Then
        assertThat(teachers.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void test() {
        assertThat(MY_SQL_CONTAINER.isRunning()).isTrue();
    }
}
