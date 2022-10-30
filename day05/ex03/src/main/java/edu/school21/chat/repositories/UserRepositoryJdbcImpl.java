package edu.school21.chat.repositories;

import edu.school21.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {
    private final String QUERY_TEMPLATE = "SELECT * FROM chat.users WHERE id=";
    private final Connection connection;

    public UserRepositoryJdbcImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public Optional<User> findById(Long id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(QUERY_TEMPLATE + id);
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            User user = new User(resultSet.getLong("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    new ArrayList<>(),
                    new ArrayList<>());
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
