package com.douglas.service;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class RequestService {
    private int counter = 0;

    public int increment() {
        counter++;
        return counter;
    }
}
