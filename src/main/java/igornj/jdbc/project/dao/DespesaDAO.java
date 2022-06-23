package igornj.jdbc.project.dao;

import igornj.jdbc.project.infra.ConnectionFactory;
import igornj.jdbc.project.model.Categoria;
import igornj.jdbc.project.model.Despesa;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DespesaDAO implements iDespesaDAO{
    @Override
    public Despesa save(Despesa despesa) {

        try(Connection connection = ConnectionFactory.getConnection()) {
            //try-with-resources, basically close the connection for us automatically, without a close() for the methods.
            //The question marks help us avoid the SQL INJECTION, basically it avoids malicious injections from something or someone.
            String sql = "INSERT INTO Despesas (descricao, valor, data, categoria ) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, despesa.getDescricao());
            preparedStatement.setDouble(2, despesa.getValor());
            preparedStatement.setDate(3, java.sql.Date.valueOf(despesa.getData())); //expects sql date, that's why we do the conversion
            preparedStatement.setString(4, despesa.getCategoria().toString());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys(); //Here we have access to all the keys inside de DB
            resultSet.next(); //At first the resultSet it's before our first line in the DB, calling this method it will position the cursor in the right row.

            Long generatedId = resultSet.getLong("id");
            despesa.setId(generatedId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return despesa;
    }

    @Override
    public void update(Despesa despesa) {
        String sql = "UPDATE Despesas SET descricao = ? , data = ?, valor = ?, categoria = ? WHERE id = ?";


        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, despesa.getDescricao());
            preparedStatement.setDate(2, java.sql.Date.valueOf(despesa.getData()));
            preparedStatement.setDouble(3, despesa.getValor());
            preparedStatement.setString(4, despesa.getCategoria().toString());
            preparedStatement.setLong(5, despesa.getId());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Despesas WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            System.out.println("A despesa com id " + id + " foi deletada com sucesso");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Despesa> findAll() {
        String sql = "SELECT id, descricao, data, valor, categoria FROM Despesas";

        List<Despesa> despesas = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String descricao = resultSet.getString("descricao");
                LocalDate data = resultSet.getDate("data").toLocalDate();
                double valor = resultSet.getDouble("valor");
                Categoria categoria = Categoria.valueOf(resultSet.getString("categoria"));

                Despesa despesa = new Despesa(id, descricao, data, valor, categoria);
                despesas.add(despesa);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return despesas;

    }

    @Override
    public Optional<Despesa> findById(Long id) {
        String sql = "SELECT id, descricao, data, valor, categoria FROM Despesas WHERE id = ?";

        Despesa despesa = null;

        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Long idReceived = resultSet.getLong("id");
                String descricao = resultSet.getString("descricao");
                LocalDate data = resultSet.getDate("data").toLocalDate();
                double valor = resultSet.getDouble("valor");
                Categoria categoria = Categoria.valueOf(resultSet.getString("categoria"));

                despesa = new Despesa(idReceived, descricao, data, valor, categoria);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(despesa);
    }

    @Override
    public List<Despesa> findByCategoria(Categoria categoria) {
        String sql = "SELECT id, descricao, data, valor, categoria FROM Despesas WHERE categoria = ?";

        List<Despesa> despesas = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, categoria.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String descricao = resultSet.getString("descricao");
                LocalDate data = resultSet.getDate("data").toLocalDate();
                double valor = resultSet.getDouble("valor");
                Categoria categoriaReceived = Categoria.valueOf(resultSet.getString("categoria"));

                Despesa despesa = new Despesa(id, descricao, data, valor, categoriaReceived);
                despesas.add(despesa);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return despesas;
    }
}
