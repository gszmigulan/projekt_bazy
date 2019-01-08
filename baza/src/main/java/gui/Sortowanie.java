package gui;

import baza.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Sortowanie extends JPanel {
    JFrame f;
    JLabel sort;

    JComboBox combo;
    JLabel a;
    JButton done;
    User user;

    public  Sortowanie(baza.User user){
        this.user=user;
        f= new JFrame("Sortowanie");
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        sort= new JLabel("Sortuj według: ");
        combo= new JComboBox();
        combo.addItem("imie");
        combo.addItem("nazwisko");
        combo.addItem("etat");
        combo.addItem("wynagrodzenie");
        combo.addItem("urodzenie");
        combo.setBounds(60,60,100,20);
        f.add(combo);

        done= new JButton("Sortuj");
        a= new JLabel(" ");
        f.add(done);
        f.add(a);
        done.setBounds(60,100,100,20);
        done.addActionListener(new Done());

        JPanel panel= new JPanel();
        f.pack();
        f.add(panel);
        f.setSize(new Dimension(300,200));
        f.add(panel, BorderLayout.CENTER);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }
    private class Done implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            Object c = combo.getSelectedItem();
            String st;
            st= (String) c;
            List<String> l;
            // chciałam zrobić żeby obsługiwało każdą wybraną opcje
            // ale w sql nie działa mi procedura z nazwą tego po czym segregujemy na wejściu
            if(st=="imie"){
             l = user.Sortuj_imie();
                App app= new App(user,l);
                f.setVisible(false);}

             if(st=="nazwisko"){
                 l = user.Sortuj_nazwisko();
                 App app= new App(user,l);
                 f.setVisible(false);}

             if(st=="etat"){
            l = user.Sortuj_etat();
            App app= new App(user,l);
            f.setVisible(false);}

            if(st=="wynagrodzenie"){
                l = user.Sortuj_wynagrodzenie();
                App app= new App(user,l);
                f.setVisible(false);}

            if(st=="urodzenie"){
                l = user.Sortuj_urodzenie();
                App app= new App(user,l);
                f.setVisible(false);}
        }



            // powinno otwierać App z posortowaną bazą danych

        //}
    }
}
