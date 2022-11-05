package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private String QUERY_SELECT_ID = "SELECT * FROM price.price_table WHERE id = ";
    private String QUERY_UPDATE = "UPDATE price.price_table SET item_name = ?, price = ? WHERE id = ?;";
    private String QUERY_SAVE = "INSERT INTO price.price_table (item_name, price) VALUES (?, ?);";
    private String QUERY_DELETE = "DELETE FROM price.price_table WHERE id = ";
    private String QUERY_SELECT_ALL = "SELECT * FROM price.price_table";
    private DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> productList = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QUERY_SELECT_ALL);

        while (resultSet.next()) {
            productList.add(new Product(
                    resultSet.getLong("id"),
                    resultSet.getString("item_name"),
                    resultSet.getLong("price")
            ));
        };
        statement.close();
        connection.close();
        return productList;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        Product product = null;
        ResultSet resultSet = statement.executeQuery(QUERY_SELECT_ID + id);
        if (resultSet.next()) {
            product = new Product(
                            resultSet.getLong("id"),
                            resultSet.getString("item_name"),
                            resultSet.getLong("price")
                    );
        }
        statement.close();
        connection.close();
        return Optional.ofNullable(product);
    }

    @Override
    public void update(Product product) throws SQLException {

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
        preparedStatement.setString(1, product.getItem_name());
        preparedStatement.setLong(2, product.getPrice());
        preparedStatement.setLong(3, product.getId());
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void save(Product product) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SAVE);
        preparedStatement.setString(1, product.getItem_name());
        preparedStatement.setLong(2, product.getPrice());
        preparedStatement.execute();
        connection.close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            if (findById(id).isPresent()) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(QUERY_DELETE + id);
                resultSet.next();
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
    }
}
