package baza;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Stanowisko {
    private int id;
    private String nazwa;
    private DBConnection dbConnection;

    public Stanowisko(int id, DBConnection dbConnection) {
        this.id = id;
        this.dbConnection = dbConnection;
        this.setStanowisko();
    }

    private void setStanowisko() {
        String query = "SELECT nazwa FROM stanowiska WHERE id = ?;";
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, this.id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                this.nazwa = result.getString(1);
            }
            result.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("Stanowisko catch");
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
