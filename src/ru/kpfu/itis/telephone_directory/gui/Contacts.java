package ru.kpfu.itis.telephone_directory.gui;

import ru.kpfu.itis.telephone_directory.contact.Contact;
import ru.kpfu.itis.telephone_directory.dbe.DBException;
import ru.kpfu.itis.telephone_directory.dbe.DBSelect;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Created with IntelliJ IDEA.
 * User: Ilnur
 * Date: 11/1/13
 * Time: 3:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class Contacts extends JPanel {
    private JFrame frame;

    public Contacts(JFrame frame) {
        this.frame = frame;
        setLayout(new BoxLayout(this, Y_AXIS));
        initContent();
    }

    private JButton makeButtonForPreviewContact(Contact contact) {
        JButton jButton = new JButton(contact.getName());
        jButton.setAlignmentX(jButton.CENTER_ALIGNMENT);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("Oh!");
            }
        });
        return jButton;
    }

    private JButton makeButtonForAddNewContact() {
        JButton jButton = new JButton("Add new contact");
        jButton.setAlignmentX(jButton.CENTER_ALIGNMENT);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame("Telephone");
                frame1.setLocation(frame.getLocation());
                frame1.setContentPane(new AddContact(frame1, frame));
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.pack();
                frame1.setSize(200, 120);
                frame.setVisible(false);
                frame1.setVisible(true);
            }
        });
        return jButton;
    }

    private void initContent() {
        DBSelect select = new DBSelect();
        ArrayList<Contact> contacts;
        try {
            contacts = select.getContacts();
            if (!contacts.isEmpty()) {
                for (Contact x : contacts) {
                    add(makeButtonForPreviewContact(x));
                }
            }
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            add(makeButtonForAddNewContact());
        }
    }
}
