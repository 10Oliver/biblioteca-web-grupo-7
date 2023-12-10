package sv.edu.udb.www.Recursos.Models.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.www.Recursos.Conexion.ConnectionDb;

public class Editorial {
    private int id;
    private String editorial;

    private final String SELECT_STATEMENT = "SELECT * FROM Editoriales WHERE id = ?";
    private final String SELECT_ALL_STATEMENT = "SELECT * FROM Editoriales";

    public Editorial() {}
    public Editorial(int newId,String newEditorial) {
        id = newId;
        editorial = newEditorial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public List<Editorial> selectAllEditorials(ConnectionDb connection) {
        List<Editorial> editoriales = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                setId(resultSet.getInt("id"));
                setEditorial(resultSet.getString("NombreEditorial"));
                Editorial editorial = new Editorial(getId(),getEditorial());
                editoriales.add(editorial);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all Editoriales: " + e.getMessage());
            e.printStackTrace();
        }
        return editoriales;
    }
    public Editorial selectEditorialsById(ConnectionDb connection) {
        Editorial editorial = null;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_STATEMENT);
            statement.setInt(1,getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                setId(resultSet.getInt("id"));
                setEditorial(resultSet.getString("NombreEditorial"));
                editorial = new Editorial(getId(),getEditorial());
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting Editorial: " + e.getMessage());
            e.printStackTrace();
        }
        return editorial;
    }
}
