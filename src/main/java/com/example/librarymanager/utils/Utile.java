package com.example.librarymanager.utils;


import com.example.librarymanager.models.Exemplu;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public abstract class Utile {



    @Bean
    Exemplu createEx(){

        return new Exemplu();
    }
}
