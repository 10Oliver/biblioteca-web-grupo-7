package conf.sv.edu.udb.www.Recursos.Models.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conf.sv.edu.udb.www.Recursos.Conexion.ConnectionDb;

public class Facultad {
    private int id;
    private String facultad;

    private final String SELECT_STATEMENT = "SELECT * FROM Facultades WHERE id = ?";
    private final String SELECT_ALL_STATEMENT = "SELECT * FROM Facultades";

    public Facultad() {}
    public Facultad(int newId, String newFacultad){
        id = newId;
        facultad = newFacultad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public List<Facultad> selectAllFacultads(ConnectionDb connection) {
        List<Facultad> facultades = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                setId(resultSet.getInt("id"));
                setFacultad(resultSet.getString("NombreFacultad"));
                Facultad facultad = new Facultad(getId(),getFacultad());
                facultades.add(facultad);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all Facultad: " + e.getMessage());
            e.printStackTrace();
        }
        return facultades;
    }
    public Facultad selectFacultadsById(ConnectionDb connection) {
        Facultad facultad = null;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_STATEMENT);
            statement.setInt(1,getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                setId(resultSet.getInt("id"));
                setFacultad(resultSet.getString("NombreFacultad"));
                facultad = new Facultad(getId(),getFacultad());
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting Facultad: " + e.getMessage());
            e.printStackTrace();
        }
        return facultad;
    }
}
