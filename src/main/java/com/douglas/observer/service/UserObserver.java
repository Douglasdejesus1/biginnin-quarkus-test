package com.douglas.observer.service;

import com.douglas.observer.entity.UserCreatedEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.ObservesAsync;

@ApplicationScoped
public class UserObserver {

    // Este método será chamado automaticamente quando o evento for disparado
    //public void onUserCreated(@Observes UserCreatedEvent event) {
    public void onUserCreated(@ObservesAsync UserCreatedEvent event) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("🎉 Usuário criado: " + event.getUsername());
    }
}
