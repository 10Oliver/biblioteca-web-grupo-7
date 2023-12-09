package Recursos.Models.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Recursos.Conexion.ConnectionDb;

public class Productor {
    private int id;
    private String productor;

    private final String SELECT_STATEMENT = "SELECT * FROM Productores WHERE id = ?";
    private final String SELECT_ALL_STATEMENT = "SELECT * FROM Productores";

    public Productor(int productorId, String productorName)
    {
        id = productorId;
        productor = productorName;
    }

    public Productor()
    {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public List<Productor> selectAllProductors(ConnectionDb connection) {
        List<Productor> productores = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                setId(resultSet.getInt("id"));
                setProductor((resultSet.getString("NombreProductor")));
                Productor productor = new Productor(getId(),getProductor());
                productores.add(productor);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all productores: " + e.getMessage());
            e.printStackTrace();
        }
        return productores;
    }
    public Productor selectProductorsById(ConnectionDb connection) {
        Productor productor = null;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_STATEMENT);
            statement.setInt(1,getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                setId(resultSet.getInt("id"));
                setProductor((resultSet.getString("NombreProductor")));
                productor = new Productor(getId(),getProductor());
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting productor: " + e.getMessage());
            e.printStackTrace();
        }
        return productor;
    }
}
