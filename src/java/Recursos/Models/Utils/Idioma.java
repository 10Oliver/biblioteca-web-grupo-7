package conf.sv.edu.udb.www.Recursos.Models.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conf.sv.edu.udb.www.Recursos.Conexion.ConnectionDb;

public class Idioma {
    private int id;
    private String idioma;

    private final String SELECT_STATEMENT = "SELECT * FROM Idiomas WHERE id = ?";
    private final String SELECT_ALL_STATEMENT = "SELECT * FROM Idiomas";

    public Idioma(){}
    public Idioma(int newId, String newIdioma){
        id = newId;
        idioma = newIdioma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public List<Idioma> selectAllIdiomas(ConnectionDb connection) {
        List<Idioma> idiomas = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                setId(resultSet.getInt("id"));
                setIdioma(resultSet.getString("NombreIdioma"));
                Idioma idioma = new Idioma(getId(),getIdioma());
                idiomas.add(idioma);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all idiomas: " + e.getMessage());
            e.printStackTrace();
        }
        return idiomas;
    }
    public Idioma selectIdiomasById(ConnectionDb connection) {
        Idioma idioma = null;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_STATEMENT);
            statement.setInt(1,getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                setId(resultSet.getInt("id"));
                setIdioma(resultSet.getString("NombreIdioma"));
                idioma = new Idioma(getId(),getIdioma());
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting Idioma: " + e.getMessage());
            e.printStackTrace();
        }
        return idioma;
    }
}
