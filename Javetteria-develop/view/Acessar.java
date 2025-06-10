package view;

import model.*;
import controller.UsuarioController;
import controller.LoginController;

import java.util.Scanner;
import java.util.ArrayList;

public class Acessar {

    private Scanner scanner = new Scanner(System.in);
    private UsuarioController usuarioController;
    private LoginController loginController;


    public Acessar(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
        this.loginController = new LoginController(
                usuarioController.getClientes(),
                usuarioController.getFuncionarios(),
                usuarioController.getGerentes()
        );
    }


//    INPPUT
private String pedirInput(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine();
}



    //------NOVO MENU PRINCIPALLL
    public void exibirMenuPrincipal() {
        while (true) {
            System.out.println("\n=== Javetteria ===");
            System.out.println(" ╔════════════════════════════════╗");
            System.out.println(" ║                                ║");
            System.out.println(" ║ [1] Cadastrar                  ║");
            System.out.println(" ║ [2] Login                      ║");
            System.out.println(" ║ [0] Sair                       ║");
            System.out.println(" ║                                ║");
            System.out.println(" ╚════════════════════════════════╝");

            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    exibirCadastro();
                    break;

                case "2":
                    realizarLogin();
                    break;

                case "0":
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }


    // novo metodo de login
    private void realizarLogin() {
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Pessoa pessoaLogada = loginController.autenticar(login, senha);

        if (pessoaLogada != null) {
            System.out.println("\nLogin bem-sucedido!");
            exibirMenu(pessoaLogada);
        } else {
            System.out.println("Login inválido.");
        }
    }



    public void exibirCadastro() {
        System.out.println("\n=== Cadastro de Usuário ===");
        System.out.println(" ╔════════════════════════════════╗");
        System.out.println(" ║                                ║");
        System.out.println(" ║ [1] Cliente                    ║");
        System.out.println(" ║ [2] Funcionario                ║");
        System.out.println(" ║ [3] Gerente                    ║");
        System.out.println(" ║ [0] Sair                       ║");
        System.out.println(" ║                                ║");
        System.out.println(" ╚════════════════════════════════╝ ");



        System.out.print("Escolha o tipo de usuário: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 0) {
            System.out.println("Voltando ao menu principal.");
            return;
        }

        String nome = pedirInput("Nome: ");
        String login = pedirInput("Login: ");
        String cpf = pedirInput("CPF: ");
        String senha = pedirInput("Senha: ");

        switch (tipo) {
            case 1:
                cadastrarCliente(nome, login, cpf, senha);
                break;
            case 2:
                cadastrarFuncionario(nome, login, cpf, senha);
                break;
            case 3:
                cadastrarGerente(nome, login, cpf, senha);
                break;
            default:
                System.out.println("Tipo inválido.");
        }
    }

    private void cadastrarCliente(String nome, String login, String cpf, String senha) {
        Cliente novoCliente = new Cliente(nome, login, cpf, senha);
        usuarioController.adicionarCliente(novoCliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private void cadastrarFuncionario(String nome, String login, String cpf, String senha) {
        String cargo = pedirInput("Cargo: ");
        String turno = pedirInput("Turno: ");
        Funcionario novoFuncionario = new Funcionario(nome, login, cpf, cargo, turno, senha);
        usuarioController.adicionarFuncionario(novoFuncionario);
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private void cadastrarGerente(String nome, String login, String cpf, String senha) {
        String cargoGerente = pedirInput("Cargo: ");
        String turnoGerente = pedirInput("Turno: ");
        Gerente novoGerente = new Gerente(nome, login, cpf, cargoGerente, turnoGerente, senha);
        usuarioController.adicionarGerente(novoGerente);
        System.out.println("Gerente cadastrado com sucesso!");
    }


    public void exibirMenu(Pessoa pessoaLogada) {
        String tipo = pessoaLogada.getTipoPessoa();

        switch (tipo) {
            case "Cliente":
                menuCliente((Cliente) pessoaLogada);
                break;
            case "Funcionário":
                menuFuncionario((Funcionario) pessoaLogada);
                break;
            case "Gerente":
                menuGerente((Gerente) pessoaLogada);
                break;
            default:
                System.out.println("Tipo de usuário desconhecido.");
        }
    }

    private void menuCliente(Cliente cliente) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== Menu Cliente ===");
            System.out.println(" ╔════════════════════════════════╗");
            System.out.println(" ║                                ║");
            System.out.println(" ║ [1] Ver meus dados             ║");
            System.out.println(" ║ [2] Alterar endereço           ║");
            System.out.println(" ║ [0] Sair                       ║");
            System.out.println(" ║                                ║");
            System.out.println(" ╚════════════════════════════════╝ ");

            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    verDadosCliente(cliente);
                    break;
                case 2:
                    alterarEnderecoCliente(cliente);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void verDadosCliente(Cliente cliente) {
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        // Adiciona uma verificação para Endereco não ser nulo
        if (cliente.getEndereco() != null) {
            System.out.println("Endereço: " + cliente.getEndereco().getRua() + ", " +
                    cliente.getEndereco().getNumero() + " - " + cliente.getEndereco().getBairro() +
                    ", " + cliente.getEndereco().getCidade());
        } else {
            System.out.println("Endereço: Não cadastrado.");
        }
    }

    private void alterarEnderecoCliente(Cliente cliente) {
        String novaRua = pedirInput("Nova rua:");
        String novoNumero = pedirInput("Novo número:");
        String novoBairro = pedirInput("Novo bairro:");
        String novaCidade = pedirInput("Nova cidade:");

       
        usuarioController.atualizarCliente(
                cliente.getLogin(),
                cliente.getNome(), // Mantém o nome
                cliente.getCpf(),  // Mantém o CPF
                novaRua,
                novoNumero,
                novoBairro,
                novaCidade
        );
        System.out.println("Endereço atualizado com sucesso!");
    }


    private void menuFuncionario(Funcionario funcionario) {
        System.out.println("\n=== Menu Funcionário ===");
        System.out.println("Nome: " + funcionario.getNome());
        System.out.println("Cargo: " + funcionario.getCargo());
        System.out.println("Turno: " + funcionario.getTurno());
        pedirInput("Pressione Enter para voltar ao menu principal."); // Para dar tempo de ler as informações


    }

    private void menuGerente(Gerente gerente) {
        int opcao = -1;
        while (opcao != 0) {

            System.out.println("\n     === Menu Gerente ===");
            System.out.println(" ╔════════════════════════════════╗");
            System.out.println(" ║                                ║");
            System.out.println(" ║ [1] Listar Funcionário         ║");
            System.out.println(" ║ [2] Listar Clientes            ║");
            System.out.println(" ║ [3] Cadastrar novo Funcionário ║");
            System.out.println(" ║ [4] Remover Funcionário        ║");
            System.out.println(" ║ [5] Alterar senha de usuário   ║");
            System.out.println(" ║ [0] Sair                       ║");
            System.out.println(" ║                                ║");
            System.out.println(" ╚════════════════════════════════╝ ");

            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listarFuncionariosGerente();
                    break;
                case 2:
                    listarClientesGerente();
                    break;
                case 3:
                    cadastrarNovoFuncionarioGerente();
                    break;
                case 4:
                    removerFuncionarioGerente();
                    break;
                case 5:
                    alterarSenhaUsuarioGerente();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void listarFuncionariosGerente() {
        System.out.println("\n--- Lista de Funcionários ---");
        for (Funcionario f : usuarioController.listarFuncionarios()) {
            System.out.println("Nome: " + f.getNome() + " | Cargo: " + f.getCargo() + " | Login: " + f.getLogin());
        }
    }


    private void listarClientesGerente() {
        System.out.println("\n--- Lista de Clientes ---");
        for (Cliente c : usuarioController.listarClientes()) {
            System.out.println("Nome: " + c.getNome() + " | CPF: " + c.getCpf() + " | Login: " + c.getLogin());
            if (c.getEndereco() != null) {
                System.out.println("  Endereço: " + c.getEndereco().getRua() + ", " +
                        c.getEndereco().getNumero() + " - " + c.getEndereco().getBairro() +
                        ", " + c.getEndereco().getCidade());
            }
        }
    }


    private void cadastrarNovoFuncionarioGerente() {
        System.out.println("\n--- Cadastrar Novo Funcionário ---");
        String nome = pedirInput("Nome: ");
        String login = pedirInput("Login: ");
        String cpf = pedirInput("CPF: ");
        String cargo = pedirInput("Cargo: ");
        String turno = pedirInput("Turno: ");
        String senha = pedirInput("Senha: ");
        usuarioController.adicionarFuncionario(new Funcionario(nome, login, cpf, cargo, turno, senha));
        System.out.println("Funcionário cadastrado com sucesso!");
    }


    private void removerFuncionarioGerente() {
        System.out.print("Login do funcionário a remover: ");
        String loginRemover = scanner.nextLine();
        if (usuarioController.removerFuncionario(loginRemover)) {
            System.out.println("Removido com sucesso.");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }


    private void alterarSenhaUsuarioGerente() {
        System.out.print("Login do usuário: ");
        String loginAlt = scanner.nextLine();
        System.out.print("Nova senha: ");
        String novaSenha = scanner.nextLine();

        if (usuarioController.alterarSenha(loginAlt, novaSenha)) {
            System.out.println("Senha alterada.");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

}
