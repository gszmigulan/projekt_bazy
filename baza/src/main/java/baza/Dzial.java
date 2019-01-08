package baza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dzial {
    private int id;
    private String nazwa;
    private DBConnection dbConnection;

    public Dzial(int id, DBConnection dbConnection) {
        this.id = id;
        this.dbConnection = dbConnection;
        this.setDzial();
    }

    private void setDzial() {
        String query = "SELECT nazwa FROM dzialy WHERE id = ?;";
        Connection connection = dbConnection.getConnection();
       try {
            PreparedStatement statement = connection.prepareStatement(query);
            System.out.println("");
            statement.setInt(1, this.id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {

                this.nazwa = result.getString(1);
           }
            result.close();
            statement.close();

       } catch (SQLException e) {
            System.out.println("Dzial catch");
            e.printStackTrace();

        }
    }

    public String toString() {
        return this.nazwa;
    }

    public int getId() {
        return this.id;
    }
}
