package sv.edu.udb.www.Recursos.Models.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.www.Recursos.Conexion.ConnectionDb;

public class InstitucionAcademica {
    private int id;
    private String institucion;

    private final String SELECT_STATEMENT = "SELECT * FROM InstitucionesAcademicas WHERE id = ?";
    private final String SELECT_ALL_STATEMENT = "SELECT * FROM InstitucionesAcademicas";

    public InstitucionAcademica(){}
    public InstitucionAcademica(int newId, String institucionAcademica){
        id = newId;
        institucion = institucionAcademica;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public List<InstitucionAcademica> selectAllInstitucionAcademicas(ConnectionDb connection) {
        List<InstitucionAcademica> institutos = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                setId(resultSet.getInt("id"));
                setInstitucion((resultSet.getString("NombreInstitucion")));
                InstitucionAcademica instituto = new InstitucionAcademica(getId(),getInstitucion());
                institutos.add(instituto);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all institutos: " + e.getMessage());
            e.printStackTrace();
        }
        return institutos;
    }
    public InstitucionAcademica selectInstitucionAcademicasById(ConnectionDb connection) {
        InstitucionAcademica instituto = null;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_STATEMENT);
            statement.setInt(1,getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                setId(resultSet.getInt("id"));
                setInstitucion(resultSet.getString("NombreInstitucion"));
                instituto = new InstitucionAcademica(getId(),getInstitucion());
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting instituto: " + e.getMessage());
            e.printStackTrace();
        }
        return instituto;
    }
}
