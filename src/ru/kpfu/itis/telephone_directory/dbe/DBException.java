package ru.kpfu.itis.telephone_directory.dbe;

/**
 * Created with IntelliJ IDEA.
 * User: Ilnur
 * Date: 10/30/13
 * Time: 11:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBException extends Exception {

    public DBException(String message) {
        super(message);
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}
