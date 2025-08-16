package com.douglas.qualifiers.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ChocolateService implements FlavorService {
    public String getFlavor() { return "Chocolate"; }
}
