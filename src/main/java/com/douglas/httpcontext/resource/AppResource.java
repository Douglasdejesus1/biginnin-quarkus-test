package com.douglas.httpcontext.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.ArrayList;


@Path("/app")
@ApplicationScoped
public class AppResource {

    @Inject
    ServletContext servletContext;

    @GET
    @Path("/info")
    public String getAppInfo() {
        // Nome da aplicação (web.xml ou contexto raiz)
        String contextPath = servletContext.getContextPath();
        String clasLoader = String.valueOf(servletContext.getClassLoader());

        // Versão do servidor
        String serverInfo = servletContext.getServerInfo();

        // Diretório real do app (deployment)
        String realPath = servletContext.getRealPath("/");

        return "ContextPath: " + contextPath +
                "\nServerInfo: " + serverInfo +
                "\nClassLoader: " + clasLoader +
                "\nRealPath: " + realPath;
    }
}

