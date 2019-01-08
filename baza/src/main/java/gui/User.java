package gui;

import baza.DBConnection;
import baza.EmptyResultSetException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;

public class User extends JPanel {
    private static final long serialVersionUID = 1L;
    protected JFrame frame;
    JPanel panel;
    JButton zaloguj;
    JTextField login;
    JLabel loginL;
    JLabel hasloL;
    JPasswordField haslo;
    String l;
    String h; //
    public  User(){
        frame = new JFrame("Logowanie");
        loginL = new JLabel("Login");
        login= new JTextField("test2");
        hasloL = new JLabel("Hasło");
        haslo= new JPasswordField("test2");
        zaloguj= new JButton("zaloguj");


        loginL.setLocation(20,20);
        loginL.setSize(100,20);
        login.setLocation(120,20);
        login.setSize(100,20);
        hasloL.setLocation(20,60);
        hasloL.setSize(100,20);
        haslo.setLocation(120,60);
        haslo.setSize(100,20);
        zaloguj.setBounds(120,100,100,20);
        //zaloguj.setSize(100,20);


        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel= new JPanel();

        panel.add(login);
        panel.add(loginL);
        panel.add(haslo);
        panel.add(hasloL);
        panel.add(zaloguj);
        panel.setLayout(null);
        frame.add(login);
        frame.add(loginL);
        frame.add(hasloL);
        frame.add(haslo);
        frame.add(zaloguj);
        zaloguj.addActionListener(new Zaloguj(this.login, this.haslo));
        l= String.valueOf(this.login);
        h= String.valueOf(this.haslo);
        //zaloguj.setBounds(10,200,100,20);
        panel.add(zaloguj);
        frame.pack();
        frame.add(panel);
        frame.setSize(new Dimension(300,200));
        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // czcionka, miesjsce + actionListener
        // paint();

    }

    private class Zaloguj implements ActionListener{
        private JTextField loginTextField;
        private JPasswordField passwordTextField;

        public Zaloguj(JTextField loginTextField, JPasswordField passwordTextField) {
            this.loginTextField = loginTextField;
            this.passwordTextField = passwordTextField;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String login = this.loginTextField.getText();
            String haslo = new String(this.passwordTextField.getPassword());
            try {
                DBConnection dBConnection = new DBConnection(login, haslo); // App jest otwierane w DBC
                frame.setVisible(false);

            } catch (EmptyResultSetException e1) {
                JOptionPane.showMessageDialog(null, "Błąd logowania");
            }
            catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, "Błąd logowania");
            }
        }
    }
}
