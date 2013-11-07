package ru.kpfu.itis.telephone_directory.dbe;

import ru.kpfu.itis.telephone_directory.contact.Contact;
import ru.kpfu.itis.telephone_directory.contact.Phone;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: Ilnur
 * Date: 10/30/13
 * Time: 12:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class DBInsert {

    public void insertContact(Contact contact) throws DBException {
        Connection connection = DBConnection.connection;
        Statement statement = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String name = contact.getName();
            statement.execute("INSERT INTO contact (contactcol) VALUE ('" + name + "')");
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    throw new RuntimeException("Can't rollback connection");
                }
            }
            throw new DBException("Can't execute SQL");
        }
    }

    public void insertPhone(Phone phone) throws DBException {
        Connection connection = DBConnection.connection;
        Statement statement = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            int id = phone.getIdUser();
            String number = phone.getNumber();
            int type = phone.getType();
            statement.execute("INSERT INTO phones (peopleId, number, type) VALUES (" + id + ", '" + number + "', " + type + ")");
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    throw new RuntimeException("Can't rollback connection");
                }
            }
            throw new DBException("Can't execute SQL");
        }
    }

    public void insert(Contact contact, Phone phone) throws DBException {
        Connection connection = DBConnection.connection;
        Statement statement = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String name = contact.getName();
            statement.execute("INSERT INTO contact (contactcol) VALUE ('" + name + "')");
            int id = phone.getIdUser();
            String number = phone.getNumber();
            int type = phone.getType();
            statement.execute("INSERT INTO phones (peopleId, number, type) VALUES (" + id + ", '" + number + "', " + type + ")");
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    throw new RuntimeException("Can't rollback connection");
                }
            }
            throw new DBException("Can't execute SQL");
        }
    }
}
