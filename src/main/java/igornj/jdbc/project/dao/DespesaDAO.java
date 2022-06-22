package igornj.jdbc.project.dao;

import igornj.jdbc.project.infra.ConnectionFactory;
import igornj.jdbc.project.model.Categoria;
import igornj.jdbc.project.model.Despesa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DespesaDAO implements iDespesaDAO{
    @Override
    public Despesa save(Despesa despesa) {

        try(Connection connection = ConnectionFactory.getConnection()) {
            //try-with-resources, basically close the connection for us automatically, without a close() method.
            //The question marks help us avoid the SQL INJECTION, basically it avoids malicious injections from something or someone.
            String sql = "INSERT INTO Despesas (descricao, valor, data, categoria ) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, despesa.getDescricao());
            preparedStatement.setDouble(2, despesa.getValor());
            preparedStatement.setDate(3, java.sql.Date.valueOf(despesa.getData())); //expects sql date, that's why we do the conversion
            preparedStatement.setString(4, despesa.getCategoria().toString());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return despesa;
    }

    @Override
    public Despesa update(Despesa despesa) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Despesa> findAll() {
        return null;
    }

    @Override
    public Optional<Despesa> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Despesa> findByCategoria(Categoria categoria) {
        return null;
    }
}
