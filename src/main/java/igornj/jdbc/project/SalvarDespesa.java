package igornj.jdbc.project;

import igornj.jdbc.project.dao.DespesaDAO;
import igornj.jdbc.project.model.Categoria;
import igornj.jdbc.project.model.Despesa;

import java.time.LocalDate;

public class SalvarDespesa {
    public static void main(String[] args){

        DespesaDAO dao = new DespesaDAO();

        Despesa despesa = new Despesa(1L, "Placa de video", LocalDate.of(2022, 06, 15), 3500.69, Categoria.OUTROS);
        dao.save(despesa);
    }
}
