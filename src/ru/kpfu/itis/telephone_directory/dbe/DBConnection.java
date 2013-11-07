package ru.kpfu.itis.telephone_directory.dbe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Ilnur
 * To change this template use File | Settings | File Templates.
 */

class DBConnection {
    private final static String DRIVER = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/telephone_book";
    private final static String LOGIN = "root";
    private final static String PASSWORD = "ilnur";
    static Connection connection;

    static {
        try {
            Class.forName(DRIVER);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}