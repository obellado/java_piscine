package edu.school21.app;

import edu.school21.classes.User;
import edu.school21.manager.OrmManager;

import java.sql.SQLException;

public class Program {
    public static void main(String[] args) {

        OrmManager manager = new OrmManager();

        manager.init();
        User user = new User(0L, "Tom", "Sawyer", 12);
        manager.save(user);
        user.setAge(15);
        user.setFirstName("Anna");
        manager.update(user);
        User user2;
        user2 = manager.findById(user.getId(), User.class);
        System.out.println(user2);
    }
}
