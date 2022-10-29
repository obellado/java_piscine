package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private long UserID;
    private String Login;
    private String Password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> joinedRooms;

    public User(long id, String login, String password, List<Chatroom> cr, List<Chatroom> jr){
        this.UserID = id;
        this.Login = login;
        this.Password = password;
        this.createdRooms = cr;
        this.joinedRooms = jr;
    }

    public long getUserID() {
        return UserID;
    }

    public List<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public List<Chatroom> getJoinedRooms() {
        return joinedRooms;
    }

    public String getLogin() {
        return Login;
    }

    public String getPassword() {
        return Password;
    }

    public void setCreatedRooms(List<Chatroom> createdRooms) {
        this.createdRooms = createdRooms;
    }

    public void setJoinedRooms(List<Chatroom> joinedRooms) {
        this.joinedRooms = joinedRooms;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setUserID(long userID) {
        UserID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return UserID == user.UserID && Login.equals(user.Login) && Password.equals(user.Password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(UserID, Login, Password);
    }

    @Override
    public String toString() {
        return "User{" +
                "UserID=" + UserID +
                ", Login='" + Login + '\'' +
                ", Password='" + Password + '\'' +
                ", createdRooms=" + createdRooms +
                ", joinedRooms=" + joinedRooms +
                '}';
    }
}
