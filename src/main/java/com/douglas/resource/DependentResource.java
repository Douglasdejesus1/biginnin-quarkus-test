package com.douglas.resource;

import com.douglas.service.ApplicationService;
import com.douglas.service.DependentService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
@Path("/dependent")
public class DependentResource {
        @Inject
        DependentService depService;

        @Inject
        ApplicationService appService;
        @GET
        @Path("/dep")
        @Produces(MediaType.TEXT_PLAIN)
        public String appScopedCounter() {
            return "Dependent counter: " + depService.increment();
        }
    @GET
    @Path("/app")
    @Produces(MediaType.TEXT_PLAIN)
    public String appScopedCounter2() {
        return "ApplicationScoped counter: " + appService.increment();
    }

    }