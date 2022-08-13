package com.fethitunali.todoapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperBean {

    @Bean
    public ModelMapperBean modelMapper(){
        return new ModelMapperBean();
    }
}
