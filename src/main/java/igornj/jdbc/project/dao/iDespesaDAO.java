package igornj.jdbc.project.dao;

import igornj.jdbc.project.model.Categoria;
import igornj.jdbc.project.model.Despesa;

import java.util.List;
import java.util.Optional;

public interface iDespesaDAO {

    Despesa save(Despesa despesa);
    Despesa update(Despesa despesa);
    void delete(Long id);
    List<Despesa> findAll();
    Optional<Despesa> findById(Long id); //The optional type is used here because we can pass an ID that might not exist.
    List<Despesa> findByCategoria(Categoria categoria);
}
