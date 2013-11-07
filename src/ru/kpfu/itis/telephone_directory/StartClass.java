package ru.kpfu.itis.telephone_directory;

import ru.kpfu.itis.telephone_directory.gui.Contacts;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ilnur
 * Date: 10/29/13
 * Time: 10:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class StartClass {

    public static void main (String[] args){
        JFrame frame = new JFrame("Telephone");
        frame.setLocation(0,50);
        frame.setContentPane(new Contacts(frame));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(200,700);
        frame.setVisible(true);
    }}
