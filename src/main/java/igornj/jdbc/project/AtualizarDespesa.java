package igornj.jdbc.project;

import igornj.jdbc.project.dao.DespesaDAO;
import igornj.jdbc.project.model.Categoria;
import igornj.jdbc.project.model.Despesa;

import java.time.LocalDate;
import java.util.Optional;

public class AtualizarDespesa {
    public static void main(String[] args){
        DespesaDAO dao = new DespesaDAO();

        Optional<Despesa> despesaOptional = dao.findById(3L);
        Despesa despesa = despesaOptional.get();

        System.out.println(despesa.getId());
        System.out.println(despesa.getDescricao());
        System.out.println(despesa.getValor());
        System.out.println(despesa.getData());
        System.out.println(despesa.getCategoria());

        despesa.setDescricao("Gasto com dentista");
        despesa.setValor(120.00);
        despesa.setData(LocalDate.of(2022, 6, 26));
        despesa.setCategoria(Categoria.OUTROS);

        dao.update(despesa);
    }

}
