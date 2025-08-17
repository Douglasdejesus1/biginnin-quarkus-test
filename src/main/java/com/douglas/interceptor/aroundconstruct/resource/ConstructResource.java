package com.douglas.interceptor.aroundconstruct.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import com.douglas.interceptor.aroundconstruct.service.MyService;

@Path("/construct")
public class ConstructResource {

    @Inject
    MyService service;

    @GET
    public String test() {
        service.doWork();
        return "Check logs!";
    }
}