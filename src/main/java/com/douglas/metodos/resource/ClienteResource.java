package com.douglas.metodos.resource;

import com.douglas.metodos.entity.Cliente;
import com.douglas.metodos.entity.ClienteRequestDTO;
import com.douglas.metodos.entity.ClienteResponseDTO;
import com.douglas.metodos.service.ClienteService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    private final ClienteService service;

    public ClienteResource(ClienteService service) {
        this.service = service;
    }

    @POST
    public Response criar(ClienteRequestDTO dto) {
        Cliente cliente = service.salvar(dto.getNome(), dto.getIdade());
        return Response.status(Response.Status.CREATED)
                .entity(new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getIdade()))
                .build();
    }

    @GET
    public List<ClienteResponseDTO> listar() {
        return service.listar().stream()
                .map(c -> new ClienteResponseDTO(c.getId(), c.getNome(), c.getIdade()))
                .collect(Collectors.toList());
    }

    @GET
    @Path("{id}")
    public Response buscar(@PathParam("id") Long id) throws Exception {
        Cliente cliente = service.buscarPorId(id);
        if (cliente == null) {
            throw new Exception();
            //return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getIdade())).build();
    }

    @PUT
    @Path("{id}")
    public Response atualizar(@PathParam("id") Long id, ClienteRequestDTO dto) {
        Cliente cliente = service.atualizar(id, dto.getNome(), dto.getIdade());
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getIdade())).build();
    }

    @PATCH
    @Path("{id}")
    public Response atualizarParcial(@PathParam("id") Long id, ClienteRequestDTO dto) {
        Cliente cliente = service.atualizarParcial(id, dto);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getIdade())).build();
    }

    @DELETE
    @Path("{id}")
    public Response deletar(@PathParam("id") Long id) {
        boolean removido = service.deletar(id);
        return removido ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
    @HEAD
    @Path("{id}")
    public Response verificar(@PathParam("id") Long id) {
        Cliente cliente = service.buscarPorId(id);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        // Retorna 200 OK mas sem corpo
        return Response.ok().build();
    }
// -----------------------
    // Exemplos de parâmetros
    // -----------------------

    // @QueryParam → usado para filtros ou paginação em query string
    // Exemplo: GET /clientes/filtro?idade=30
    // Exemplo se tivesse mais de um filtro: GET /clientes/filtro?idade=30&nome=Maria
    @GET
    @Path("/filtro")
    public Response filtrarPorIdade(@QueryParam("idade") int idade) {
        List<ClienteResponseDTO> clientes = service.listar().stream()
                .filter(c -> c.getIdade() == idade)
                .map(c -> new ClienteResponseDTO(c.getId(), c.getNome(), c.getIdade()))
                .collect(Collectors.toList());
        return Response.ok(clientes).build();
    }

    // @MatrixParam → parâmetros no estilo matrix (pouco usado, mas suportado)
    // Exemplo: GET /clientes/matrix;idade=25;nome=Maria
    @GET
    @Path("/matrix")
    public Response filtrarMatrix(@MatrixParam("idade") int idade, @MatrixParam("nome") String nome) {
        List<ClienteResponseDTO> clientes = service.listar().stream()
                .filter(c -> c.getIdade() == idade && c.getNome().equalsIgnoreCase(nome))
                .map(c -> new ClienteResponseDTO(c.getId(), c.getNome(), c.getIdade()))
                .collect(Collectors.toList());
        return Response.ok(clientes).build();
    }

    // @FormParam → usado para consumir application/x-www-form-urlencoded
    // Exemplo: POST /clientes/form (Content-Type: application/x-www-form-urlencoded)
    // Body: nome=Joao&idade=22
    @POST
    @Path("/form")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response criarViaForm(@FormParam("nome") String nome, @FormParam("idade") int idade) {
        Cliente cliente = service.salvar(nome, idade);
        return Response.status(Response.Status.CREATED)
                .entity(new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getIdade()))
                .build();
    }

    // @HeaderParam → captura valores de cabeçalhos HTTP
    // Exemplo: GET /clientes/header   com header: X-Request-ID: 123
    @GET
    @Path("/header")
    public Response verificarHeader(@HeaderParam("X-Request-ID") String requestId) {
        return Response.ok("Request recebido com ID: " + requestId).build();
    }

    // @BeanParam → agrupa vários parâmetros em um objeto
    // Exemplo: GET /clientes/bean?idade=30&nome=Maria
    public static class ClienteFiltroBean {
        @QueryParam("idade")
        public int idade;

        @QueryParam("nome")
        public String nome;
    }

    @GET
    @Path("/bean")
    public Response filtrarComBean(@BeanParam ClienteFiltroBean filtro) {
        List<ClienteResponseDTO> clientes = service.listar().stream()
                .filter(c -> (filtro.idade == 0 || c.getIdade() == filtro.idade) &&
                        (filtro.nome == null || c.getNome().equalsIgnoreCase(filtro.nome)))
                .map(c -> new ClienteResponseDTO(c.getId(), c.getNome(), c.getIdade()))
                .collect(Collectors.toList());
        return Response.ok(clientes).build();
    }
}