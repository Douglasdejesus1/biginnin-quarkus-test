package com.douglas.interceptor.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService extends BaseService {
    public void processUser() {
        System.out.println("Processando usuário...");
    }
}
