package com.example.universityogranizer.teacherservice;

import com.example.universityogranizer.teacherservice.repository.TeacherRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeacherServiceConfiguration {

    @Bean
    public TeacherServiceFacade teacherServiceFacade(TeacherDao teacherDao, TeacherMapperImpl teacherMapper){
        return new TeacherServiceFacade(teacherDao, teacherMapper);
    }

    public  TeacherServiceFacade teacherServiceFacadeTest(TeacherRepository teacherRepository){
        TeacherMapperImpl teacherMapper = new TeacherMapperImpl();
        TeacherDao teacherDao = new TeacherDao(teacherRepository, teacherMapper);
        return new TeacherServiceFacade(teacherDao,teacherMapper);
    }
}
