package gui;

import baza.User;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Seek extends JPanel {
    JCheckBox[] box;
    List<String> list_k;
    JFrame frame;
    JPanel panel;
    JTextField imie;
    JTextField nazwisko;
    JTextField pesel;
    JTextField urodzenia;
    JTextField adres;
    JTextField telefon;
    JTextField stanowisko;
    JTextField dział;
    JTextField etat;
    JTextField wynagrodzenie;

    JLabel imieL;
    JLabel nazwiskoL;
    JLabel peselL;
    JLabel urodzeniaL;
    JLabel adresL;
    JLabel telefonL;
    JLabel stanowiskoL;
    JLabel działL;
    JLabel etatL;
    JLabel wynagrodzenieL;
    JLabel next1;

    JButton next;
   // JButton done;

    JCheckBox k1;
    JCheckBox k2;
    JCheckBox k3;
    JCheckBox k4;
    JCheckBox k5;
    JCheckBox k6;
    JCheckBox k7;
    JCheckBox k8;
    final User user;

    public Seek(final baza.User user){
        this.user=user;
        frame = new JFrame("Dodaj nowego pracownika");
        panel= new JPanel();
        imie= new JTextField("");
        nazwisko = new JTextField("");
        imieL= new JLabel("Imie");
        nazwiskoL= new JLabel("Nazwisko");
        pesel= new JTextField("");
        peselL= new JLabel("PESEL");
        urodzenia= new JTextField("");
        urodzeniaL =new JLabel("Data urodzenia");
        adres= new JTextField("");
        adresL= new JLabel("Adres");
        telefon= new JTextField("");
        telefonL= new JLabel("nr telefonu");
        stanowisko= new JTextField("");
        stanowiskoL= new JLabel("Stanowisko");
        dział= new JTextField("");
        działL= new JLabel("Dział");
        etat= new JTextField("");
        etatL= new JLabel("Etat");
        wynagrodzenie= new JTextField("");
        wynagrodzenieL= new JLabel("Wynagrodzenie");
        k1 = new JCheckBox("zatrudniony");
        k2 = new JCheckBox("zwolniony");

        next= new JButton("Szukaj");

        next1= new JLabel(" ");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stan="2";
                // zrobić listę boollead jeśli jest 1 to zaznaczone
                boolean[] kwalifikacje= new boolean[box.length]; // +1?
                int i=0;
                for(JCheckBox a: box){
                    try{
                    if(a.isSelected()){ kwalifikacje[i]=true; System.out.println("true");}
                    else{kwalifikacje[i]=false; System.out.println("false");}
                    i++;}
                    catch(NullPointerException ex){}
                }
                List<String> l;
                try {
                     stan = "2";
                    if (k1.isSelected()) {
                        stan = "1";
                    }
                    if (k2.isSelected()) {
                        stan = "0";
                    }
                    if (k1.isSelected() && k2.isSelected()) {
                        stan = "3";
                    } // błąd
                }
                catch (NullPointerException exx){}
                l = (List<String>) user.Szukaj(imie.getText(), nazwisko.getText(),pesel.getText(),urodzenia.getText(),
                        adres.getText(),telefon.getText(),stanowisko.getText(),dział.getText(),
                        etat.getText(), wynagrodzenie.getText(),stan,kwalifikacje,list_k);

                App app= new App(user,l);
                frame.setVisible(false);
            }
        });


        frame.add(imie);
        frame.add(nazwisko);
        frame.add(imieL);
        frame.add(nazwiskoL);
        frame.add(pesel);
        frame.add(peselL);
        frame.add(urodzenia);
        frame.add(urodzeniaL);
        frame.add(adres);
        frame.add(adresL);
        frame.add(telefon);
        frame.add(telefonL);
        frame.add(stanowisko);
        frame.add(stanowiskoL);
        frame.add(dział);
        frame.add(działL);
        frame.add(etat);
        frame.add(etatL);
        frame.add(wynagrodzenie);
        frame.add(wynagrodzenieL);
        frame.add(next);
        //frame.add(next1);


        imieL.setLocation(20,20);
        imieL.setSize(100,20);
        imie.setLocation(120,20);
        imie.setSize(100,20);
        nazwiskoL.setLocation(20,60);
        nazwiskoL.setSize(120,20);
        nazwisko.setLocation(120,60);
        nazwisko.setSize(100,20);
        pesel.setBounds(120,100,100,20);
        peselL.setBounds(20,100,100,20);
        urodzenia.setBounds(120,140,100,20);
        urodzeniaL.setBounds(20,140,100,20);
        adres.setBounds(120,180,100,20);
        adresL.setBounds(20,180,100,20);
        telefon.setBounds(120,220,100,20);
        telefonL.setBounds(20,220,100,20);
        stanowisko.setBounds(120,260,100,20);
        stanowiskoL.setBounds(20,260,100,20);
        dział.setBounds(120,300,100,20);
        działL.setBounds(20,300,100,20);
        etat.setBounds(120,340,100,20);
        etatL.setBounds(20,340,100,20);
        wynagrodzenie.setBounds(120,380,100,20);
        wynagrodzenieL.setBounds(20,380,100,20);
        //next.setBounds(400,420,100,20);
        k1.setBounds(300,20,300,20);
        k2.setBounds(300,60,300,20);
        frame.add(k1);
        frame.add(k2);

// może zrobić jeszcze tę ilość lat w zawodzie czy co to on mówił
        // coś w bazie jest nie tak bo te kwalifikacje są dodane po kilka razy
        /*List<String>*/ list_k= user.Kwalifikacje();
        box= new JCheckBox[list_k.size()+1];
        int i=0;
        for(String value: list_k){
            box[i]= new JCheckBox(value);
            box[i].setBounds(300,100+ (i*40),300,20);
            frame.add(box[i]);
            i++;
        }
        next.setBounds(400,100+(i*40),100,20);

        frame.add(next1);
        next1= new JLabel(" ");


        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        panel.setLayout(null);
        frame.pack();
        frame.add(panel);
        frame.setSize(new Dimension(600,600));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }



}
