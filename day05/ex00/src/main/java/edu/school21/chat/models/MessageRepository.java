package edu.school21.chat.models;

import java.util.Optional;

public interface MessageRepository {
    Optional<Message> findByld(Long id);
}
