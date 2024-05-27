package model.service;

import model.entity.FuncionarioEntity;
import model.entity.UsuarioEntity;
import model.repository.FuncionarioRepository;

public class FuncionarioService {

    public static class CriarFuncionario {

        private final FuncionarioRepository funcionarioRepository;

        public CriarFuncionario(FuncionarioRepository funcionarioRepository) {
            this.funcionarioRepository = funcionarioRepository;
        }

        public FuncionarioEntity criarFuncionario(Long id, String nome, String cpf, String telefone, Boolean ativo, UsuarioEntity usuario) throws Exception {
            if (funcionarioRepository.findByNome(nome) != null) {
                throw new Exception("Nome do funcionário já existe.");
            } else {
                FuncionarioEntity novoFuncionario = new FuncionarioEntity(id, nome, cpf, telefone, ativo, usuario);
                return (FuncionarioEntity) funcionarioRepository.create(novoFuncionario);
            }
        }
    }
}
