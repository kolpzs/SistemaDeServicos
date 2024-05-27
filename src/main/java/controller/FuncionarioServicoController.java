package controller;

import model.entity.FuncionarioEntity;
import model.entity.FuncionarioServicoEntity;
import model.entity.ServicoEntity;
import model.service.FuncionarioServicoService.CriarFuncionarioServico;

public class FuncionarioServicoController {

    private final CriarFuncionarioServico criarFuncServService;

    public FuncionarioServicoController(CriarFuncionarioServico criarFuncServService) {
        this.criarFuncServService = criarFuncServService;
    }

    public FuncionarioServicoEntity criarFuncionarioServico(FuncionarioEntity funcionario, ServicoEntity servico) throws Exception {
        if (funcionario == null || servico == null) {
            throw new Exception("Funcionário ou serviço não podem ser nulos.");
        }

        FuncionarioServicoEntity novoFuncServ = criarFuncServService.criarFuncionarioServico(funcionario, servico);
        if (novoFuncServ == null) {
            throw new Exception("Falha ao criar a associação entre funcionário e serviço.");
        }

        return novoFuncServ;
    }
}
