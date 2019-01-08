package baza;

//import jdk.nashorn.internal.codegen.CompilerConstants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String login;
    private String password;
    private Pracownik pracownik;
    public String uprawnienia;
    private DBConnection dbConnection;
    Connection connection;
    List<String> lista;
    List<String> l;

    public User(String login, String password, DBConnection dbConnection) {
        this.setLogin(login);
        this.setPassword(password);
        this.dbConnection = dbConnection;
        //this.setPracownik();
    }

    //TODO: public boolean addUser

    public void setPracownik() throws EmptyResultSetException {
        String query = "SELECT id_pracownika, uprawnienia FROM uzytkownicy WHERE login = ?;";
        Connection connection = dbConnection.getConnection();
        System.out.println(uprawnienia);

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, this.login);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                this.pracownik = new Pracownik(result.getInt(1), this.dbConnection);
                this.uprawnienia = result.getString(2);
            } else {
                System.out.println("to user ");
                throw new EmptyResultSetException();
            }
            result.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Znajduje pracowników działu, w którym pracuje ten użytkownik bazy.
     *
     * @return zwraca listę danych pracowników w postaci tablicy stringów (każdy string w tablicy
     * zawiera dane jednego pracownika).
     */
    public List<String> getListaPracownikow() {
		/*
		List<String> lista = new ArrayList<String>();
		String query = "SELECT id FROM pracownicy WHERE dzial=?;";
		Connection connection = dbConnection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, this.pracownik.getDzial().getId());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
		         Pracownik p = new Pracownik(result.getInt(1), this.dbConnection);
		         String string = p.toString();
		         lista.add(string);
		    }
			result.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
		*/

        List<String> lista = new ArrayList<String>();
        /*Connection*/
        connection = dbConnection.getConnection();
        if (this.uprawnienia.equals("kierownik")) {
            try {
                CallableStatement statement = connection.prepareCall("{CALL pokaz_pracownikow_dzialu(?)}");
                statement.setInt(1, this.pracownik.getId());
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    Pracownik p = new Pracownik(result.getInt(1), this.dbConnection);
                    String string = p.toString();
                    lista.add(string);
                }
                result.close();
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                CallableStatement statement = connection.prepareCall("{CALL pokaz_wszystkich_pracownikow()}");
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    Pracownik p = new Pracownik(result.getInt(1), this.dbConnection);
                    String string = p.toString();
                    lista.add(string);
                }
                result.close();
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lista;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }

    public DBConnection getDbConnection() {
        return dbConnection;
    }

    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<String> Sortuj_imie() {

        l = new ArrayList<String>();
            try {
                CallableStatement statement;
                if (this.uprawnienia.equals("kierownik")) {
                 statement = connection.prepareCall("{CALL sortnameK(?)}");
                statement.setInt(1, this.pracownik.getId());}
                else{
                     statement = connection.prepareCall("{CALL sortname()}");
                }
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    Pracownik p = new Pracownik(result.getInt(1), this.dbConnection);
                    String string = p.toString();
                    l.add(string);
                }
                result.close();
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
    return l;
    }


    public List<String> Sortuj_nazwisko() {

        l = new ArrayList<String>();
        try {
            CallableStatement statement;
            if (this.uprawnienia.equals("kierownik")) {
                statement = connection.prepareCall("{CALL sortnazwiskoK(?)}");
                statement.setInt(1, this.pracownik.getId());}
            else{
                statement = connection.prepareCall("{CALL sortnazwisko()}");
            }
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Pracownik p = new Pracownik(result.getInt(1), this.dbConnection);
                String string = p.toString();
                l.add(string);
            }
            result.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }

    public List<String> Sortuj_etat() {

        l = new ArrayList<String>();
        try {
            CallableStatement statement;
            if (this.uprawnienia.equals("kierownik")) {
                statement = connection.prepareCall("{CALL sortetatK(?)}");
                statement.setInt(1, this.pracownik.getId());}
            else{
                statement = connection.prepareCall("{CALL sortetat()}");
            }
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Pracownik p = new Pracownik(result.getInt(1), this.dbConnection);
                String string = p.toString();
                l.add(string);
            }
            result.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }
    public List<String> Sortuj_wynagrodzenie() {

        l = new ArrayList<String>();
        try {
            CallableStatement statement;
            if (this.uprawnienia.equals("kierownik")) {
                statement = connection.prepareCall("{CALL sortwynagrodzenieK(?)}");
                statement.setInt(1, this.pracownik.getId());}
            else{
                statement = connection.prepareCall("{CALL sortwynagrodzenie()}");
            }
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Pracownik p = new Pracownik(result.getInt(1), this.dbConnection);
                String string = p.toString();
                l.add(string);
            }
            result.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }

    public List<String> Sortuj_urodzenie() {

        l = new ArrayList<String>();
        try {
            CallableStatement statement;
            if (this.uprawnienia.equals("kierownik")) {
                statement = connection.prepareCall("{CALL sorturodzenieK(?)}");
                statement.setInt(1, this.pracownik.getId());}
            else{
                statement = connection.prepareCall("{CALL sorturodzenie()}");
            }
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Pracownik p = new Pracownik(result.getInt(1), this.dbConnection);
                String string = p.toString();
                l.add(string);
            }
            result.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }

    public List<String> Dodaj_dzial(String nazwa) {

        l = new ArrayList<String>();
        try {
            CallableStatement statement;

                statement = connection.prepareCall("{CALL addDzial(?)}");
            statement.setString(1,nazwa);
            ResultSet result = statement.executeQuery();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }


    public List<String> Dodaj_stanowisko(String nazwa) {

        l = new ArrayList<String>();
        try {
            CallableStatement statement;

            statement = connection.prepareCall("{CALL addStanowisko(?)}");
            statement.setString(1,nazwa);
            ResultSet result = statement.executeQuery();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }


    public List<String> Dodaj_kwalifikacje(String nazwa) {

        l = new ArrayList<String>();
        try {
            CallableStatement statement;

            statement = connection.prepareCall("{CALL addKwalifikacja(?)}");
            statement.setString(1,nazwa);
            ResultSet result = statement.executeQuery();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }

    public List<String> Szukaj( String imie, String nazwisko, String pesel, String urodzenia,
                               String adres, String telefon, String stanowisko, String dział,
                               String etat, String wynagrodzenie, String stan, boolean[] k, List<String> list_k){

        l = new ArrayList<String>();
        ResultSet r;
        try {

            int i=0;
            int j=0;
            int[] potwierdzone= new int[k.length+1];
            for(String x: list_k){
                System.out.println(x + " "+ i + " "+ k[i]);
                if(k[i]){
                    potwierdzone[j]=i+1;
                    j++;
                }
                i++;
            }
            potwierdzone[j]=0; // kiedy 0 to znaczy że koniec listy;
            CallableStatement statement;
            if (this.uprawnienia.equals("kierownik")) {
                statement = connection.prepareCall("{CALL szukajK(?)}");
                // zrobić tylko rozpocznij dla kierownika
                statement.setInt(1, this.pracownik.getId());}
            else{
                statement = connection.prepareCall("{CALL szukaj(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            }
            // wszystkie pola podaje
            if(imie.equals("")){imie="%";}
            if(nazwisko.equals("")){nazwisko="%";}
            if(pesel.equals("")){pesel="%";}
            if(urodzenia.equals("")){urodzenia="%";}
            if(adres.equals("")){adres="%";}
            if(telefon.equals("")) { telefon="%";}
            if(stanowisko.equals("")){stanowisko="%";}
            if(dział.equals("")){dział="%";}
            if(etat.equals("")){ etat="%";}
            if(wynagrodzenie.equals("")){ wynagrodzenie="%";}



            statement.setString(1,imie);
            statement.setString(2,nazwisko);
            statement.setString(3,pesel);
            statement.setString(4,urodzenia);
            statement.setString(5,adres);
            statement.setString(6,telefon);
            statement.setString(7,stanowisko);
            statement.setString(8,dział);
            statement.setString(9,etat);
            statement.setString(10,wynagrodzenie);
            statement.setString(11,stan);
            int a=0;
            for(int ia=1;ia<16;ia++ ){
                statement.setString((11+ia),"%");
            }

                while(potwierdzone[a]!=0){
                statement.setString((12+a), Integer.toString(potwierdzone[a]) );
                a++; }



            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Pracownik p = new Pracownik(result.getInt(1), this.dbConnection);
                String string = p.toString();
                l.add(string);
            }
            result.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("nie działa");
        }
        return l;

    }

    public List<String> Kwalifikacje (){
        List<String> l= new ArrayList<String>();
        try {
            CallableStatement statement = connection.prepareCall("{CALL get_kwalifikacje()}");

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String string = result.getString(1);
                l.add(string);
            }
            result.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
   return l; }
}
