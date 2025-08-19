package com.douglas.async.resouce;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Path("/mutiny")
public class MutinyResource {

    private static final Logger LOG = Logger.getLogger(MutinyResource.class);

    @GET
    @Path("/async")
    public Uni<Response> async() {
        String requestThread = Thread.currentThread().getName();
        LOG.infof("➡️ [UNI] Iniciando requisição na thread: %s", requestThread);

        return Uni.createFrom()
                .item(() -> {
                    String workerThread = Thread.currentThread().getName();
                    LOG.infof("⚙️ [UNI] Executando tarefa em thread: %s", workerThread);

                    try {
                        Thread.sleep(5000); // simula operação demorada
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    return Response.ok("Finalizado com Mutiny!").build();
                })
                .runSubscriptionOn(io.smallrye.mutiny.infrastructure.Infrastructure.getDefaultExecutor())
                .invoke(() -> {
                    LOG.info("⬅️ [UNI] Resposta concluída!");
                });
    }
}
