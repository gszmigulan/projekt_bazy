package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Instrukcja extends JPanel {
    JFrame f;
    private BufferedImage image;

    public Instrukcja() {
        f= new JFrame("Słownik");

        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        JLabel pic= new JLabel(" aaa");
        try {
            image = ImageIO.read(new File("C:/Users/gszmi/OneDrive/Pulpit/instrukcja.png"));
            // to jest ten twój wykres tablic u mnie jest zapisany w tym miejscu
             pic = new  JLabel(new ImageIcon(image));
            pic.setBounds(0,0,300,200);

            f.add(pic);
        } catch (IOException ex) {
            // handle exception...
            System.out.println("nie działa");
        }

        JPanel panel= new JPanel();
        f.pack();
        f.add(panel);

        f.getContentPane().add(pic, BorderLayout.CENTER);
        f.setSize(new Dimension(975,620));
        f.add(panel, BorderLayout.CENTER);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
    }

}

