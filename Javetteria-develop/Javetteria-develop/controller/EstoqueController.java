package controller;
import utils.Cores;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import model.Estoque;
import model.Ingrediente;


public class EstoqueController {


    public static List<Ingrediente> listarItens() {
        return Estoque.getIngredientes();
    }

    public static void adicionarItem(int id, String nome, int quantidade, String unidade) {
        Estoque.adicionar(new Ingrediente(id, nome, quantidade, unidade));
    }

    public static boolean removerItem(int id){
        return Estoque.remover(id);
    }

    public static void atualizarItem(int id, int quantidade){
        Estoque.atualizarQuantidade(id, quantidade);
    }

    public static void exportarEstoque() {
        List<Ingrediente> ingredientes = Estoque.getIngredientes();
        // otimizar a escrita em arquivos
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("data/estoque.txt"))){
            // filewriter sobrescreve o arquivo
            escritor.write("========== ESTOQUE ==========\n");

            for (Ingrediente i : ingredientes) {
                escritor.write("→ ID: " + i.getId() + "\n");
                escritor.write("Nome: " + i.getNome() + "\n");
                escritor.write("Quantidade: " + i.getQuantidade() + "\n");
                escritor.write("Unidade: " + i.getUnidade() + "\n\n");

                escritor.write("==============================\n");
            }
            view.EstoqueView.mostrarMensagem((Cores.LAVENDER + "\n>>" + Cores.RESET) + Cores.CREME + " Estoque exportado com sucesso!");

        } catch (IOException e){
            // IOException -> erros de entrada e saida
            view.EstoqueView.mostrarMensagem((Cores.LAVENDER + "\n>>" + Cores.RESET) + Cores.CREME +
                    " Erro ao exportar o estoque: " + e.getMessage());
            // getMessage -> mensagem descritiva do erro

        }
        // escritor.close(); -> o try ja fecha o escritor
    }

}