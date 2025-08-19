package com.douglas.contextos.resource;

import com.douglas.contextos.service.ApplicationService;
import com.douglas.contextos.service.DependentService;
import com.douglas.contextos.service.SessionService;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/session")
public class SessionRessorce {
    @Inject
    SessionService appService;


    @GET
    @Path("/cook")
    @Produces(MediaType.TEXT_PLAIN)
    public Response appScopedCounter(@Context HttpServletRequest request) {
        //request.getSession(true);
        int value = appService.increment();
        return Response.ok("Contador da sessão: " + value).build();
    }




}