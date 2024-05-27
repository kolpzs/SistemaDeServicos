package view;

import controller.FuncionarioController;
import model.entity.FuncionarioEntity;
import model.entity.UsuarioEntity;
import model.repository.FuncionarioRepository;
import model.repository.UsuarioRepository;
import model.service.FuncionarioService.CriarFuncionario;

import java.util.List;
import java.util.Scanner;

public class FuncionarioView {

    private final Scanner scannerFuncionario = new Scanner(System.in);

    public void FuncionarioMenu() throws Exception {
        int respostaFuncionario;

        UsuarioRepository usuarioRepository = new UsuarioRepository();
        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
        CriarFuncionario criarFuncionarioService = new CriarFuncionario(funcionarioRepository);
        FuncionarioController funcionarioController = new FuncionarioController(criarFuncionarioService);

        do {
            System.out.println("1 - Criar Funcionário");
            System.out.println("2 - Alterar Funcionário");
            System.out.println("3 - Excluir Funcionário");
            System.out.println("4 - Listar Todos Funcionários");
            System.out.println("5 - Ativar/Desativar Funcionário");
            System.out.println("6 - Sair");

            do {
                respostaFuncionario = scannerFuncionario.nextInt();
                if (respostaFuncionario < 1 || respostaFuncionario > 6) {
                    System.out.println("Insira uma opção válida.");
                }
            } while (respostaFuncionario < 1 || respostaFuncionario > 6);

            switch (respostaFuncionario) {
                case 1:
                    System.out.println("Selecionado a opção 'Criar Funcionário'");

                    System.out.println("Digite o nome do funcionário: ");
                    scannerFuncionario.nextLine();  // Consumir o restante da linha
                    String nomeFuncionario = scannerFuncionario.nextLine();
                    System.out.println("Digite o CPF do funcionário:");
                    String cpfFuncionario = scannerFuncionario.nextLine();
                    System.out.println("Digite o telefone do funcionário:");
                    String telefoneFuncionario = scannerFuncionario.nextLine();
                    System.out.println("Digite o ID do usuário associado ao funcionário:");
                    Long idUsuario = scannerFuncionario.nextLong();
                    scannerFuncionario.nextLine(); // Consumir o restante da linha
                    UsuarioEntity usuario = (UsuarioEntity) usuarioRepository.findById(idUsuario);
                    if (usuario == null) {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }

                    FuncionarioEntity novoFuncionario = funcionarioController.criarFuncionario(null, nomeFuncionario, cpfFuncionario, telefoneFuncionario, true, usuario);

                    if (novoFuncionario != null) {
                        System.out.println("O funcionário foi criado com sucesso!");
                        System.out.println("ID do funcionário: " + novoFuncionario.getId());
                        System.out.println("Nome do funcionário: " + novoFuncionario.getNome());
                    } else {
                        System.out.println("Não foi possível criar o funcionário.");
                    }
                    break;

                case 2:
                    System.out.print("Digite o ID do funcionário que você deseja alterar: ");
                    Long id = scannerFuncionario.nextLong();
                    scannerFuncionario.nextLine(); // Consume newline left-over
                    FuncionarioEntity funcionario = (FuncionarioEntity) funcionarioRepository.findById(id);
                    if (funcionario == null) {
                        System.out.println("Funcionário não encontrado.");
                        break;
                    }
                    System.out.print("Digite o novo nome do funcionário: ");
                    String nome = scannerFuncionario.nextLine();
                    System.out.print("Digite o novo CPF do funcionário: ");
                    String cpf = scannerFuncionario.nextLine();
                    System.out.print("Digite o novo telefone do funcionário: ");
                    String telefone = scannerFuncionario.nextLine();
                    funcionario.setNome(nome);
                    funcionario.setCpf(cpf);
                    funcionario.setTelefone(telefone);
                    funcionario = (FuncionarioEntity) funcionarioRepository.updateById(funcionario);
                    System.out.println("Funcionário atualizado com sucesso: " + funcionario.getNome());
                    break;

                case 3:
                    System.out.print("Digite o ID do funcionário que você deseja excluir: ");
                    Long idDelete = scannerFuncionario.nextLong();
                    scannerFuncionario.nextLine(); // Consume newline left-over
                    FuncionarioEntity funcionarioDelete = (FuncionarioEntity) funcionarioRepository.delete(idDelete);
                    if (funcionarioDelete == null) {
                        System.out.println("Funcionário não encontrado.");
                    } else {
                        System.out.println("Funcionário excluído com sucesso: " + funcionarioDelete.getNome());
                    }
                    break;

                case 4:
                    List<FuncionarioEntity> funcionarios = funcionarioRepository.findAll();
                    for (FuncionarioEntity i : funcionarios) {
                        System.out.println("ID: " + i.getId() + ", Nome: " + i.getNome() + ", Usuario: " + i.getUsuario().getUsername());
                        System.out.println("");
                    }
                    break;

                case 5:
                    System.out.print("Digite o ID do funcionário que você deseja ativar/desativar: ");
                    Long idAtivarDesativar = scannerFuncionario.nextLong();
                    scannerFuncionario.nextLine(); // Consume newline left-over
                    FuncionarioEntity funcionarioAtivarDesativar = (FuncionarioEntity) funcionarioRepository.findById(idAtivarDesativar);
                    if (funcionarioAtivarDesativar == null) {
                        System.out.println("Funcionário não encontrado.");
                    } else {
                        funcionarioAtivarDesativar.setAtivo(!funcionarioAtivarDesativar.getAtivo());
                        funcionarioAtivarDesativar = (FuncionarioEntity) funcionarioRepository.updateById(funcionarioAtivarDesativar);
                        System.out.println("Funcionário " + (funcionarioAtivarDesativar.getAtivo() ? "ativado" : "desativado") + " com sucesso: " + funcionarioAtivarDesativar.getNome());
                    }
                    break;

                case 6:
                    System.out.println("Voltando ao menu...");
                    break;
            }

        } while (respostaFuncionario != 6);
    }
}
