package sv.edu.udb.www.Recursos.Models.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.www.Recursos.Conexion.ConnectionDb;

public class LugarPublicacion {
    private int id;
    private String lugarPublicacion;

    private final String SELECT_STATEMENT = "SELECT * FROM LugaresPublicacion WHERE id = ?";
    private final String SELECT_ALL_STATEMENT = "SELECT * FROM LugaresPublicacion";

    public LugarPublicacion() {}
    public LugarPublicacion(int newId, String lugar) {
        id = newId;
        lugarPublicacion = lugar;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLugarPublicacion() {
        return lugarPublicacion;
    }

    public void setLugarPublicacion(String lugarPublicacion) {
        this.lugarPublicacion = lugarPublicacion;
    }

    public List<LugarPublicacion> selectAllLugarPublicacions(ConnectionDb connection) {
        List<LugarPublicacion> lugares = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                setId(resultSet.getInt("id"));
                setLugarPublicacion((resultSet.getString("NombreLugarPublicacion")));
                LugarPublicacion lugar = new LugarPublicacion(getId(),getLugarPublicacion());
                lugares.add(lugar);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all Lugares: " + e.getMessage());
            e.printStackTrace();
        }
        return lugares;
    }
    public LugarPublicacion selectLugarPublicacionsById(ConnectionDb connection) {
        LugarPublicacion lugar = null;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_STATEMENT);
            statement.setInt(1,getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                setId(resultSet.getInt("id"));
                setLugarPublicacion((resultSet.getString("NombreLugarPublicacion")));
                lugar = new LugarPublicacion(getId(),getLugarPublicacion());
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting Lugar: " + e.getMessage());
            e.printStackTrace();
        }
        return lugar;
    }
}
