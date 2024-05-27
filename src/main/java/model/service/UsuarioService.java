package model.service;

import model.entity.UsuarioEntity;
import model.repository.UsuarioRepository;

public class UsuarioService {

    public static class CriarUsuario {

        private final UsuarioRepository usuarioRepository;

        public CriarUsuario(UsuarioRepository usuarioRepository) {
            this.usuarioRepository = usuarioRepository;
        }

        public UsuarioEntity criarUsuario(Long id, String username, String senha) throws Exception {
            if (usuarioRepository.findByUsername(username) != null) {
                throw new Exception("Nome de usuário já existe.");
            } else if (senha.equals(username)) {
                throw new Exception("Senha não pode ser igual ao nome de usuário.");
            } else {
                UsuarioEntity novoUsuario = new UsuarioEntity(id, username, senha);
                return (UsuarioEntity) usuarioRepository.create(novoUsuario);
            }
        }
    }
}
