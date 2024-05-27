package model.service;

import model.entity.ClienteEntity;
import model.repository.ClienteRepository;

public class ClienteService {

    public static class CriarCliente {

        private final ClienteRepository clienteRepository;

        public CriarCliente(ClienteRepository clienteRepository) {
            this.clienteRepository = clienteRepository;
        }

        public ClienteEntity criarCliente(Long id, String nome, String cpf, String telefone) throws Exception {
            if (clienteRepository.findByNome(nome) != null) {
                throw new Exception("Nome do cliente já existe.");
            } else {
                ClienteEntity novoCliente = new ClienteEntity(id, nome, cpf, telefone);
                return (ClienteEntity) clienteRepository.create(novoCliente);
            }
        }
    }
}
