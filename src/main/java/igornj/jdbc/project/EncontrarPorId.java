package igornj.jdbc.project;

import igornj.jdbc.project.dao.DespesaDAO;
import igornj.jdbc.project.model.Despesa;

import java.util.Optional;

public class EncontrarPorId {
    public static void main(String[] args){

        DespesaDAO dao = new DespesaDAO();

        Optional<Despesa> despesaOptional = dao.findById(5L);
        despesaOptional.ifPresent(despesa -> {
            System.out.println("ID: " + despesa.getId());
            System.out.println("Descricao: " + despesa.getDescricao());
            System.out.println("Valor: " + despesa.getValor());
        });
    }
}
