package com.douglas.service;

import io.quarkus.runtime.Startup;
import jakarta.inject.Singleton;

import java.sql.SQLOutput;

@Singleton
@Startup
public class SingletonService {

    public SingletonService() {
//        throw new RuntimeException("Singleton Criado");
        System.out.println("Singleton Criando aquiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");



    }
    private int counter = 0;
    public int increment() {
        counter++;
        return counter;
    }
}
