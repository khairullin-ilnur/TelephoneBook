package ru.kpfu.itis.telephone_directory.dbe;

import ru.kpfu.itis.telephone_directory.contact.Contact;
import ru.kpfu.itis.telephone_directory.contact.Phone;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Ilnur
 * Date: 10/30/13
 * Time: 12:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class DBSelect {

    public ArrayList<Phone> getPhones(int idContact) throws DBException {
        Connection connection = DBConnection.connection;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT number, type, id FROM phones WHERE peopleId IN (" + idContact + ")");
            ArrayList<Phone> result = new ArrayList<Phone>();
            while (resultSet.next()) {
                String number = resultSet.getString("number");
                int type = resultSet.getInt("type");
                int id = resultSet.getInt("id");
                result.add(new Phone(id, idContact, type, number));
            }
            connection.commit();
            return result;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    throw new RuntimeException("Can't rollback connection");
                }
            }
            throw new DBException("Can't execute SQL: select " + idContact + " from phones");
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }


    public ArrayList<Contact> getContacts() throws DBException {
        Connection connection = DBConnection.connection;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from contact");
            ArrayList<Contact> result = new ArrayList<Contact>();
            while (resultSet.next()) {
                int id = resultSet.getInt("idcontact");
                String name = resultSet.getString("contactcol");
                result.add(new Contact(id, name));
            }
            connection.commit();
            return result;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    throw new RuntimeException("Can't rollback connection");
                }
            }
            throw new DBException("Can't execute SQL: select * from contact");
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
