package igornj.jdbc.project;

import igornj.jdbc.project.dao.DespesaDAO;

public class DeletarDespesa {
    public static void main(String[] args){
        DespesaDAO dao = new DespesaDAO();

        dao.delete(5L);
    }
}
