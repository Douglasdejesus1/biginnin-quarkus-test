package com.douglas.async.resouce;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.jboss.logging.Logger;

@Path("/async")
public class AsyncResource {

    private static final Logger LOG = Logger.getLogger(AsyncResource.class);

    @Inject
    ManagedExecutor executor;

    @GET
    public void getAsync(@Suspended AsyncResponse asyncResponse) {
        String requestThread = Thread.currentThread().getName();
        LOG.infof("➡️ Iniciando requisição na thread: %s", requestThread);

        executor.runAsync(() -> {
            String workerThread = Thread.currentThread().getName();
            LOG.infof("⚙️ Executando tarefa assíncrona na thread: %s", workerThread);

            try {
                Thread.sleep(2000); // simula tarefa pesada
                asyncResponse.resume(Response.ok("Processado com sucesso!").build());
            } catch (Exception e) {
                asyncResponse.resume(Response.serverError().entity(e.getMessage()).build());
            }
        });

        LOG.infof("✅ Requisição suspensa, thread %s já foi liberada", requestThread);
    }
}
