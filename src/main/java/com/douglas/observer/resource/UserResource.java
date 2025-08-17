package com.douglas.observer.resource;

import com.douglas.observer.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/users")
public class UserResource {

    @Inject
    UserService userService;

    @GET
    @Path("/create")
    public String create(@QueryParam("name") String name) {
        userService.createUser(name);
        return "Usuário criado: " + name;
    }
}
