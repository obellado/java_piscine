package edu.school21.manager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.annotations.OrmColumn;
import edu.school21.annotations.OrmColumnId;
import edu.school21.annotations.OrmEntity;


import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class OrmManager {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "123";

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    static {
        config.setJdbcUrl( DB_URL );
        config.setUsername( USER );
        config.setPassword( PASS );
//        config.addDataSourceProperty( "cachePrepStmts" , "true" );
//        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
//        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );
    }
    private Connection connection;

    public OrmManager() {
        try {
            this.connection = ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void update(Object o) {
        Class<?> class_ = o.getClass();
        try {
            if (class_.isAnnotationPresent(OrmEntity.class)) {
                OrmEntity entity = class_.getAnnotation(OrmEntity.class);
                StringBuilder sql = new StringBuilder("UPDATE test." + entity.table() + " SET ");
                Field[] fields = class_.getDeclaredFields();
                String id = "";

                for (Field f : fields) {
                    f.setAccessible(true);
                    if (f.isAnnotationPresent(OrmColumn.class)) {
                        OrmColumn column = f.getAnnotation(OrmColumn.class);
                        sql.append(column.name());
                        sql.append("=");
                        if (f.getType().getSimpleName().equals("String")) {
                            sql.append("'").append(f.get(o)).append("', ");
                        } else {
                            sql.append(f.get(o)).append(", ");
                        }
                    } else if (f.isAnnotationPresent(OrmColumnId.class)) {
                        id = f.get(o).toString();
                    }
                }
                sql = new StringBuilder(sql.substring(0, sql.length() - 2));
                sql.append(" WHERE id=").append(id).append(";");
                System.out.println(sql);
                PreparedStatement statement = connection.prepareStatement(sql.toString());
                statement.execute();
            }
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T findById(Long id, Class<T> aClass) {
        if (aClass.isAnnotationPresent(OrmEntity.class)) {
            try {
                OrmEntity entity = aClass.getAnnotation(OrmEntity.class);
                String sql = "SELECT * FROM test." + entity.table() + " WHERE id = " + id + ";";
                PreparedStatement statement = connection.prepareStatement(sql);
                System.out.println(sql);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    T object = aClass.getDeclaredConstructor().newInstance();
                    for (Field f : aClass.getDeclaredFields()) {
                        f.setAccessible(true);
                        if (f.isAnnotationPresent(OrmColumnId.class)) {
                            f.set(object, resultSet.getLong("id"));
                        } else if (f.isAnnotationPresent(OrmColumn.class)) {
                            OrmColumn column = f.getAnnotation(OrmColumn.class);
                            f.set(object, resultSet.getObject(column.name()));
                        }
                    }
                    return object;
                }
                System.out.println("Not found!");
                return null;
            } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public void save(Object o) {
        Class<?> class_ = o.getClass();
        try {
            if (class_.isAnnotationPresent(OrmEntity.class)) {
                OrmEntity entity = class_.getAnnotation(OrmEntity.class);
                StringBuilder attr = new StringBuilder("(");
                StringBuilder values = new StringBuilder("(");
                Field[] fields = class_.getDeclaredFields();
                for (Field f : fields) {
                    f.setAccessible(true);
                    if (f.isAnnotationPresent(OrmColumn.class)) {
                        OrmColumn column = f.getAnnotation(OrmColumn.class);
                        attr.append(", ");
                        attr.append(column.name());
                        values.append(", ");
                        if (f.getType().getSimpleName() != "int")
                            values.append("\'").append(f.get(o)).append("\'");
                        else
                            values.append(f.get(o));
                    } else if (f.isAnnotationPresent(OrmColumnId.class)) {
                        attr.append(f.getName());
                        values.append(f.get(o));
                    }
                }
                attr.append(")");
                values.append(")");
                String sql = "INSERT INTO test." + entity.table() + " " + attr + " VALUES " + values;
                System.out.println(sql);
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.execute();
            }
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void init() {
        String path = "./target/classes/edu/school21/classes/";
        File classes[] = null;
        PreparedStatement preparedStatement;

        File obj = new File(path);
        if (obj.exists() && obj.isDirectory()) {
            classes = obj.listFiles();
        }
        try {
            for (File file : classes) {
                System.out.println(file.getName());
                Class<?> class_ = Class.forName("edu.school21.classes." + file.getName().replace(".class", ""));
                if (class_.isAnnotationPresent(OrmEntity.class)) {
                    OrmEntity entity = class_.getAnnotation(OrmEntity.class);
                    preparedStatement = connection.prepareStatement("DROP SCHEMA IF EXISTS test CASCADE;");
                    preparedStatement.execute();
                    preparedStatement = connection.prepareStatement("CREATE SCHEMA IF NOT EXISTS test;");
                    preparedStatement.execute();
                    StringBuilder stringBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS test." + entity.table() + "(");
                    for (Field f : class_.getDeclaredFields()) {
                        if (f.isAnnotationPresent(OrmColumn.class)) {
                            stringBuilder.append(", ");
                            OrmColumn column = f.getAnnotation(OrmColumn.class);
                            stringBuilder.append(column.name());
                            stringBuilder.append(" ");
                            stringBuilder.append(toSqlType(f.getType().getSimpleName()));
                        } else if (f.isAnnotationPresent(OrmColumnId.class)) {
                            if (f.getType().getSimpleName().equals("Long")) {
                                stringBuilder.append(f.getName());
                                stringBuilder.append(" SERIAL PRIMARY KEY");
                            }
                        }
                    }
                    stringBuilder.append(");");
                    System.out.println(stringBuilder.toString());
                    preparedStatement = connection.prepareStatement(stringBuilder.toString());
                    preparedStatement.execute();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toSqlType(String type) {
        String result = "";
        switch (type) {
            case "String":
                result = "VARCHAR(255)";
                break;

            case "Integer":
            case "int":
                result = "INTEGER";
                break;

            case "Boolean":
                result = "BOOLEAN";
                break;

            case "Long":
            case "long":
                result = "BIGINT";
                break;

            case "Double":
                result = "DOUBLE";
                break;

        }
        return result;
    }
}
