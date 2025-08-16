package com.douglas.qualifiers.resource;

import com.douglas.qualifiers.service.ChocolateService;
import com.douglas.qualifiers.service.FlavorService;
import com.douglas.qualifiers.service.Rare;
import com.douglas.qualifiers.service.VanillaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Produces;


@Path("/flavor")
public class FlavorResource {

    @Inject
    VanillaService vanilla;

    @Inject
    ChocolateService chocolate;

    @Inject
    @Rare
    FlavorService rareFlavor;

    @GET
    @Path("/vanilla")
    @Produces(MediaType.TEXT_PLAIN)
    public String getVanilla() {
        return vanilla.getFlavor();
    }

    @GET
    @Path("/chocolate")
    @Produces(MediaType.TEXT_PLAIN)
    public String getChocolate() {
        return chocolate.getFlavor();
    }

    @GET
    @Path("/rare")
    @Produces(MediaType.TEXT_PLAIN)
    public String getRare() {
        return rareFlavor.getFlavor();
    }
}
