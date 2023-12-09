package conf.sv.edu.udb.www.Recursos.Models.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conf.sv.edu.udb.www.Recursos.Conexion.ConnectionDb;

public class Genero {
    private int id;
    private String Genero;

    private final String SELECT_STATEMENT = "SELECT * FROM Generos WHERE id = ?";
    private final String SELECT_ALL_STATEMENT = "SELECT * FROM Generos";

    public Genero(){}
    public Genero(int newId, String genero){
        id = newId;
        Genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public List<Genero> selectAllGeneros(ConnectionDb connection) {
        List<Genero> generos = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                setId(resultSet.getInt("id"));
                setGenero(resultSet.getString("NombreGenero"));
                Genero productor = new Genero(getId(),getGenero());
                generos.add(productor);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all generos: " + e.getMessage());
            e.printStackTrace();
        }
        return generos;
    }
    public Genero selectGenerosById(ConnectionDb connection) {
        Genero genero = null;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_STATEMENT);
            statement.setInt(1,getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                setId(resultSet.getInt("id"));
                setGenero(resultSet.getString("NombreGenero"));
                genero = new Genero(getId(),getGenero());
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting genero: " + e.getMessage());
            e.printStackTrace();
        }
        return genero;
    }
}
