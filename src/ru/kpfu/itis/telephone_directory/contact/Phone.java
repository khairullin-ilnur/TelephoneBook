package ru.kpfu.itis.telephone_directory.contact;

/**
 * Created with IntelliJ IDEA.
 * User: Ilnur
 * Date: 10/30/13
 * Time: 12:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class Phone {
    private int id;
    private int idUser;
    private int type;
    private String number;

    public Phone(int id, int idUser, int type, String number) {
        this.id = id;
        this.idUser = idUser;
        this.type = type;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public int getType() {

        return type;
    }

    public int getIdUser() {

        return idUser;
    }

    public int getId() {

        return id;
    }

    @Override
    public String toString() {
        return "" + number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
