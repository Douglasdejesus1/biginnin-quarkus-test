package com.douglas.metodos.service;

import jakarta.ws.rs.ext.*;
import org.jboss.logging.Logger;

import java.io.*;

@Provider
public class LoggingInterceptor implements ReaderInterceptor, WriterInterceptor {

    private static final Logger LOG = Logger.getLogger(LoggingInterceptor.class);

    // Intercepta leitura do corpo da requisição (JSON -> Objeto Java)
    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException {
        InputStream inputStream = context.getInputStream();

        // Lê o corpo em memória
        String body = new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .reduce("", (acc, line) -> acc + line + "\n");

        LOG.infof("📥 Corpo da requisição recebido: %s", body.trim());

        // Recoloca o InputStream para que JAX-RS consiga desserializar normalmente
        context.setInputStream(new ByteArrayInputStream(body.getBytes()));

        return context.proceed();
    }

    // Intercepta escrita do corpo da resposta (Objeto Java -> JSON)
    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException {
        LOG.infof("📤 Enviando resposta: %s", context.getEntity());

        context.proceed(); // continua fluxo normal
    }
}
