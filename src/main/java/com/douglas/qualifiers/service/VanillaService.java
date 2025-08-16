package com.douglas.qualifiers.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VanillaService implements FlavorService {
    public String getFlavor() { return "Vanilla"; }
}

