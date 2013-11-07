package ru.kpfu.itis.telephone_directory.gui;

import ru.kpfu.itis.telephone_directory.contact.Contact;
import ru.kpfu.itis.telephone_directory.dbe.DBException;
import ru.kpfu.itis.telephone_directory.dbe.DBSelect;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Created with IntelliJ IDEA.
 * User: Ilnur
 * Date: 10/30/13
 * Time: 1:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class ContactsListClass3 extends JFrame {
    private ArrayList<String> contacts;

    public ContactsListClass3() {
        super();
        setSize(250, 100);
        setTitle("MyContacts");
        setContent(getContentPane());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JButton makeButtonForPreviewContact (Contact contact){
        //button for preview cont po his id
        JButton jButton = new JButton(contact.getName());
        jButton.setAlignmentX(jButton.CENTER_ALIGNMENT);
        Insets i = new Insets(15, 15, 0, 0);
        return jButton;
    }

    private JButton makeButtonForAddNewContact (){
        JButton jButton = new JButton("Add new contact");
        jButton.setBackground(Color.green);
        jButton.setAlignmentX(jButton.CENTER_ALIGNMENT);
        return jButton;
    }

    private void setContent (Container pane){
        DBSelect select = new DBSelect();
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        pane.setLayout(new BoxLayout(pane, Y_AXIS));

        try {
            contacts = select.getContacts();
            if(!contacts.isEmpty()){
                for (Contact x: contacts) {
                    pane.add(makeButtonForPreviewContact(x));
                }
            }
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            pane.add(makeButtonForAddNewContact());
            //pane.add(jPanel1);
        }
    }

    public static void main(String[] args){
        ContactsListClass3 c = new ContactsListClass3();
    }
}
