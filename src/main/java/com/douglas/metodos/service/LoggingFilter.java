package com.douglas.metodos.service;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final Logger LOG = Logger.getLogger(LoggingFilter.class);

    // Marca o tempo em que a requisição começou
    @Override
    public void filter(ContainerRequestContext requestContext) {
        long startTime = System.currentTimeMillis();
        requestContext.setProperty("start-time", startTime);

        LOG.infof("➡️ Requisição recebida: %s %s",
                requestContext.getMethod(),
                requestContext.getUriInfo().getRequestUri());

        // (Opcional) logar headers
        requestContext.getHeaders().forEach((k, v) -> LOG.debugf("Header %s: %s", k, v));
    }

    // Loga a resposta e calcula tempo de execução
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        long startTime = (long) requestContext.getProperty("start-time");
        long duration = System.currentTimeMillis() - startTime;

        LOG.infof("⬅️ Resposta enviada: %d %s (em %d ms)",
                responseContext.getStatus(),
                responseContext.getStatusInfo().getReasonPhrase(),
                duration);

        // (Opcional) adicionar header customizado
        responseContext.getHeaders().add("X-Response-Time", duration + "ms");
    }
}
