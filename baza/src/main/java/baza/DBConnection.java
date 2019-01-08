package baza;

import gui.App;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBConnection {
    private static final String host = "jdbc:mysql://127.0.0.1:3306/baza_pracownikow"
            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode"
            + "=false&serverTimezone=UTC";
    /*private*/ User user;
    private Connection connection;

    public DBConnection(String login, String password) throws EmptyResultSetException, SQLException {
        this.user = new User(login, password, this);
        connect();
        this.user.setPracownik();
        //test
        //gui.App app = new App(this.user); // to było i działało
        Pracownik p = new Pracownik(1,this);
        System.out.println(p.toString());
        List<String> lista = user.getListaPracownikow();
        gui.App app= new App(this.user, lista);
        int i = 1;
        for(String s: lista) {
            System.out.println(i + " " + s);
            i++;
        }
        //koniec testu
    }

    private void connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String login = this.user.getLogin();
            String password = this.user.getPassword();
            this.connection = DriverManager.getConnection(host, login, password);
        } catch (ClassNotFoundException e) {
            System.out.println("DBC catch");
            e.printStackTrace();
        }
    }


    public Connection getConnection() {
        return connection;
    }
}