package sv.edu.udb.www.Recursos.Models.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.www.Recursos.Conexion.ConnectionDb;

public class PaisCiudad {
    private int id;
    private String paisCiudad;

    private final String SELECT_STATEMENT = "SELECT * FROM PaisCiudad WHERE id = ?";
    private final String SELECT_ALL_STATEMENT = "SELECT * FROM PaisCiudad";

    public PaisCiudad() {}
    public PaisCiudad(int newId, String paisCiudadName) {
        id = newId;
        paisCiudad = paisCiudadName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaisCiudad() {
        return paisCiudad;
    }

    public void setPaisCiudad(String paisCiudad) {
        this.paisCiudad = paisCiudad;
    }

    public List<PaisCiudad> selectAllPaisCiudads(ConnectionDb connection) {
        List<PaisCiudad> paisciudades = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                setId(resultSet.getInt("id"));
                setPaisCiudad((resultSet.getString("NombrePaisCiudad")));
                PaisCiudad paisCiudad = new PaisCiudad(getId(),getPaisCiudad());
                paisciudades.add(paisCiudad);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all paises o ciudades: " + e.getMessage());
            e.printStackTrace();
        }
        return paisciudades;
    }
    public Rol selectRolsById(ConnectionDb connection) {
        Rol rol = null;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_STATEMENT);
            statement.setInt(1,getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                setId(resultSet.getInt("id"));
                setPaisCiudad((resultSet.getString("NombrePaisCiudad")));
                rol = new Rol(getId(),getPaisCiudad());
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting pais o ciudad: " + e.getMessage());
            e.printStackTrace();
        }
        return rol;
    }
}