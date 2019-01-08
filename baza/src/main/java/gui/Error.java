package gui;

import baza.User;

import javax.swing.*;
import java.awt.*;

public class Error {


    JFrame f;
    JLabel sort;

    JLabel a;


    public Error(String message) {
        f = new JFrame("Błąd");
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        sort = new JLabel(message);
        sort.setBounds(20, 40,300,20);
        f.add(sort);

        a = new JLabel(" ");


        f.add(a);

        //done.addActionListener(new Admin.Done());

        JPanel panel = new JPanel();
        f.pack();
        f.add(panel);
        f.setSize(new Dimension(300, 200));
        f.add(panel, BorderLayout.CENTER);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }
}
