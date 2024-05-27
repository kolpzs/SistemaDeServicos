package controller;

import model.entity.ClienteEntity;
import model.service.ClienteService.CriarCliente;

public class ClienteController {

    private final CriarCliente criarClienteService;

    public ClienteController(CriarCliente criarClienteService) {
        this.criarClienteService = criarClienteService;
    }

    public ClienteEntity criarCliente(Long id, String nome, String cpf, String telefone) throws Exception {
        // Criar o novo cliente
        ClienteEntity novoCliente = criarClienteService.criarCliente(id, nome, cpf, telefone);
        if (novoCliente == null) {
            throw new Exception("Erro ao criar o cliente.");
        }

        return novoCliente;
    }
}