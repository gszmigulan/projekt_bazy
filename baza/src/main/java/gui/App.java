package gui;

import baza.DBConnection;
import baza.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

public class App extends JPanel {
    protected  JFrame frame;
    JPanel panel;
    JMenuBar bar;
    JList l_pracowników;
    User user;

    public App(baza.User user, List<String> lista){
        this.user=user;
        frame = new JFrame("Logowanie użytkownika");
        bar = new JMenuBar();
        JMenu Plik= new JMenu("Plik");
        Plik.setMnemonic(KeyEvent.VK_F);
        bar.add(Plik);
        JMenu Edycja= new JMenu("Edycja");
        Edycja.setMnemonic(KeyEvent.VK_F);
        bar.add(Edycja);
        JMenu tools= new JMenu("Narzędzia");
        tools.setMnemonic(KeyEvent.VK_F);
        bar.add(tools);
        JMenu baza= new JMenu("Baza");
        baza.setMnemonic(KeyEvent.VK_F);
        bar.add(baza);
        JMenu Słownik= new JMenu("Słownik");
        Słownik.setMnemonic(KeyEvent.VK_F);
        bar.add(Słownik);

        JMenuItem dodaj_b= new JMenuItem("dodaj do bazy", KeyEvent.VK_N);
        baza.add(dodaj_b);
        JMenuItem usuń_b= new JMenuItem("usuń z bazy", KeyEvent.VK_N);
        //baza.add(usuń_b);
        JMenuItem edytuj_b= new JMenuItem("edytuj bazę", KeyEvent.VK_N);
        //baza.add(edytuj_b);


        JMenuItem nowy = new JMenuItem("dodaj użytkownikia", KeyEvent.VK_N);
        Plik.add(nowy);
        JMenuItem wyjście = new JMenuItem("wyjście", KeyEvent.VK_N);
        Plik.add(wyjście);
        JMenuItem edit = new JMenuItem("edytuj", KeyEvent.VK_N);
        Edycja.add(edit);
        JMenuItem dodaj = new JMenuItem("dodaj", KeyEvent.VK_N);
        Edycja.add(dodaj);
        JMenuItem usuń = new JMenuItem("usuń", KeyEvent.VK_N);
        Edycja.add(usuń);
        JMenuItem szukaj = new JMenuItem("szukaj", KeyEvent.VK_N);
        tools.add(szukaj);
        JMenuItem sortuj = new JMenuItem("sortuj", KeyEvent.VK_N);
        tools.add(sortuj);
        JMenuItem słownik = new JMenuItem("słownik", KeyEvent.VK_N);
        Słownik.add(słownik);

       // jeszcze opcje edycji bazy
        frame.setJMenuBar(bar);

        dodaj_b.addActionListener(new Dodaj_b());
       // usuń_b.addActionListener(new Usuń_b());
        //edytuj_b.addActionListener(new Edytuj_b());


        wyjście.addActionListener(new Exit());
       dodaj.addActionListener(new DodajPracownika()); // nowy
       sortuj.addActionListener(new Sortuj());
       słownik.addActionListener(new Slownik());
       szukaj.addActionListener(new Szukaj());
       //baza.User user= new User(login, haslo,db);
       //System.out.println(user.uprawnienia);
       //List<String> lista= user.getListaPracownikow();
       String[] l= new String[lista.size()];

        int i = 1;
       for(String s: lista) {
           System.out.println(i + " " + s);
           l[i-1]=s;
            i++;
        }

        l_pracowników= new JList(l); // wyświetlana lista
       l_pracowników.setSelectedIndex(2);
        JScrollPane scroll= new JScrollPane(l_pracowników);
        scroll.setPreferredSize( new Dimension(1200,800));
        scroll.setViewportView(l_pracowników);
        //scroll.setMaximumSize(new Dimension(800,800));
        frame.add(scroll);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel= new JPanel();
        panel.setLayout(null);
        frame.pack();
        frame.add(panel);
        //frame.setSize(new Dimension(500,300));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public class Exit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);

        }

    }

    public class DodajPracownika implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Pracownik p  = new Pracownik(user);

        }

    }

    public class Sortuj implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // frame.setVisible(false)  // zamykam starą bazę a po wybraniu sortj otworzy się posortowana
            Sortowanie s  = new Sortowanie(user);

        }

    }

    public class Szukaj implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            Seek s  = new Seek(user);

        }

    }

    public class Slownik implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
           Instrukcja i= new Instrukcja();

        }

    }


    public class Dodaj_b implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Admin a  = new Admin(user, "dodaj");

        }

    }


  //  public class Usuń_b implements ActionListener{
    //    @Override
      //  public void actionPerformed(ActionEvent e) {
        //    Admin a  = new Admin(user, "usuń");

        //}

    //}


   // public class Edytuj_b implements ActionListener{
     //   @Override
      //  public void actionPerformed(ActionEvent e) {
        //    Admin a  = new Admin(user, "edytuj");

        //}

    //}
}
