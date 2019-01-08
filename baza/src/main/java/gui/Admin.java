package gui;

import baza.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Admin {
    // stanowisko, dział, kwalifikacja

    JFrame f;
    JLabel sort;
    JComboBox combo;
    JTextField text;
    JLabel a;
    JButton done;
    User user;
    String option;

    public  Admin(baza.User user,String option ){
        this.option= option;
        this.user=user;
        f= new JFrame(option);
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        sort= new JLabel(option+" :");
        text= new JTextField();
        combo= new JComboBox();
        combo.addItem("dzial");
        combo.addItem("stanowisko");
        combo.addItem("kwalifikacja");
        combo.setBounds(60,20,100,20);
        f.add(combo);
        f.add(text);
        done= new JButton(option);
        a= new JLabel(" ");

        f.add(done);
        f.add(a);
        text.setBounds(30,60,200,20);
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

          //if(option.equals("dodaj")){
            if(text.getText().equals("")){
                Error er1= new Error("Nie podano nazwy");
            }
            else{
            if(user.uprawnienia.equals("administrator")){

              Object c = combo.getSelectedItem();
              String st;
              st= (String) c;
              List<String> l;
              String mess= text.getText();
              if(st=="dzial"){
                  l = user.Dodaj_dzial(mess);
                  text.setText("");
                  }
                if(st=="stanowisko"){
                    l = user.Dodaj_stanowisko(mess);
                    text.setText("");
                    }
                if(st=="kwalifikacja"){
                    l = user.Dodaj_kwalifikacje(mess);
                    text.setText("");
                    }

          }
          else{
              Error er= new Error("nie posiadasz wystarczających uprawnień");
            }}

        }
    }
}


