package view;

import controller.ServicoController;
import model.entity.ServicoEntity;
import model.entity.ClienteEntity;
import model.repository.ClienteRepository;
import model.repository.ServicoRepository;
import model.service.ServicoService.CriarServico;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ServicoView {

    private final Scanner scannerServico = new Scanner(System.in);

    public void ServicoMenu() throws Exception {
        int respostaServico;

        ClienteRepository clienteRepository = new ClienteRepository();
        ServicoRepository servicoRepository = new ServicoRepository();
        CriarServico criarEnderecoService = new CriarServico(servicoRepository);
        ServicoController servicoController = new ServicoController(criarEnderecoService);

        do {
            System.out.println("1 - Criar Serviço");
            System.out.println("2 - Alterar Serviço");
            System.out.println("3 - Excluir Serviço");
            System.out.println("4 - Listar Todos Serviços");
            System.out.println("5 - Sair");

            do {
                respostaServico = scannerServico.nextInt();
                if (respostaServico < 1 || respostaServico > 5) {
                    System.out.println("Insira uma opção válida.");
                }
            } while (respostaServico < 1 || respostaServico > 5);

            switch (respostaServico) {
                case 1:
                    System.out.println("Selecionado a opção 'Criar Serviço'");

                    System.out.println("Digite a data do serviço (no formato dd/MM/yyyy): ");
                    scannerServico.nextLine();
                    String dataServico = scannerServico.nextLine();
                    Date dia = new SimpleDateFormat("dd/MM/yyyy").parse(dataServico);
                    System.out.println("Digite a descrição do serviço:");
                    String descricaoServico = scannerServico.nextLine();
                    System.out.println("Digite o ID do cliente:");
                    Long idCliente = scannerServico.nextLong();
                    scannerServico.nextLine();
                    ClienteEntity cliente = (ClienteEntity) clienteRepository.findById(idCliente);

                    ServicoEntity novoServico = servicoController.criarServico(null, dia, true, descricaoServico, cliente);

                    if (novoServico != null) {
                        System.out.println("ID do serviço: " + novoServico.getId());
                        System.out.println("Data do serviço: " + new SimpleDateFormat("dd/MM/yyyy").format(novoServico.getDia()));
                        System.out.println("Descrição do serviço: " + novoServico.getDescricao());
                    } else {
                        System.out.println("Não foi possível criar o serviço.");
                    }
                    break;

                case 2:
                    System.out.println("Selecionado a opção 'Alterar Serviço'");

                    System.out.println("Digite o ID do serviço que deseja alterar:");
                    Long idServico = scannerServico.nextLong();
                    scannerServico.nextLine();

                    ServicoEntity servicoExistente = (ServicoEntity) servicoRepository.findById(idServico);
                    if (servicoExistente == null) {
                        System.out.println("Serviço não encontrado.");
                        break;
                    }

                    System.out.println("Digite a nova data do serviço (no formato dd/MM/yyyy): ");
                    String novaDataServico = scannerServico.nextLine();
                    Date novoDia = new SimpleDateFormat("dd/MM/yyyy").parse(novaDataServico);

                    System.out.println("Digite a nova descrição do serviço:");
                    String novaDescricaoServico = scannerServico.nextLine();

                    System.out.println("Digite o novo ID do cliente:");
                    Long novoIdCliente = scannerServico.nextLong();
                    scannerServico.nextLine();
                    ClienteEntity novoCliente = (ClienteEntity) clienteRepository.findById(novoIdCliente);

                    servicoExistente.setDia(novoDia);
                    servicoExistente.setDescricao(novaDescricaoServico);
                    servicoExistente.setCliente(novoCliente);

                    servicoRepository.updateById(servicoExistente);

                    System.out.println("O serviço foi alterado com sucesso!");
                    System.out.println("ID do serviço: " + servicoExistente.getId());
                    System.out.println("Nova data do serviço: " + new SimpleDateFormat("dd/MM/yyyy").format(servicoExistente.getDia()));
                    System.out.println("Nova descrição do serviço: " + servicoExistente.getDescricao());
                    break;

                case 3:
                    System.out.println("Selecionado a opção 'Excluir Serviço'");

                    System.out.println("Digite o ID do serviço que deseja excluir:");
                    Long idServicoExcluir = scannerServico.nextLong();
                    scannerServico.nextLine();

                    ServicoEntity servicoExcluir = (ServicoEntity) servicoRepository.findById(idServicoExcluir);
                    if (servicoExcluir == null) {
                        System.out.println("Serviço não encontrado.");
                        break;
                    }

                    servicoRepository.delete(idServicoExcluir);

                    System.out.println("O serviço foi excluído com sucesso!");
                    break;

                case 4:
                    System.out.println("Selecionado a opção 'Listar Todos Serviços'");

                    List<ServicoEntity> servicos = servicoRepository.findAll();
                    if (servicos.isEmpty()) {
                        System.out.println("Não há serviços cadastrados.");
                        break;
                    }

                    System.out.println("Listando todos os serviços:");
                    for (ServicoEntity servico : servicos) {
                        System.out.println("ID do serviço: " + servico.getId());
                        System.out.println("Data do serviço: " + new SimpleDateFormat("dd/MM/yyyy").format(servico.getDia()));
                        System.out.println("Descrição do serviço: " + servico.getDescricao());
                        System.out.println("ID do cliente: " + servico.getCliente().getId());
                        System.out.println("----------------------------");
                    }
                    break;

                case 5:
                    System.out.println("Voltando ao menu...");
                    break;
            }

        } while (respostaServico != 5);
    }
}
