package sv.edu.udb.www.Recursos.Models.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.www.Recursos.Conexion.ConnectionDb;

public class Autor {
    private int id;
    private String autor;

    private final String SELECT_STATEMENT = "SELECT * FROM Autores WHERE id = ?";
    private final String SELECT_ALL_STATEMENT = "SELECT * FROM Autores";

    public Autor(){

    }
    public Autor(int newId, String nameAutor){
        id = newId;
        autor = nameAutor;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<Autor> selectAllAutors(ConnectionDb connection) {
        List<Autor> autores = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                setId(resultSet.getInt("id"));
                setAutor((resultSet.getString("NombreAutor")));
                Autor autor = new Autor(getId(),getAutor());
                autores.add(autor);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all autores: " + e.getMessage());
            e.printStackTrace();
        }
        return autores;
    }
    public Autor selectAutorsById(ConnectionDb connection) {
        Autor autor = null;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_STATEMENT);
            statement.setInt(1,getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                setId(resultSet.getInt("id"));
                setAutor((resultSet.getString("NombreAutor")));
                autor = new Autor(getId(),getAutor());
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting Autor: " + e.getMessage());
            e.printStackTrace();
        }
        return autor;
    }
}
