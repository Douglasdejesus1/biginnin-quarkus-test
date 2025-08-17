package com.douglas.defaultbean.service;

import io.quarkus.arc.DefaultBean;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@DefaultBean // só será usado se não houver outro GreetingService
public class DefaultGreetingService implements GreetingService {
    @Override
    public String greet(String name) {
        return "Hello (default), " + name + "!";
    }
}
