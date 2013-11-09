package ru.kpfu.itis.telephone_directory.dbe;

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
public class DBUpdate {
    public void updatePhone(Phone phone) throws DBException {
        Connection connection = DBConnection.connection;
        Statement statement = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            int id = phone.getId();
            String number = phone.getNumber();
            int type = phone.getType();
            System.out.print("4");
            statement.execute("UPDATE phones SET number='" + number + "' WHERE id=" + id + "");
            statement.execute("UPDATE phones SET type='" + type + "' WHERE id=" + id + "");
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
        }
    }
}
