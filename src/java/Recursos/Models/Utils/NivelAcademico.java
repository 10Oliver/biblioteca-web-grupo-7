package Recursos.Models.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Recursos.Conexion.ConnectionDb;

public class NivelAcademico {
    private int id;
    private String NivelAcademico;

    private final String SELECT_STATEMENT = "SELECT * FROM NivelesAcademicos WHERE id = ?";
    private final String SELECT_ALL_STATEMENT = "SELECT * FROM NivelesAcademicos";

    public NivelAcademico() {}
    public NivelAcademico(int newId, String newNivel ) {
        id = newId;
        NivelAcademico = newNivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNivelAcademico() {
        return NivelAcademico;
    }

    public void setNivelAcademico(String nivelAcademico) {
        NivelAcademico = nivelAcademico;
    }

    public List<NivelAcademico> selectAllNivelAcademicos(ConnectionDb connection) {
        List<NivelAcademico> niveles = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                setId(resultSet.getInt("id"));
                setNivelAcademico((resultSet.getString("NivelAcademico")));
                NivelAcademico nivelAcedAcademico = new NivelAcademico(getId(),getNivelAcademico());
                niveles.add(nivelAcedAcademico);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all Niveles Academicos: " + e.getMessage());
            e.printStackTrace();
        }
        return niveles;
    }
    public NivelAcademico selectNivelAcademicosById(ConnectionDb connection) {
        NivelAcademico nivel = null;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_STATEMENT);
            statement.setInt(1,getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                setId(resultSet.getInt("id"));
                setNivelAcademico((resultSet.getString("NivelAcademico")));
                nivel = new NivelAcademico(getId(),getNivelAcademico());
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting NivelAcademico: " + e.getMessage());
            e.printStackTrace();
        }
        return nivel;
    }
}
