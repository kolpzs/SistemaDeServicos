package controller;

import model.entity.UsuarioEntity;
import model.service.UsuarioService.CriarUsuario;

public class UsuarioController {

    private final CriarUsuario criarUsuarioService;

    public UsuarioController(CriarUsuario criarUsuarioService) {
        this.criarUsuarioService = criarUsuarioService;
    }

    public UsuarioEntity criarUsuario(Long id, String username, String senha) throws Exception {
        if (senha.equals(username)) {
            throw new Exception("Senha não pode ser igual ao nome de usuário.");
        }

        UsuarioEntity novoUsuario = criarUsuarioService.criarUsuario(id, username, senha);
        if (novoUsuario == null) {
            throw new Exception("Nome de usuário já existe.");
        }

        return novoUsuario;
    }
}
