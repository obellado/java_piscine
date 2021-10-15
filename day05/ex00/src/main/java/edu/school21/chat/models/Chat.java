package edu.school21.chat.models;

import java.io.File;
import java.sql.*;
import java.util.Optional;
import java.util.Scanner;

public class Chat {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/library";
    private static final String DB_SCHEMA = "/resources/schema.sql";
    private static final String DB_DATA = "/resources/data.sql";

    static final String USER = "postgres";
    static final String PASS = "123";

    public static void main(String[] args) {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating tables...");
            tryPostgreSQL(connection, DB_SCHEMA);
            System.out.println("Creating data...");
            tryPostgreSQL(connection, DB_DATA);
            System.out.println("Finito!");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void tryPostgreSQL(Connection connection, String filename){
        try {
            File file = new File("/Users/obellado/Desktop/Java_In_Progress/day05/ex00/src/main" + filename);
            Scanner scanner = new Scanner(file).useDelimiter(";");
            while (scanner.hasNext())
                connection.createStatement().execute(scanner.next());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
