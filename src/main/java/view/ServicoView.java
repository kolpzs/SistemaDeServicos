package view;

import controller.FuncionarioServicoController;
import controller.ServicoController;
import model.entity.FuncionarioEntity;
import model.entity.FuncionarioServicoEntity;
import model.entity.ServicoEntity;
import model.entity.ClienteEntity;
import model.repository.ClienteRepository;
import model.repository.FuncionarioRepository;
import model.repository.FuncionarioServicoRepository;
import model.repository.ServicoRepository;
import model.service.FuncionarioServicoService.CriarFuncionarioServico;
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
        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
        FuncionarioServicoRepository funcServRepository = new FuncionarioServicoRepository();
        CriarFuncionarioServico criarFuncServService = new CriarFuncionarioServico(funcServRepository);
        FuncionarioServicoController funcServController = new FuncionarioServicoController(criarFuncServService);

        do {
            System.out.println("1 - Criar Serviço");
            System.out.println("2 - Alterar Serviço");
            System.out.println("3 - Desativar Serviço");
            System.out.println("4 - Pesquisar Serviço");
            System.out.println("5 - Listar Todos Serviços");
            System.out.println("6 - Associar Funcionário a um Serviço");
            System.out.println("7 - Sair");

            do {
                respostaServico = scannerServico.nextInt();
                if (respostaServico < 1 || respostaServico > 7) {
                    System.out.println("Insira uma opção válida.");
                }
            } while (respostaServico < 1 || respostaServico > 7);

            switch (respostaServico) {
                case 1:
                    System.out.println("Selecionado a opção 'Criar Serviço'");

                    System.out.println("Digite a data do serviço (no formato dd/MM/yyyy): ");
                    scannerServico.nextLine();
                    String dataServico = scannerServico.nextLine();
                    Date dia = null;
                    if (!dataServico.isEmpty()) {
                        dia = new SimpleDateFormat("dd/MM/yyyy").parse(dataServico);
                    } else {
                        System.out.println("Data não fornecida. Usando a data atual como padrão.");
                        dia = new Date();
                    }
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
                    scannerServico.nextLine();
                    String novaDataServico = scannerServico.nextLine();
                    Date novoDiaDate = null;
                    if (!novaDataServico.isEmpty()) {
                        novoDiaDate = new SimpleDateFormat("dd/MM/yyyy").parse(novaDataServico);
                    } else {
                        System.out.println("Data não fornecida. Usando a data atual como padrão.");
                        novoDiaDate = new Date();
                    }

                    System.out.println("Digite a nova descrição do serviço:");
                    String novaDescricaoServico = scannerServico.nextLine();

                    System.out.println("Digite o novo ID do cliente:");
                    Long novoIdCliente = scannerServico.nextLong();
                    scannerServico.nextLine();
                    ClienteEntity novoCliente = (ClienteEntity) clienteRepository.findById(novoIdCliente);

                    servicoExistente.setDia(novoDiaDate);
                    servicoExistente.setDescricao(novaDescricaoServico);
                    servicoExistente.setCliente(novoCliente);

                    servicoRepository.updateById(servicoExistente);

                    System.out.println("O serviço foi alterado com sucesso!");
                    System.out.println("ID do serviço: " + servicoExistente.getId());
                    System.out.println("Nova data do serviço: " + new SimpleDateFormat("dd/MM/yyyy").format(servicoExistente.getDia()));
                    System.out.println("Nova descrição do serviço: " + servicoExistente.getDescricao());
                    break;

                case 3:
                    System.out.println("Selecionado a opção 'Desativar Serviço'");

                    System.out.println("Digite o ID do serviço que deseja desativar:");
                    Long idServicoDesativar = scannerServico.nextLong();
                    scannerServico.nextLine();

                    ServicoEntity servicoDesativar = (ServicoEntity) servicoRepository.findById(idServicoDesativar);
                    if (servicoDesativar == null) {
                        System.out.println("Serviço não encontrado.");
                        break;
                    }

                    servicoDesativar.setAtivo(false);
                    servicoRepository.updateById(servicoDesativar);

                    System.out.println("O serviço foi desativado com sucesso!");
                    break;

                case 4:
                    System.out.println("Selecionado a opção 'Buscar Serviço Específico'");

                    System.out.println("Digite o ID do serviço:");
                    Long idServicoBuscar = scannerServico.nextLong();
                    scannerServico.nextLine();

                    ServicoEntity servicoBuscado = (ServicoEntity) servicoRepository.findById(idServicoBuscar);
                    if (servicoBuscado == null) {
                        System.out.println("Serviço não encontrado.");
                        break;
                    }

                    System.out.println("ID do serviço: " + servicoBuscado.getId());
                    System.out.println("Data do serviço: " + new SimpleDateFormat("dd/MM/yyyy").format(servicoBuscado.getDia()));
                    System.out.println("Descrição do serviço: " + servicoBuscado.getDescricao());
                    System.out.println("ID do cliente: " + servicoBuscado.getCliente().getId());

                    List<FuncionarioServicoEntity> funcServs = funcServRepository.findAll();
                    boolean hasFuncionario = false;
                    for (FuncionarioServicoEntity funcServ : funcServs) {
                        if (funcServ.getServico().getId().equals(servicoBuscado.getId())) {
                            System.out.println("ID do funcionário: " + funcServ.getFuncionario().getId());
                            System.out.println("Nome do funcionário: " + funcServ.getFuncionario().getNome());
                            hasFuncionario = true;
                            break;
                        }
                    }
                    if (!hasFuncionario) {
                        System.out.println("SEM FUNCIONÁRIO ASSOCIADO");
                    }
                    System.out.println("----------------------------");
                    break;

                case 5:
                    System.out.println("Selecionado a opção 'Listar Todos os Serviços'");

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

                        List<FuncionarioServicoEntity> funcServes = funcServRepository.findAll();
                        boolean temFuncionario = false;
                        for (FuncionarioServicoEntity funcServ : funcServes) {
                            if (funcServ.getServico().getId().equals(servico.getId())) {
                                System.out.println("ID do funcionário: " + funcServ.getFuncionario().getId());
                                System.out.println("Nome do funcionário: " + funcServ.getFuncionario().getNome());
                                temFuncionario = true;
                                break;
                            }
                        }
                        if (!temFuncionario) {
                            System.out.println("SEM FUNCIONÁRIO ASSOCIADO");
                        }
                        System.out.println("----------------------------");
                    }
                    break;

                case 6:
                    System.out.println("Selecionado a opção 'Associar Funcionário a um Serviço'");

                    System.out.println("Digite o ID do funcionário:");
                    Long idFuncionario = scannerServico.nextLong();
                    scannerServico.nextLine();
                    FuncionarioEntity funcionario = (FuncionarioEntity) funcionarioRepository.findById(idFuncionario);

                    System.out.println("Digite o ID do serviço:");
                    Long idServicox = scannerServico.nextLong();
                    scannerServico.nextLine();
                    ServicoEntity servico = (ServicoEntity) servicoRepository.findById(idServicox);

                    FuncionarioServicoEntity novoFuncServ = funcServController.criarFuncionarioServico(funcionario, servico);

                    if (novoFuncServ != null) {
                        System.out.println("O funcionário foi associado ao serviço com sucesso!");
                        System.out.println("ID da associação: " + novoFuncServ.getId());
                        System.out.println("ID do funcionário: " + novoFuncServ.getFuncionario().getId());
                        System.out.println("ID do serviço: " + novoFuncServ.getServico().getId());
                    } else {
                        System.out.println("Não foi possível associar o funcionário ao serviço.");
                    }
                    break;

                case 7:
                    System.out.println("Voltando ao menu...");
                    break;
            }

        } while (respostaServico != 7);
    }
}
