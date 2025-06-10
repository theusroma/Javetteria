import Controller.UsuarioController;
import Model.Cliente;
import Model.Funcionario;
import Model.Gerente;
import View.Menu;
import View.MenuInterativo;

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
        Menu menu = new Menu(usuarioController);

        menu.exibirMenuPrincipal();

    }
}

