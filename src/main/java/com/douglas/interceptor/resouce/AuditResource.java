package com.douglas.interceptor.resouce;

import com.douglas.interceptor.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/audit")
public class AuditResource {

    @Inject
    UserService userService;

    @GET
    @Path("/test")
    public Response testAudit() {
        userService.commonLogic();
        userService.processUser();
        return Response.ok("Métodos executados com auditoria! Veja o log.").build();
    }
}
