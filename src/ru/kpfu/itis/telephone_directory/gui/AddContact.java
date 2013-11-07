package ru.kpfu.itis.telephone_directory.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JFrame mineFrame;

    public AddContact(JFrame myFrame, JFrame mineFrame) {
        this.myFrame = myFrame;
        this.mineFrame = mineFrame;
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
                mineFrame.setVisible(true);
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
                mineFrame.setVisible(true);
                myFrame.dispose();
            }
        });
        return jButton;
    }

    private JTextField makeTextField(String text) {
        JTextField jTextField = new JTextField(text);
        jTextField.setAlignmentX(jTextField.CENTER_ALIGNMENT);
        mineFrame.setLocation(myFrame.getLocation());
        return jTextField;
    }

    private void initContent() {
        JTextField jTextFieldName = makeTextField("Name");
        add(jTextFieldName);
        JTextField jTextFieldPhone = makeTextField("Phone number");
        add(jTextFieldPhone);
        JPanel buttons = new JPanel();
        buttons.add(makeButtonCancel());
        buttons.add(makeButtonOk());
        add(buttons);
    }
}
