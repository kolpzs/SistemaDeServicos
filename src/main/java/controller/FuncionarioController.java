package controller;

import model.entity.FuncionarioEntity;
import model.entity.UsuarioEntity;
import model.service.FuncionarioService.CriarFuncionario;

public class FuncionarioController {

    private final CriarFuncionario criarFuncionarioService;

    public FuncionarioController(CriarFuncionario criarFuncionarioService) {
        this.criarFuncionarioService = criarFuncionarioService;
    }

    public FuncionarioEntity criarFuncionario(Long id, String nome, String cpf, String telefone, Boolean ativo, UsuarioEntity usuario) throws Exception {
        FuncionarioEntity novoFuncionario = criarFuncionarioService.criarFuncionario(id, nome, cpf, telefone, ativo, usuario);
        if (novoFuncionario == null) {
            throw new Exception("Erro ao criar o funcionário.");
        }

        return novoFuncionario;
    }
}
