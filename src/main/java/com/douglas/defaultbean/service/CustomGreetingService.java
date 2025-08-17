package com.douglas.defaultbean.service;

import jakarta.enterprise.context.ApplicationScoped;

//@ApplicationScoped
public class CustomGreetingService implements GreetingService {
    @Override
    public String greet(String name) {
        return "Hi (custom), " + name + "!";
    }
}
