package com.douglas.observer.service;

import com.douglas.observer.entity.UserCreatedEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {

    @Inject
    Event<UserCreatedEvent> userCreatedEvent;

    public void createUser(String username) {
        System.out.println("Criando usuário: " + username);

        // Dispara o evento
        userCreatedEvent.fireAsync(new UserCreatedEvent(username));
    }
}
