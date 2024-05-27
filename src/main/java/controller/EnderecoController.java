package controller;

import model.entity.EnderecoEntity;
import model.entity.ClienteEntity;
import model.service.EnderecoService.CriarEndereco;

public class EnderecoController {

    private final CriarEndereco criarEnderecoService;

    public EnderecoController(CriarEndereco criarEnderecoService) {
        this.criarEnderecoService = criarEnderecoService;
    }

    public EnderecoEntity criarEndereco(Long id, String rua, int numeroCasa, String bairro, String cep, String cidade, ClienteEntity cliente) {
        EnderecoEntity novoEndereco = criarEnderecoService.criarEndereco(id, rua, numeroCasa, bairro, cep, cidade, cliente);
        if (novoEndereco == null) {
            System.out.println("Não foi possível criar o endereço.");
        } else {
            System.out.println("O endereço foi criado com sucesso!");
        }
        return novoEndereco;
    }
}
