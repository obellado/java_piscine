package edu.school21.chat.repositories;


import edu.school21.chat.models.Chatroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ChatroomRepositoryJdbcImpl implements ChatroomRepository {
    private final String QUERY_TEMPLATE = "SELECT * FROM chat.rooms WHERE id=";
    private final Connection connection;

    private final UserRepository usersRepository;

    public ChatroomRepositoryJdbcImpl(Connection connection){
        this.connection = connection;
        usersRepository = new UserRepositoryJdbcImpl(connection);
    }

    @Override
    public Optional<Chatroom> findById(Long id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(QUERY_TEMPLATE + id);
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            Chatroom chat = new Chatroom(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    usersRepository.findById(resultSet.getLong("owner")).orElse(null),
                    new ArrayList<>());
            return Optional.of(chat);
        }
        return Optional.empty();
    }
}
