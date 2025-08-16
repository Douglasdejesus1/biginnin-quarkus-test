package com.douglas.resource;
import com.douglas.service.InjectionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/exemplo")
public class InjectionResource {

    @Inject
    InjectionService injectionService; // InjectionPoint vai capturar "injectionService"

    @GET
    public String testeInjectionPoint() {
        return injectionService.dizerDeOndeFoiInjetado();
    }
}
