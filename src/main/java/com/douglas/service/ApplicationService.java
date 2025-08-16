package com.douglas.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApplicationService {


    private int counter = 0;

    public int increment() {
        counter++;
        return counter;
    }

    @PostConstruct
    void init() {
        System.out.println("➡️ @PostConstruct: Bean inicializado!");
    }

    @PreDestroy
    void destroy() {
        System.out.println("AppScopedService será destruído!");
        // aqui você poderia liberar recursos se houvesse algo a fechar
    }
}
