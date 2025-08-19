package com.douglas.contextos.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;

//@SessionScoped
@RequestScoped
public class SessionService implements Serializable {


    private int counter = 0;

    public int increment() {
        counter++;
        return counter;
    }

    @PostConstruct
    void init() {
        System.out.println("➡️ @PostConstruct: Bean  session inicializado!");
    }

    @PreDestroy
    void destroy() {
        System.out.println("Session será destruído!");
        // aqui você poderia liberar recursos se houvesse algo a fechar
    }
}
