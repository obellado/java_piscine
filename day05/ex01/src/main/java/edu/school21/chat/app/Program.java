package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.repositories.*;

import java.io.File;
import java.sql.*;
import java.util.Optional;
import java.util.Scanner;

public class Program {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_SCHEMA = "/resources/schema.sql";
    private static final String DB_DATA = "/resources/data.sql";

    static final String USER = "postgres";
    static final String PASS = "123";

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    static {
        config.setJdbcUrl( DB_URL );
        config.setUsername( USER );
        config.setPassword( PASS );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );
    }

    public static void main(String[] args) {
        try {
            Connection connection = ds.getConnection();
            MessageRepository msgRepository = new MessagesRepositoryJdbcImpl(connection);
//            UserRepository usersRepository = new UserRepositoryJdbcImpl(connection);
//            ChatroomRepository chatroomRepository = new ChatroomRepositoryJdbcImpl(connection);
            System.out.println("Creating tables...");
            tryPostgreSQL(connection, DB_SCHEMA);
            System.out.println("Creating data...");
            tryPostgreSQL(connection, DB_DATA);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a message ID:");
            Long id = scanner.nextLong();
            System.out.println(msgRepository.findById(id));
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void tryPostgreSQL(Connection connection, String filename){
        try {
            File file = new File("/Users/obellado/Desktop/java-piscine/day05/ex01/src/main" + filename);
            Scanner scanner = new Scanner(file).useDelimiter(";");
            while (scanner.hasNext())
                connection.createStatement().execute(scanner.next());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
