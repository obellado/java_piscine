package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private long id;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime messageDateTime;;

    public Message(long id, User author, Chatroom room, String text, LocalDateTime messageDateTime){
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.messageDateTime = messageDateTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Chatroom getRoom() {
        return room;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getMessageDateTime() {
        return messageDateTime;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setMessageDateTime(LocalDateTime messageDateTime) {
        this.messageDateTime = messageDateTime;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && Objects.equals(author, message.author) && Objects.equals(room, message.room) && Objects.equals(text, message.text) && Objects.equals(messageDateTime, message.messageDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, room, text, messageDateTime);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author=" + author +
                ", room=" + room +
                ", text='" + text + '\'' +
                ", messageDateTime=" + messageDateTime +
                '}';
    }
}
