package ru.kpfu.itis.telephone_directory.gui;

import ru.kpfu.itis.telephone_directory.contact.Contact;
import ru.kpfu.itis.telephone_directory.contact.Phone;
import ru.kpfu.itis.telephone_directory.dbe.DBDelete;
import ru.kpfu.itis.telephone_directory.dbe.DBException;
import ru.kpfu.itis.telephone_directory.dbe.DBSelect;
import ru.kpfu.itis.telephone_directory.dbe.DBUpdate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Created with IntelliJ IDEA.
 * User: Ilnur
 * Date: 11/8/13
 * Time: 3:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class Phones extends JPanel {
    private Contact contact;
    private JFrame frame;
    private ArrayList<JPanel> panels = new ArrayList<JPanel>();

    public Phones(JFrame frame, Contact contact) {
        this.frame = frame;
        this.contact = contact;
        setLayout(new BoxLayout(this, Y_AXIS));
        initContent();
    }

    private JButton makeButtonForPreviewPhone(Phone phone1) {
        JButton jButton = new JButton(phone1.getNumber());
        jButton.setAlignmentX(jButton.CENTER_ALIGNMENT);
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

    private JButton makeButtonOk() {
        JButton jButton = new JButton("Back");
        jButton.setAlignmentX(jButton.CENTER_ALIGNMENT);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }
        });
        return jButton;
    }

    private JButton makeButtonForInsert() {
        JButton jButton = new JButton("Add");
        jButton.setAlignmentX(jButton.CENTER_ALIGNMENT);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame("Telephone");
                frame1.setLocation(frame.getLocation());
                frame1.setContentPane(new AddPhone(frame1, contact));
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.pack();
                frame1.setSize(200, 100);
                frame1.setVisible(true);
            }
        });
        return jButton;
    }

    private JButton makeButtonForDelPhone(final Phone phone) {
        JButton jButton = new JButton("Del.");
        jButton.setAlignmentX(jButton.CENTER_ALIGNMENT);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBDelete dbDelete = new DBDelete();
                try {
                    dbDelete.deletePhone(phone);
                } catch (DBException e1) {
                    e1.printStackTrace();
                } finally {
                    upDateContent();
                }
            }
        });
        return jButton;
    }

    private JButton makeButtonForRedPhone(final Phone phone) {
        JButton jButton = new JButton("Red.");
        jButton.setAlignmentX(jButton.CENTER_ALIGNMENT);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame("Telephone");
                frame1.setLocation(frame.getLocation());
                frame1.setContentPane(new RedactPhone(frame1, phone));
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.pack();
                frame1.setSize(200, 100);
                frame1.setVisible(true);
            }
        });
        return jButton;
    }

    private void initContent() {
        ArrayList<Phone> phones;
        JButton button;
        JPanel jp;
        DBSelect select = new DBSelect();
        try {
            phones = select.getPhones(contact.getId());
            if (!phones.isEmpty()) {
                for (Phone x : phones) {
                    jp = new JPanel();
                    jp.setLayout(new GridLayout());
                    button = makeButtonForPreviewPhone(x);
                    jp.add(button);
                    button = makeButtonForDelPhone(x);
                    jp.add(button);
                    button = makeButtonForRedPhone(x);
                    jp.add(button);
                    panels.add(jp);
                    add(jp);
                }
            }
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            jp = new JPanel();
            button = makeButtonForUpDateContact();
            jp.add(button);
            button = makeButtonOk();
            jp.add(button);
            button = makeButtonForInsert();
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
