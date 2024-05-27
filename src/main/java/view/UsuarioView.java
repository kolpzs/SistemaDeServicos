package view;

import controller.UsuarioController;
import model.entity.UsuarioEntity;
import model.repository.UsuarioRepository;
import model.service.UsuarioService.CriarUsuario;

import java.util.List;
import java.util.Scanner;

public class UsuarioView {

    private final Scanner scannerUsuario = new Scanner(System.in);

    public void UsuarioMenu() throws Exception {
        int respostaUsuario;

        UsuarioRepository usuarioRepository = new UsuarioRepository();
        CriarUsuario criarUsuarioService = new CriarUsuario(usuarioRepository);
        UsuarioController usuarioController = new UsuarioController(criarUsuarioService);

        do {
            System.out.println("1 - Criar Usuário");
            System.out.println("2 - Alterar Usuário");
            System.out.println("3 - Excluir Usuário");
            System.out.println("4 - Listar Todos Usuários");
            System.out.println("5 - Sair");

            do {
                respostaUsuario = scannerUsuario.nextInt();
                if (respostaUsuario < 1 || respostaUsuario > 5) {
                    System.out.println("Insira uma opção válida.");
                }
            } while (respostaUsuario < 1 || respostaUsuario > 5);

            switch (respostaUsuario) {
                case 1:
                    System.out.println("Selecionado a opção 'Criar Usuário'");

                    System.out.println("Digite o nome do usuário: ");
                    scannerUsuario.nextLine();
                    String nomeUsuario = scannerUsuario.nextLine();
                    System.out.println("Digite a senha do usuário:");
                    String senhaUsuario = scannerUsuario.nextLine();

                    UsuarioEntity novoUsuario = usuarioController.criarUsuario(null, nomeUsuario, senhaUsuario);

                    if (novoUsuario != null) {
                        System.out.println("O usuário foi criado com sucesso!");
                        System.out.println("ID do usuário: " + novoUsuario.getId());
                        System.out.println("Nome do usuário: " + novoUsuario.getUsername());
                        System.out.print("Senha do Usuário: ");
                        for (int i = 0; i < senhaUsuario.length(); i++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    } else {
                        System.out.println("Não foi possível criar o usuário.");
                    }
                    break;

                case 2:
                    System.out.print("Digite o ID do usuário que você deseja alterar: ");
                    Long id = scannerUsuario.nextLong();
                    scannerUsuario.nextLine();
                    UsuarioEntity usuario = (UsuarioEntity) usuarioRepository.findById(id);
                    if (usuario == null) {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }
                    System.out.print("Digite o novo nome de usuário: ");
                    String username = scannerUsuario.nextLine();
                    System.out.print("Digite a nova senha: ");
                    String senha = scannerUsuario.nextLine();
                    usuario.setUsername(username);
                    usuario.setSenha(senha);
                    usuario = (UsuarioEntity) usuarioRepository.updateById(usuario);
                    System.out.println("Usuário atualizado com sucesso: " + usuario.getUsername());
                    break;

                case 3:
                    System.out.print("Digite o ID do usuário que você deseja excluir: ");
                    Long idDelete = scannerUsuario.nextLong();
                    scannerUsuario.nextLine();
                    UsuarioEntity usuarioDelete = (UsuarioEntity) usuarioRepository.delete(idDelete);
                    if (usuarioDelete == null) {
                        System.out.println("Usuário não encontrado.");
                    } else {
                        System.out.println("Usuário excluído com sucesso: " + usuarioDelete.getUsername());
                    }
                    break;

                case 4:
                    List<UsuarioEntity> usuarios = usuarioRepository.findAll();
                    for (UsuarioEntity i : usuarios) {
                        System.out.println("ID: " + i.getId() + ", Username: " + i.getUsername());
                        System.out.println("");
                    }
                    break;

                case 5:
                    System.out.println("Voltando ao menu...");
                    break;
            }

        } while (respostaUsuario != 5);
    }
}
