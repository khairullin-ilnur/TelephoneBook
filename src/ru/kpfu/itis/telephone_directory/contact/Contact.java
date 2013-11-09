package ru.kpfu.itis.telephone_directory.contact;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Ilnur
 * Date: 10/29/13
 * Time: 11:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Contact {
    private int id;
    private String name;
    private ArrayList<Phone> phones = new ArrayList<Phone>();

    public Contact(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Contact(int id, String name, ArrayList<Phone> phones) {
        this.id = id;
        this.name = name;
        this.phones = phones;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Phone phone) {
        System.out.print(phone);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phones=" + phones +
                '}';
    }
}
