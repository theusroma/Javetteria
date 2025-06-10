import controller.UsuarioController;
import model.Cliente;
import model.Funcionario;
import model.Gerente;
import view.Acessar;
import view.Acessar;
import view.MenuInterativo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        model.Estoque.inicializarEstoque();
        view.MenuInterativo.arteInicial();

        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        ArrayList<Gerente> gerentes = new ArrayList<>();

        UsuarioController usuarioController = new UsuarioController(clientes, funcionarios, gerentes);
        Acessar acessar = new Acessar(usuarioController);

        acessar.exibirMenuPrincipal();

    }
}

