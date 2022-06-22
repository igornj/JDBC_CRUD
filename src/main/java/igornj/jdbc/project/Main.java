package igornj.jdbc.project;

import igornj.jdbc.project.dao.DespesaDAO;
import igornj.jdbc.project.model.Categoria;
import igornj.jdbc.project.model.Despesa;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args){

        DespesaDAO dao = new DespesaDAO();

        Despesa despesa = new Despesa();
        despesa.setDescricao("Caneta");
        despesa.setValor(10.00);
        despesa.setData(LocalDate.of(2022, 6, 21));
        despesa.setCategoria(Categoria.EDUCACAO);

        dao.save(despesa);

    }
}
