package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessageRepository {
    private final String QUERY_TEMPLATE = "SELECT * FROM chat.messages WHERE id=";
    private final String QUERY_TEMPLATE_INSERT = "INSERT INTO chat.messages (author, room, text, timestamp) VALUES (?, ?, ?, ?) RETURNING *";

    private final String QUERY_TEMPLATE_UPDATE = "UPDATE chat.messages SET author=?, room=?, text=?, timestamp=? WHERE id=?;";
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
            if (resultSet.getTimestamp("timestamp") == null) {
                Message mess =  new Message(resultSet.getLong("id"),
                        usersRepository.findById(resultSet.getLong("author")).orElse(null),
                        chatroomRepository.findById(resultSet.getLong("room")).orElse(null),
                        resultSet.getString("text"),
                        null);
                return Optional.of(mess);
            }
            else {
                Message mess = new Message(resultSet.getLong("id"),
                        usersRepository.findById(resultSet.getLong("author")).orElse(null),
                        chatroomRepository.findById(resultSet.getLong("room")).orElse(null),
                        resultSet.getString("text"),
                        resultSet.getTimestamp("timestamp").toLocalDateTime());
                return Optional.of(mess);
            }
        }
        return Optional.empty();
    }

    @Override
    public void save(Message message) throws SQLException {
        if (!usersRepository.findById(message.getAuthor().getUserID()).isPresent()) {
            throw new NotSavedSubEntityException("No user with this ID in database");
        }
        if (!chatroomRepository.findById(message.getRoom().getId()).isPresent()) {
            throw new NotSavedSubEntityException("No chatroom with this ID in database");
        }
        PreparedStatement stmt = connection.prepareStatement(QUERY_TEMPLATE_INSERT);
        stmt.setLong(1, message.getAuthor().getUserID());
        stmt.setLong(2, message.getRoom().getId());
        stmt.setString(3, message.getText());
        stmt.setTimestamp(4, Timestamp.valueOf(message.getMessageDateTime()));
        ResultSet resultSet = stmt.executeQuery();
        resultSet.next();
        message.setId(resultSet.getLong("id"));
    }

    @Override
    public void update(Message message) throws SQLException {
        if (!usersRepository.findById(message.getAuthor().getUserID()).isPresent()) {
            throw new NotSavedSubEntityException("No user with this ID in database");
        }
        if (!chatroomRepository.findById(message.getRoom().getId()).isPresent()) {
            throw new NotSavedSubEntityException("No chatroom with this ID in database");
        }
        PreparedStatement stmt = connection.prepareStatement(QUERY_TEMPLATE_UPDATE);
        ResultSet resultSet = null;
        stmt.setLong(1, message.getAuthor().getUserID());
        stmt.setLong(2, message.getRoom().getId());
        stmt.setString(3, message.getText());
        if (message.getMessageDateTime() == null)
            stmt.setTimestamp(4, null);
        else
            stmt.setTimestamp(4, Timestamp.valueOf(message.getMessageDateTime()));
        stmt.setLong(5, message.getId());
        stmt.execute();
    }
}
