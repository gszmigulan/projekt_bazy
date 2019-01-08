package gui;

import baza.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Pracownik extends JPanel {
    protected JFrame frame;
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
    JCheckBox[] box;
User user;
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
JButton done;

 // to okno można też zrobić żeby przy zmianie danych było otwierane z parametrami
 // wyświetlanymi od razu w jtextfield w "" a jak jest nowy to jest String "";
public Pracownik(User user){
    this.user= user;
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
    next1= new JLabel(" ");
    next= new JButton("Dalej");



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
    frame.add(next1);
    next.addActionListener(new Next());
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
    next.setBounds(120,420,100,20);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    panel.setLayout(null);
    frame.pack();
    frame.add(panel);
    frame.setSize(new Dimension(300,500));
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}
    public class Next implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.getContentPane().removeAll();
            frame.repaint();
            kwalifikacje();
        }

    }
    public void kwalifikacje(){
        done = new JButton("Dodaj");
        List<String> list_k= user.Kwalifikacje();
        box= new JCheckBox[list_k.size()+1];
        int i=0;
        for(String value: list_k){
            box[i]= new JCheckBox(value);
            box[i].setBounds(20,20+(i*40),300,20);
            frame.add(box[i]);
            i++;
        }
        frame.add(done);
        done.setBounds(120,20+(i*40),100,20);
        done.addActionListener(new Done());

    }
    public class Done implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //  powinno pobierać info z pól i dodawać do bazy
            frame.setVisible(false);

        }

    }

}
