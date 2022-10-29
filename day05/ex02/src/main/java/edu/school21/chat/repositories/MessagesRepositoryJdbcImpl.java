package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessageRepository {
    private final String QUERY_TEMPLATE = "SELECT * FROM chat.messages WHERE id=";
    private final Connection connection;
    private final UserRepository usersRepository;
    private final ChatroomRepository chatroomRepository;

    public MessagesRepositoryJdbcImpl(Connection connection){
        this.connection = connection;
        chatroomRepository = new ChatroomRepositoryJdbcImpl(connection);
        usersRepository = new UserRepositoryJdbcImpl(connection);
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(QUERY_TEMPLATE + id);
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            Message mess =  new Message(resultSet.getLong("id"),
                    usersRepository.findById(resultSet.getLong("author")).orElse(null),
                    chatroomRepository.findById(resultSet.getLong("room")).orElse(null),
                    resultSet.getString("text"),
                    resultSet.getTimestamp("timestamp").toLocalDateTime());
            return Optional.of(mess);
        }
        return Optional.empty();
    }

    @Override
    public void save(Message message) throws SQLException {

    }
}
