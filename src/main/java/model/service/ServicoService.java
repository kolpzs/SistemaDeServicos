package model.service;

import model.entity.ClienteEntity;
import model.entity.ServicoEntity;
import model.repository.ServicoRepository;

import java.util.Date;

public class ServicoService {

    public static class CriarServico {

        private final ServicoRepository servicoRepository;

        public CriarServico(ServicoRepository servicoRepository) {
            this.servicoRepository = servicoRepository;
        }

        public ServicoEntity criarServico(Long id, Date dia, boolean ativo, String descricao, ClienteEntity cliente) {
            ServicoEntity novoServico = new ServicoEntity(id, dia, ativo, descricao, cliente);
            return (ServicoEntity) servicoRepository.create(novoServico);
        }
    }
}
