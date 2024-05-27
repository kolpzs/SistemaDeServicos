package model.service;

import model.entity.FuncionarioEntity;
import model.entity.FuncionarioServicoEntity;
import model.entity.ServicoEntity;
import model.repository.FuncionarioServicoRepository;

public class FuncionarioServicoService {

    public static class CriarFuncionarioServico {

        private final FuncionarioServicoRepository funcServRepository;

        public CriarFuncionarioServico(FuncionarioServicoRepository funcServRepository) {
            this.funcServRepository = funcServRepository;
        }

        public FuncionarioServicoEntity criarFuncionarioServico(FuncionarioEntity funcionario, ServicoEntity servico) throws Exception {
            if (funcionario == null || servico == null) {
                throw new Exception("Funcionário ou serviço não podem ser nulos.");
            } else {
                FuncionarioServicoEntity novoFuncServ = new FuncionarioServicoEntity(funcionario, servico);
                return funcServRepository.create(novoFuncServ);
            }
        }
    }
}
