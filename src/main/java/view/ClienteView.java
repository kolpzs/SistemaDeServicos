package view;

import controller.ClienteController;
import controller.EnderecoController;
import model.entity.ClienteEntity;
import model.entity.EnderecoEntity;
import model.repository.ClienteRepository;
import model.repository.EnderecoRepository;
import model.service.ClienteService.CriarCliente;
import model.service.EnderecoService.CriarEndereco;

import java.util.List;
import java.util.Scanner;

public class ClienteView {

    private final Scanner scannerCliente = new Scanner(System.in);

    public void ClienteMenu() throws Exception {
        int respostaCliente;

        ClienteRepository clienteRepository = new ClienteRepository();
        CriarCliente criarClienteService = new CriarCliente(clienteRepository);
        ClienteController clienteController = new ClienteController(criarClienteService);

        EnderecoRepository enderecoRepository = new EnderecoRepository();
        CriarEndereco criarEnderecoService = new CriarEndereco(enderecoRepository);
        EnderecoController enderecoController = new EnderecoController(criarEnderecoService);

        do {
            System.out.println("1 - Criar Cliente");
            System.out.println("2 - Alterar Cliente");
            System.out.println("3 - Excluir Cliente");
            System.out.println("4 - Listar Todos Clientes");
            System.out.println("5 - Listar Endereços de um Cliente");
            System.out.println("6 - Sair");

            do {
                respostaCliente = scannerCliente.nextInt();
                if (respostaCliente < 1 || respostaCliente > 6) {
                    System.out.println("Insira uma opção válida.");
                }
            } while (respostaCliente < 1 || respostaCliente > 6);

            switch (respostaCliente) {
                case 1:
                    System.out.println("Selecionado a opção 'Criar Cliente'");

                    System.out.println("Digite o nome do cliente: ");
                    scannerCliente.nextLine();
                    String nomeCliente = scannerCliente.nextLine();
                    System.out.println("Digite o CPF do cliente:");
                    String cpfCliente = scannerCliente.nextLine();
                    System.out.println("Digite o telefone do cliente:");
                    String telefoneCliente = scannerCliente.nextLine();

                    ClienteEntity novoCliente = clienteController.criarCliente(null, nomeCliente, cpfCliente, telefoneCliente);

                    if (novoCliente != null) {
                        System.out.println("O cliente foi criado com sucesso!");
                        System.out.println("ID do cliente: " + novoCliente.getId());
                        System.out.println("Nome do cliente: " + novoCliente.getNome());
                    } else {
                        System.out.println("Não foi possível criar o cliente.");
                    }
                    break;

                case 2:
                    System.out.println("Selecionado a opção 'Alterar Cliente'");

                    System.out.print("Digite o ID do cliente que você deseja alterar: ");
                    Long id = scannerCliente.nextLong();
                    scannerCliente.nextLine(); // Consume newline left-over
                    ClienteEntity cliente = (ClienteEntity) clienteRepository.findById(id);
                    if (cliente == null) {
                        System.out.println("Cliente não encontrado.");
                        break;
                    }
                    System.out.print("Digite o novo nome do cliente: ");
                    String nome = scannerCliente.nextLine();
                    System.out.print("Digite o novo CPF do cliente: ");
                    String cpf = scannerCliente.nextLine();
                    System.out.print("Digite o novo telefone do cliente: ");
                    String telefone = scannerCliente.nextLine();
                    cliente.setNome(nome);
                    cliente.setCpf(cpf);
                    cliente.setTelefone(telefone);
                    cliente = (ClienteEntity) clienteRepository.updateById(cliente);
                    System.out.println("Cliente atualizado com sucesso: " + cliente.getNome());
                    break;

                case 3:
                    System.out.println("Selecionado a opção 'Excluir Cliente'");

                    System.out.print("Digite o ID do cliente que você deseja excluir: ");
                    Long idDelete = scannerCliente.nextLong();
                    scannerCliente.nextLine(); // Consume newline left-over
                    ClienteEntity clienteDelete = (ClienteEntity) clienteRepository.delete(idDelete);
                    if (clienteDelete == null) {
                        System.out.println("Cliente não encontrado.");
                    } else {
                        System.out.println("Cliente excluído com sucesso: " + clienteDelete.getNome());
                    }
                    break;

                case 4:
                    System.out.println("Selecionado a opção 'Listar Todos Clientes'");
                    List<ClienteEntity> clientes = clienteRepository.findAll();
                    for (ClienteEntity i : clientes) {
                        System.out.println("ID: " + i.getId() + ", Nome: " + i.getNome());
                        System.out.println("");
                    }
                    break;

                case 5:
                    System.out.println("Selecionado a opção 'Listar Endereços de um Cliente'");
                    System.out.print("Digite o ID do cliente: ");
                    Long idCliente = scannerCliente.nextLong();
                    scannerCliente.nextLine();
                    ClienteEntity clienteEntity = (ClienteEntity) clienteRepository.findById(idCliente);
                    if (clienteEntity == null) {
                        System.out.println("Cliente não encontrado.");
                        break;
                    }
                    List<EnderecoEntity> enderecos = enderecoRepository.findByCliente(clienteEntity);
                    for (EnderecoEntity endereco : enderecos) {
                        System.out.println("ID: " + endereco.getId() + ", Rua: " + endereco.getRua() + ", Número da casa: " + endereco.getNumeroCasa() + ", Bairro: " + endereco.getBairro() + ", CEP: " + endereco.getCep() + ", Cidade: " + endereco.getCidade());
                        System.out.println("");
                    }
                    break;

                case 6:
                    System.out.println("Voltando ao menu...");
                    break;
            }

        } while (respostaCliente != 6);
    }
}
