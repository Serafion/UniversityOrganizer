package com.example.universityogranizer.studentservice;

import com.example.universityogranizer.studentservice.repository.StudentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentServiceConfiguration {

    @Bean
    public StudentServiceFacade studentServiceFacade(StudentDao studentDao, StudentMapperImpl studentMapper){
        return new StudentServiceFacade(studentDao, studentMapper);
    }

    public  StudentServiceFacade studentServiceFacadeTest(StudentRepository studentRepository){
        StudentMapperImpl studentMapper = new StudentMapperImpl();
        StudentDao studentDao = new StudentDao(studentRepository, studentMapper);
        return new StudentServiceFacade(studentDao,studentMapper);
    }
}
