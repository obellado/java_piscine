public interface UsersList {
    void addUser(User user);
    User retrieveByID(int i);
    User retrieveByIndex(int i);
    int retrieveNumber();
}
