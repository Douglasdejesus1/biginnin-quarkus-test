package com.douglas.qualifiers.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Rare
public class SaffronService implements FlavorService {
    public String getFlavor() { return "Saffron"; }
}
