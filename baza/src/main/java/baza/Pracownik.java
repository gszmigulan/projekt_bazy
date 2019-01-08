package baza;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pracownik {
    private int id;
    private String imie;
    private String nazwisko;
    private Stanowisko stanowisko;
    private Dzial dzial;
    private double etat;
    private String stanZatrudnienia;
    private float wynagrodzenie;
    private String pesel;
    private Date data_urodzenia;
    private String nr_telefonu;
    private String adres;
    private DBConnection dbConnection;

    public Pracownik(int id, DBConnection dbConnection) {
        this.id = id;
        this.dbConnection = dbConnection;
        this.setPracownik();
    }

    private void setPracownik() {
        String query = "SELECT * FROM pracownicy WHERE id = ?;";
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, this.id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                this.imie = result.getString(2);
                this.nazwisko = result.getString(3);
                this.stanowisko = new Stanowisko(result.getInt(4), this.dbConnection);
                this.dzial = new Dzial(result.getInt(5), this.dbConnection);
                this.etat = result.getFloat(6);
                this.stanZatrudnienia = result.getString(7);
                this.wynagrodzenie = result.getFloat(8);
                this.pesel = result.getString(9);
                this.data_urodzenia = result.getDate(10);
                this.nr_telefonu = result.getString(11);
                this.adres = result.getString(12);
            }
            result.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("pracownik catch");
            e.printStackTrace();
        }
    }

    private String to_string(String atrybut) {
        String string = "";
        if(atrybut.equals("id")) {
            string = Integer.toString(this.id);
        }
        else if(atrybut.equals("imie")) {
            string = this.imie;
        }
        else if(atrybut.equals("nazwisko")) {
            string = this.nazwisko;
        }
        else if(atrybut.equals("stanowisko")) {
            string = this.stanowisko.toString();
        }
        else if(atrybut.equals("dzial")) {
            string = this.dzial.toString();
        }
        else if(atrybut.equals("etat")) {
            string = Double.toString(this.etat);
        }
        else if(atrybut.equals("stan_zatrudnienia")) {
            string = this.stanZatrudnienia;
        }
        else if(atrybut.equals("wynagrodzenie")) {
            string = Float.toString(this.wynagrodzenie);
        }
        else if(atrybut.equals("pesel")) {
            string = this.pesel;
        }
        else if(atrybut.equals("data_urodzenia")) {
            string = this.data_urodzenia.toString();
        }
        else if(atrybut.equals("nr_telefonu")) {
            string = this.nr_telefonu;
        }
        else if(atrybut.equals("adres")) {
            string = this.adres;
        }

        return string;
    }

    public String toString() {
        String string;
        string = this.to_string("imie") + " " + this.to_string("nazwisko") + " "
                + this.to_string("stanowisko") + " " + this.to_string("dzial") + " "
                + this.to_string("nr_telefonu");
        return string;
    }

    public Dzial getDzial() {
        return this.dzial;
    }

    public int getId() {
        return this.id;
    }
}