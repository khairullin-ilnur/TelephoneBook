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
    public void insert(Contact contact, Phone phone) throws DBException {
        Connection connection = DBConnection.connection;
        Statement statement = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String name = contact.getName();
            System.out.print("2");
            statement.execute("INSERT INTO contact (contactcol) VALUE ('" + name + "')");
            System.out.print("1");
            int id = phone.getId();
            String number = phone.getNumber();
            int type = phone.getType();
            System.out.print("4");
            statement.execute("INSERT INTO phones (peopleId, number, type) VALUES (" + id + ", '" + number + "', '" + type + "')");
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

}
