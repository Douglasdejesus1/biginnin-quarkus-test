package com.douglas.contextos.resource;

import com.douglas.contextos.service.RequestService;
import com.douglas.contextos.service.SingletonService;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

@Path("/request")
public class RequestResorce {

    @Inject
    RequestService requestService;

    @Inject
    SingletonService service;

    @Context
    UriInfo uriInfo;

    @GET
    @Path("/r")
    @Produces(MediaType.TEXT_PLAIN)
    public String requestScopedCounter() {
        return "RequestScoped counter: " + requestService.increment();

    }
    @GET
    @Path("/s")
    @Named("meuBean")
    @Produces(MediaType.TEXT_PLAIN)
    public String requestScopedCounter2() {
        return "RequestScoped counter: " + service.increment();



    }
}
