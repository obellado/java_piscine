package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessageRepository {
    private final String QUERY_TEMPLATE = "SELECT * FROM chat.messages WHERE id=";
    private final Connection connection;

    public MessagesRepositoryJdbcImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(QUERY_TEMPLATE + id);
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            Message ret = new Message(resultSet.getLong("id"), resultSet.getObject("author"), resultSet.getObject("room"), resultSet.getString("text"), resultSet.getTimestamp("timestamp").toLocalDateTime());
            System.out.println("RET : " + resultSet.getString("text"));
        }
        return Optional.empty();
    }
}
