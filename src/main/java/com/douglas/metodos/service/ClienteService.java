package com.douglas.metodos.service;

import com.douglas.metodos.entity.Cliente;
import com.douglas.metodos.entity.ClienteRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ClienteService {
    private final Map<Long, Cliente> clientes = new HashMap<>();
    private Long contadorId = 1L;

    public Cliente salvar(String nome, int idade) {
        Cliente cliente = new Cliente(contadorId++, nome, idade);
        clientes.put(cliente.getId(), cliente);
        return cliente;
    }

    public Cliente buscarPorId(Long id) {
        return clientes.get(id);
    }

    public List<Cliente> listar() {
        return new ArrayList<>(clientes.values());
    }

    public Cliente atualizar(Long id, String nome, int idade) {
        Cliente cliente = clientes.get(id);
        if (cliente != null) {
            cliente.setNome(nome);
            cliente.setIdade(idade);
        }
        return cliente;
    }

    public Cliente atualizarParcial(Long id, ClienteRequestDTO dto) {
        Cliente cliente = clientes.get(id);
        if (cliente != null) {
            if (dto.getNome() != null) {
                cliente.setNome(dto.getNome());
            }
            if (dto.getIdade() != 0) { // cuidado: 0 pode ser idade válida, só exemplo
                cliente.setIdade(dto.getIdade());
            }
        }
        return cliente;
    }

    public boolean deletar(Long id) {
        return clientes.remove(id) != null;
    }
}