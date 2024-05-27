package model.service;

import model.entity.ClienteEntity;
import model.entity.EnderecoEntity;
import model.repository.EnderecoRepository;

public class EnderecoService {

    public static class CriarEndereco {

        private final EnderecoRepository enderecoRepository;

        public CriarEndereco(EnderecoRepository enderecoRepository) {
            this.enderecoRepository = enderecoRepository;
        }

        public EnderecoEntity criarEndereco(Long id, String rua, int numeroCasa, String bairro, String cep, String cidade, ClienteEntity cliente) {
            EnderecoEntity novoEndereco = new EnderecoEntity(id, rua, numeroCasa, bairro, cep, cidade, cliente);
            return (EnderecoEntity) enderecoRepository.create(novoEndereco);
        }
    }
}
