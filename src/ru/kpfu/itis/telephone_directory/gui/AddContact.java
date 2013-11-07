package ru.kpfu.itis.telephone_directory.gui;

import ru.kpfu.itis.telephone_directory.contact.Contact;
import ru.kpfu.itis.telephone_directory.contact.Phone;
import ru.kpfu.itis.telephone_directory.dbe.DBException;
import ru.kpfu.itis.telephone_directory.dbe.DBInsert;
import ru.kpfu.itis.telephone_directory.dbe.DBSelect;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Created with IntelliJ IDEA.
 * User: Ilnur
 * Date: 11/1/13
 * Time: 3:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class AddContact extends JPanel {
    private JFrame myFrame;
    private JTextField jTextFieldName;
    private JTextField jTextFieldPhone;

    public AddContact(JFrame myFrame) {
        this.myFrame = myFrame;
        setLayout(new BoxLayout(this, Y_AXIS));
        initContent();
    }

    private JButton makeButtonOk() {
        JButton jButton = new JButton("Ok");
        jButton.setAlignmentX(jButton.CENTER_ALIGNMENT);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myFrame.setVisible(false);
                DBInsert bdInsert = new DBInsert();
                String name = jTextFieldName.getText();
                String phone = jTextFieldPhone.getText();
                Pattern p = Pattern.compile("\\d*");
                Matcher m = p.matcher(phone);
                if (m.matches()) {
                    Contact contact = new Contact(0, name);
                    try {
                        bdInsert.insertContact(contact);
                        DBSelect dbSelect = new DBSelect();
                        int t =  dbSelect.getMaxIdContact();
                        System.out.print(t);
                        Phone phone1 = new Phone(0, t, 1, phone);
                        try {
                            bdInsert.insertPhone(phone1);
                        } catch (DBException e2) {
                            System.out.print("Phone don't commit.");
                            e2.printStackTrace();
                        }
                    } catch (DBException e1) {
                        System.out.print("Contact don't commit.");
                        e1.printStackTrace();
                    }
                }
                myFrame.dispose();
            }
        });
        return jButton;
    }

    private JButton makeButtonCancel() {
        JButton jButton = new JButton("Cancel");
        jButton.setAlignmentX(jButton.CENTER_ALIGNMENT);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myFrame.setVisible(false);
                myFrame.dispose();
            }
        });
        return jButton;
    }

    private JTextField makeTextField(String text) {
        JTextField jTextField = new JTextField(text);
        jTextField.setAlignmentX(jTextField.CENTER_ALIGNMENT);
        return jTextField;
    }

    private void initContent() {
        jTextFieldName = makeTextField("Name");
        add(jTextFieldName);
        jTextFieldPhone = makeTextField("Phone number");
        add(jTextFieldPhone);
        JPanel buttons = new JPanel();
        buttons.add(makeButtonCancel());
        buttons.add(makeButtonOk());
        add(buttons);
    }
}
