package com.douglas.interceptor.service;

import jakarta.enterprise.context.ApplicationScoped;

@Audited // herdado pelas subclasses
@ApplicationScoped
public class BaseService {
    public void commonLogic() {
        System.out.println("Executando lógica comum...");
    }
}
