package com.douglas.resource;

import com.douglas.service.ApplicationService;
import com.douglas.service.DependentService;
import com.douglas.service.RequestService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
@Path("/application")
public class ApplicationResorce {
    @Inject
    ApplicationService appService;

    @Inject
    DependentService depService;
    @GET
    @Path("/app")
    @Produces(MediaType.TEXT_PLAIN)
    public String appScopedCounter() {
        return "ApplicationScoped counter: " + appService.increment();
    }

    @GET
    @Path("/dep")
    @Produces(MediaType.TEXT_PLAIN)
    public String appScopedCounterDep() {
        return "DependentScoped counter: " + appService.increment();
    }


}