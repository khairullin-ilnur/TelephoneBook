package ru.kpfu.itis.telephone_directory.gui;

import ru.kpfu.itis.telephone_directory.contact.Contact;
import ru.kpfu.itis.telephone_directory.dbe.DBDelete;
import ru.kpfu.itis.telephone_directory.dbe.DBException;
import ru.kpfu.itis.telephone_directory.dbe.DBSelect;

import javax.swing.*;
import java.awt.*;
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
    private ArrayList<JPanel> panels = new ArrayList<JPanel>();

    public Contacts(JFrame frame) {
        this.frame = frame;
        setLayout(new BoxLayout(this, Y_AXIS));
        initContent();
    }

    private JButton makeButtonForPreviewContact(final Contact contact) {
        JButton jButton = new JButton(contact.getName());
        jButton.setAlignmentX(jButton.CENTER_ALIGNMENT);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame("Telephone");
                frame1.setLocation(frame.getLocation());
                frame1.setContentPane(new Phones(frame1, contact));
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.pack();
                frame1.setSize(350, 220);
                frame1.setVisible(true);
            }
        });
        return jButton;
    }

    private JButton makeButtonForAddNewContact() {
        JButton jButton = new JButton("Add");
        jButton.setAlignmentX(jButton.CENTER_ALIGNMENT);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame("Telephone");
                frame1.setLocation(frame.getLocation());
                frame1.setContentPane(new AddContact(frame1));
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.pack();
                frame1.setSize(200, 120);
                frame1.setVisible(true);
            }
        });
        return jButton;
    }

    private JButton makeButtonForUpDateContact() {
        JButton jButton = new JButton("UpDate");
        jButton.setAlignmentX(jButton.CENTER_ALIGNMENT);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upDateContent();
            }
        });
        return jButton;
    }

    private JButton makeButtonForDelContact(final Contact contact) {
        JButton jButton = new JButton("Del.");
        jButton.setAlignmentX(jButton.CENTER_ALIGNMENT);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBDelete dbDelete = new DBDelete();
                try {
                    dbDelete.deleteContact(contact);
                } catch (DBException e1) {
                    e1.printStackTrace();
                } finally {
                    upDateContent();
                }
            }
        });
        return jButton;
    }

    private void initContent() {
        ArrayList<Contact> contacts;
        JButton button;
        JPanel jp;
        DBSelect select = new DBSelect();
        try {
            contacts = select.getContacts();
            if (!contacts.isEmpty()) {
                for (Contact x : contacts) {
                    jp = new JPanel();
                    jp.setLayout(new GridLayout());
                    button = makeButtonForPreviewContact(x);
                    jp.add(button);
                    button = makeButtonForDelContact(x);
                    jp.add(button);
                    panels.add(jp);
                    add(jp);
                }
            }
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            jp = new JPanel();
            button = makeButtonForAddNewContact();
            jp.add(button);
            button = makeButtonForUpDateContact();
            jp.add(button);
            panels.add(jp);
            add(jp);
        }
    }

    private void upDateContent() {
        if (!panels.isEmpty()) {
            for (JPanel x : panels) {
                remove(x);
            }
        }
        initContent();
        updateUI();
    }
}
