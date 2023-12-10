package Recursos.Models.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Recursos.Conexion.ConnectionDb;

public class Director {
    private int id;
    private String director;

    private final String SELECT_STATEMENT = "SELECT * FROM Directores WHERE id = ?";
    private final String SELECT_ALL_STATEMENT = "SELECT * FROM Directores";

    public Director(){

    }

    public Director(int newId, String newDirector){
        id = newId;
        director = newDirector;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<Director> selectAllDirectors(ConnectionDb connection) {
        List<Director> directores = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                setId(resultSet.getInt("id"));
                setDirector(resultSet.getString("NombreDirector"));
                Director director = new Director(getId(),getDirector());
                directores.add(director);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all Directores: " + e.getMessage());
            e.printStackTrace();
        }
        return directores;
    }
    public Director selectDirectorById(ConnectionDb connection) {
        Director director = null;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_STATEMENT);
            statement.setInt(1,getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                setId(resultSet.getInt("id"));
                setDirector(resultSet.getString("NombreDirector"));
                director = new Director(getId(),getDirector());
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting Director: " + e.getMessage());
            e.printStackTrace();
        }
        return director;
    }
}
