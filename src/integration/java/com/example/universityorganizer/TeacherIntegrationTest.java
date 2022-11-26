package com.example.universityorganizer;

import com.example.universityogranizer.DemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

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

//    @Test
//    @DisplayName("Should return generated character")
//    void shouldReturnGeneratedCharacter() throws Exception {
//        //Given
//        MvcResult generatedCharacter = mockMvc.perform(MockMvcRequestBuilders.get("/generateCharacter")).andReturn();
//
//        //When&&Then
//        assertThat(generatedCharacter.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
//    }

    @Test
    void test() {
        assertThat(MY_SQL_CONTAINER.isRunning()).isTrue();
    }
}
