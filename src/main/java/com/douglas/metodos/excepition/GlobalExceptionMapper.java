package com.douglas.metodos.excepition;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;


@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOG = Logger.getLogger(String.valueOf(GlobalExceptionMapper.class));

    @Override
    public Response toResponse(Exception exception) {

        // Loga a exceção com stack trace
        LOG.error("Erro capturado pelo ExceptionMapper", exception);

        // Define status HTTP padrão
        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
        String message = "Ocorreu um erro inesperado";

        // Se for exceção customizada
/*        if (exception instanceof AppException appEx) {
            status = appEx.getStatus();
            message = appEx.getMessage();
        }*/
        // Alguns mapeamentos comuns do JAX-RS
        if (exception instanceof jakarta.ws.rs.NotFoundException) {
            status = Response.Status.NOT_FOUND;
            message = "Recurso não encontrado";
        } else if (exception instanceof jakarta.ws.rs.BadRequestException) {
            status = Response.Status.BAD_REQUEST;
            message = exception.getMessage();
        } else if (exception instanceof jakarta.ws.rs.NotAuthorizedException) {
            status = Response.Status.UNAUTHORIZED;
            message = "Não autorizado";
        }

        // Cria a resposta JSON padronizada
        ErrorResponse error = new ErrorResponse(
                message,
                status.getStatusCode(),
                LocalDateTime.now().toString()
        );

        return Response.status(status)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    // Classe interna para padronizar o JSON
    public static class ErrorResponse {
        public String message;
        public int status;
        public String timestamp;

        public ErrorResponse(String message, int status, String timestamp) {
            this.message = message;
            this.status = status;
            this.timestamp = timestamp;
        }
    }
}