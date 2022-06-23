package igornj.jdbc.project;

import igornj.jdbc.project.dao.DespesaDAO;
import igornj.jdbc.project.model.Categoria;
import igornj.jdbc.project.model.Despesa;

import java.util.List;

public class EncontarPorCategoria {
    public static void main(String[] args){

        DespesaDAO dao = new DespesaDAO();

        List<Despesa> despesas = dao.findByCategoria(Categoria.ALIMENTACAO);
        for (Despesa d : despesas) {
            System.out.println("ID: " + d.getId());
            System.out.println("Descricao: " + d.getDescricao());
            System.out.println("Categoria: " + d.getCategoria());
            System.out.println("Valor: " + d.getValor());
            System.out.println("====================================");
        }
    }
}
