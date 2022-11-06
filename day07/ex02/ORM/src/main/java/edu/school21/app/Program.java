package edu.school21.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.classes.User;
import edu.school21.manager.OrmManager;

import java.sql.SQLException;

public class Program {


    public static void main(String[] args) throws ClassNotFoundException, SQLException, IllegalAccessException {

        OrmManager manager = new OrmManager();

        manager.init();

//
        User user = new User(1, "Tom", "Sawyer", 12);
//
        manager.save(user);

        user.setAge(15);
        user.setFirstName("Anna");

        manager.update(user);
//
//        manager.findUserById(0L);
    }
}
