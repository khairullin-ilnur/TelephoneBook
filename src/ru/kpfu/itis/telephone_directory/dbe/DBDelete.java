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
public class DBDelete {

    public void deletePhone(Phone phone) throws DBException {
        Connection connection = DBConnection.connection;
        Statement statement = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            int id = phone.getId();
            System.out.print("4");
            statement.execute("DELETE FROM phones WHERE id IN ("+id+")");
            System.out.print("3");
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
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public void deleteContact(Contact contact) throws DBException {
        Connection connection = DBConnection.connection;
        Statement statement = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            int id = contact.getId();
            System.out.print("10");
            statement.execute("DELETE FROM phones WHERE peopleId IN ("+id+")");
            System.out.print("13");
            System.out.print("9");
            statement.execute("DELETE FROM contact WHERE idcontact IN ("+id+")");
            System.out.print("8");
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
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}
