package com.douglas.service;

import jakarta.enterprise.context.Dependent;

@Dependent
public class DependentService {
    private int counter = 0;
    public int increment() {
        counter++;
        return counter;
    }
}
