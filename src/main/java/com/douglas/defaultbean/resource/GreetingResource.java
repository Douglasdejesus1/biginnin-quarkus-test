package com.douglas.defaultbean.resource;

import com.douglas.defaultbean.service.GreetingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/greet")
public class GreetingResource {

    @Inject
    GreetingService greetingService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String greet(@QueryParam("name") String name) {
        if (name == null || name.isEmpty()) {
            name = "World";
        }
        return greetingService.greet(name);
    }
}
