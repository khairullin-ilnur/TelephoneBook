package ru.kpfu.itis.telephone_directory.gui;

import ru.kpfu.itis.telephone_directory.contact.Contact;
import ru.kpfu.itis.telephone_directory.contact.Phone;
import ru.kpfu.itis.telephone_directory.dbe.DBException;
import ru.kpfu.itis.telephone_directory.dbe.DBInsert;
import ru.kpfu.itis.telephone_directory.dbe.DBSelect;
import ru.kpfu.itis.telephone_directory.dbe.DBUpdate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Created with IntelliJ IDEA.
 * User: Ilnur
 * Date: 11/8/13
 * Time: 3:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class RedactPhone extends JPanel {
    private JFrame myFrame;
    private JTextField jTextFieldPhone;
    private Phone phone;

    public RedactPhone(JFrame myFrame, Phone phone) {
        this.myFrame = myFrame;
        this.phone = phone;
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
                DBUpdate dbUpdate = new DBUpdate();
                String number = jTextFieldPhone.getText();
                Pattern p = Pattern.compile("\\d*");
                Matcher m = p.matcher(number);
                if (m.matches()) {
                    try {
                        phone.setNumber(number);
                        dbUpdate.updatePhone(phone);
                    } catch (DBException e1) {
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
        jTextFieldPhone = makeTextField(phone.getNumber());
        add(jTextFieldPhone);
        JPanel buttons = new JPanel();
        buttons.add(makeButtonCancel());
        buttons.add(makeButtonOk());
        add(buttons);
    }

}
