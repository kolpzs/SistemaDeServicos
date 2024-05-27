package view;

import controller.EnderecoController;
import model.entity.EnderecoEntity;
import model.entity.ClienteEntity;
import model.repository.ClienteRepository;
import model.repository.EnderecoRepository;
import model.service.EnderecoService.CriarEndereco;

import java.util.List;
import java.util.Scanner;

public class EnderecoView {

    private final Scanner scannerEndereco = new Scanner(System.in);

    public void EnderecoMenu() throws Exception {
        int respostaEndereco;

        ClienteRepository clienteRepository = new ClienteRepository();
        EnderecoRepository enderecoRepository = new EnderecoRepository();
        CriarEndereco criarEnderecoService = new CriarEndereco(enderecoRepository);
        EnderecoController enderecoController = new EnderecoController(criarEnderecoService);

        do {
            System.out.println("1 - Criar Endereço");
            System.out.println("2 - Alterar Endereço");
            System.out.println("3 - Excluir Endereço");
            System.out.println("4 - Sair");

            do {
                respostaEndereco = scannerEndereco.nextInt();
                if (respostaEndereco < 1 || respostaEndereco > 4) {
                    System.out.println("Insira uma opção válida.");
                }
            } while (respostaEndereco < 1 || respostaEndereco > 5);

            switch (respostaEndereco) {
                case 1:
                    System.out.println("Selecionado a opção 'Criar Endereço'");

                    System.out.println("Digite a rua do endereço: ");
                    scannerEndereco.nextLine();
                    String ruaEndereco = scannerEndereco.nextLine();
                    System.out.println("Digite o número da casa:");
                    int numeroCasa = scannerEndereco.nextInt();
                    scannerEndereco.nextLine();
                    System.out.println("Digite o bairro:");
                    String bairroEndereco = scannerEndereco.nextLine();
                    System.out.println("Digite o CEP:");
                    String cepEndereco = scannerEndereco.nextLine();
                    System.out.println("Digite a cidade:");
                    String cidadeEndereco = scannerEndereco.nextLine();
                    System.out.println("Digite o ID do cliente:");
                    Long idCliente = scannerEndereco.nextLong();
                    scannerEndereco.nextLine();
                    ClienteEntity cliente = (ClienteEntity) clienteRepository.findById(idCliente);

                    EnderecoEntity novoEndereco = enderecoController.criarEndereco(null, ruaEndereco, numeroCasa, bairroEndereco, cepEndereco, cidadeEndereco, cliente);

                    if (novoEndereco != null) {
                        System.out.println("O endereço foi criado com sucesso!");
                        System.out.println("ID do endereço: " + novoEndereco.getId());
                        System.out.println("Rua: " + novoEndereco.getRua());
                        System.out.println("Número da casa: " + novoEndereco.getNumeroCasa());
                        System.out.println("Bairro: " + novoEndereco.getBairro());
                        System.out.println("CEP: " + novoEndereco.getCep());
                        System.out.println("Cidade: " + novoEndereco.getCidade());
                    } else {
                        System.out.println("Não foi possível criar o endereço.");
                    }
                    break;

                case 2:
                    System.out.println("Selecionado a opção 'Alterar Endereço'");

                    System.out.print("Digite o ID do endereço que você deseja alterar: ");
                    Long idEndereco = scannerEndereco.nextLong();
                    scannerEndereco.nextLine();
                    EnderecoEntity endereco = (EnderecoEntity) enderecoRepository.findById(idEndereco);
                    if (endereco == null) {
                        System.out.println("Endereço não encontrado.");
                        break;
                    }
                    System.out.print("Digite a nova rua do endereço: ");
                    String novaRua = scannerEndereco.nextLine();
                    System.out.print("Digite o novo número da casa: ");
                    int novoNumeroCasa = scannerEndereco.nextInt();
                    scannerEndereco.nextLine();
                    System.out.print("Digite o novo bairro: ");
                    String novoBairro = scannerEndereco.nextLine();
                    System.out.print("Digite o novo CEP: ");
                    String novoCep = scannerEndereco.nextLine();
                    System.out.print("Digite a nova cidade: ");
                    String novaCidade = scannerEndereco.nextLine();

                    endereco.setRua(novaRua);
                    endereco.setNumeroCasa(novoNumeroCasa);
                    endereco.setBairro(novoBairro);
                    endereco.setCep(novoCep);
                    endereco.setCidade(novaCidade);

                    endereco = (EnderecoEntity) enderecoRepository.updateById(endereco);
                    System.out.println("Endereço atualizado com sucesso: " + endereco.getRua());
                    break;

                case 3:
                    System.out.println("Selecionado a opção 'Excluir Endereço'");

                    System.out.print("Digite o ID do endereço que você deseja excluir: ");
                    Long idEnderecoExcluir = scannerEndereco.nextLong();
                    scannerEndereco.nextLine();
                    EnderecoEntity enderecoExcluir = (EnderecoEntity) enderecoRepository.findById(idEnderecoExcluir);
                    if (enderecoExcluir == null) {
                        System.out.println("Endereço não encontrado.");
                        break;
                    }

                    enderecoExcluir = (EnderecoEntity) enderecoRepository.delete(enderecoExcluir.getId());
                    if (enderecoExcluir == null) {
                        System.out.println("Não foi possível excluir o endereço.");
                    } else {
                        System.out.println("Endereço excluído com sucesso: " + enderecoExcluir.getRua());
                    }
                    break;

                case 4:
                    System.out.println("Voltando ao menu...");
                    break;
            }

        } while (respostaEndereco != 4);
    }
}
