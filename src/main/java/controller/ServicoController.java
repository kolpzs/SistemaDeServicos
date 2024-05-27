package controller;

import model.entity.ServicoEntity;
import model.entity.ClienteEntity;
import model.service.ServicoService.CriarServico;

import java.util.Date;

public class ServicoController {

    private final CriarServico criarServicoService;

    public ServicoController(CriarServico criarServicoService) {
        this.criarServicoService = criarServicoService;
    }

    public ServicoEntity criarServico(Long id, Date dia, boolean ativo, String descricao, ClienteEntity cliente) {
        ServicoEntity novoServico = criarServicoService.criarServico(id, dia, ativo, descricao, cliente);
        if (novoServico == null) {
            System.out.println("Não foi possível criar o serviço.");
        } else {
            System.out.println("O serviço foi criado com sucesso!");
        }
        return novoServico;
    }
}
