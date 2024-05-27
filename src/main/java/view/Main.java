package view;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int resposta;

        do {
            System.out.println("1 - Menu Usuário");
            System.out.println("2 - Menu Funcionário");
            System.out.println("3 - Menu Cliente");
            System.out.println("4 - Menu Endereco");
            System.out.println("5 - Menu Servico");
            System.out.println("6 - Sair");

            do {
                resposta = scanner.nextInt();
                if (resposta < 1 || resposta > 6) {
                    System.out.println("Insira uma opção válida.");
                }
            } while (resposta < 1 || resposta > 6);

            switch (resposta) {
                case 1:
                    UsuarioView usuarioView = new UsuarioView();
                    usuarioView.UsuarioMenu();
                    break;

                case 2:
                    FuncionarioView funcionarioView = new FuncionarioView();
                    funcionarioView.FuncionarioMenu();
                    break;

                case 3:
                    ClienteView clienteView = new ClienteView();
                    clienteView.ClienteMenu();
                    break;

                case 4:
                    EnderecoView enderecoView = new EnderecoView();
                    enderecoView.EnderecoMenu();
                    break;

                case 5:
                    ServicoView servicoView = new ServicoView();
                    servicoView.ServicoMenu();
                    break;

                case 6:
                    System.out.println("Obrigado por usar!");
                    break;
            }

        } while (resposta != 6);

        scanner.close();
        System.out.println("Saindo...");
    }
}
