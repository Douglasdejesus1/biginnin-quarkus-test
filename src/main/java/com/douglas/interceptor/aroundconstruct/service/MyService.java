package com.douglas.interceptor.aroundconstruct.service;

import jakarta.enterprise.context.ApplicationScoped;

@Monitored
@ApplicationScoped
public class MyService {

    public MyService() {
        System.out.println("Construtor do MyService executado!");
    }

    public void doWork() {
        System.out.println("Executando doWork()");
    }
}