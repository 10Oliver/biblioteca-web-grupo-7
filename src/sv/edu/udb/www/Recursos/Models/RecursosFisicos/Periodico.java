package sv.edu.udb.www.Recursos.Models.RecursosFisicos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sv.edu.udb.www.Recursos.Conexion.ConnectionDb;
import sv.edu.udb.www.Recursos.Models.RecursoFisico;

public class Periodico extends RecursoFisico {
    private String nombrePeriodico;
    private String lugarPublicacion;

    private String UPDATE_STATEMENT = "UPDATE Periodicos SET Titulo = ?, NombrePeriodico = ?, FechaPublicacion = ?, NumeroPaginas = ?, LugarPublicacion = ?, Stock = ?, idEstante = ? WHERE CodigoIdentificacion = ?;";
    private String INSERT_STATEMENT = "INSERT INTO Periodicos (Titulo, NombrePeriodico, FechaPublicacion, NumeroPaginas, LugarPublicacion, Stock, idEstante) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private String DELETE_STATEMENT = "DELETE FROM Periodicos WHERE CodigoIdentificacion = ?;";
    private String SELECT_SINGLE_STATEMENT = "SELECT Periodicos.*, LugaresPublicacion.NombreLugarPublicacion, Estantes.NombreEstante FROM Periodicos INNER JOIN LugaresPublicacion ON Periodicos.idLugarPublicacion = LugaresPublicacion.id INNER JOIN Estantes ON Periodicos.idEstante = Estantes.id WHERE Periodicos.id = ?";
    private String SELECT_ALL_STATEMENT = "SELECT Periodicos.*, LugaresPublicacion.NombreLugarPublicacion, Estantes.NombreEstante FROM Periodicos INNER JOIN LugaresPublicacion ON Periodicos.idLugarPublicacion = LugaresPublicacion.id INNER JOIN Estantes ON Periodicos.idEstante = Estantes.id";

    public Periodico(int id, String codigoIdentificacion, String titulo, Date fechaPublicacion, int stock, String nombreEstante, int numeroPaginas, String nombrePeriodico, String lugarPublicacion) {
        super(id, codigoIdentificacion, titulo, fechaPublicacion, stock, nombreEstante, numeroPaginas);
        this.nombrePeriodico = nombrePeriodico;
        this.lugarPublicacion = lugarPublicacion;
    }
    public Periodico(String titulo, Date fechaPublicacion, int stock, String nombreEstante, int numeroPaginas, String nombrePeriodico, String lugarPublicacion) {
        super(titulo, fechaPublicacion, stock, nombreEstante, numeroPaginas);
        this.nombrePeriodico = nombrePeriodico;
        this.lugarPublicacion = lugarPublicacion;
    }

    public Periodico(String codigoIdentificacion) {
        setCodigoIdentificacion(codigoIdentificacion);
    }

    public Periodico() {
    }

    public String getNombrePeriodico() {
        return nombrePeriodico;
    }

    public void setNombrePeriodico(String nombrePeriodico) {
        this.nombrePeriodico = nombrePeriodico;
    }

    public String getLugarPublicacion() {
        return lugarPublicacion;
    }

    public void setLugarPublicacion(String lugarPublicacion) {
        this.lugarPublicacion = lugarPublicacion;
    }



    public Periodico selectPeriodico(ConnectionDb connection) {
    Periodico periodico = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to periodico
            // periodico periodico = null;
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setNombrePeriodico(resultSet.getString("NombrePeriodico"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setNumeroPaginas(resultSet.getInt("NumeroPaginas"));
            setLugarPublicacion(resultSet.getString("NombreLugarPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            periodico = new Periodico(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getNumeroPaginas(),getNombrePeriodico(),getLugarPublicacion());
        } else {
            System.out.println("No Periodico found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Periodico: " + e.getMessage());
        e.printStackTrace();
    }
    return periodico;
}

public List<Periodico> selectAllPeriodico(ConnectionDb connection) {
    List<Periodico> periodicos = new ArrayList<Periodico>();
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to periodico
            // periodico periodico = null;
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setNombrePeriodico(resultSet.getString("NombrePeriodico"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setNumeroPaginas(resultSet.getInt("NumeroPaginas"));
            setLugarPublicacion(resultSet.getString("NombreLugarPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            Periodico periodico = new Periodico(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getNumeroPaginas(),getNombrePeriodico(),getLugarPublicacion());
            periodicos.add(periodico);
        } else {
            System.out.println("No Periodico found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Periodico: " + e.getMessage());
        e.printStackTrace();
    }
    return periodicos;
}

public void insertPeriodico(ConnectionDb connection) {
    try {
        int index = 1;
        PreparedStatement statement = connection.getConnection().prepareStatement(INSERT_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setString(index++, getNombrePeriodico());
        statement.setDate(index++, getFechaPublicacion());
        statement.setInt(index++, getNumeroPaginas());
        statement.setString(index++, getLugarPublicacion());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Periodico was inserted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while inserting Periodico: " + e.getMessage());
        e.printStackTrace();
    }
}

public void updatePeriodico(ConnectionDb connection) {
    try {
        int index = 1;
        PreparedStatement statement = connection.getConnection().prepareStatement(UPDATE_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setString(index++, getNombrePeriodico());
        statement.setDate(index++, getFechaPublicacion());
        statement.setInt(index++, getNumeroPaginas());
        statement.setString(index++, getLugarPublicacion());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        statement.setString(index++, getCodigoIdentificacion());
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Periodico was updated successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while updating Periodico: " + e.getMessage());
        e.printStackTrace();
    }
}

public void deletePeriodico(ConnectionDb connection) {
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(DELETE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Periodico was deleted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while deleting Periodico: " + e.getMessage());
        e.printStackTrace();
    }
}
public String toJson() {
    Map<String, Object> jsonMap = new HashMap<>();
    jsonMap.put("id", getId());
    jsonMap.put("codigoIdentificacion", getCodigoIdentificacion());
    jsonMap.put("titulo", getTitulo());
    jsonMap.put("nombrePeriodico", getNombrePeriodico());
    jsonMap.put("fechaPublicacion", getFechaPublicacion());
    jsonMap.put("numeroPaginas", getNumeroPaginas());
    jsonMap.put("lugarPublicacion", getLugarPublicacion());
    jsonMap.put("stock", getStock());
    jsonMap.put("nombreEstante", getNombreEstante());

    StringBuilder jsonString = new StringBuilder("{");
    for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
        jsonString.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\",");
    }
    jsonString.deleteCharAt(jsonString.length() - 1); // Remove the trailing comma
    jsonString.append("}");

    return jsonString.toString();
}

public static String listPeriodicoToJson(List<Periodico> periodicoList) {
    StringBuilder jsonListString = new StringBuilder("[");
    for (Periodico periodico : periodicoList) {
        jsonListString.append(periodico.toJson()).append(",");
    }
    if (!periodicoList.isEmpty()) {
        jsonListString.deleteCharAt(jsonListString.length() - 1); // Remove the trailing comma
    }
    jsonListString.append("]");

    return jsonListString.toString();
}
public Periodico selectPeriodicoByTitulo(ConnectionDb connection) {
    Periodico periodico = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getTitulo());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to periodico
            // periodico periodico = null;
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setNombrePeriodico(resultSet.getString("NombrePeriodico"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setNumeroPaginas(resultSet.getInt("NumeroPaginas"));
            setLugarPublicacion(resultSet.getString("LugarPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            periodico = new Periodico(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getNumeroPaginas(),getNombrePeriodico(),getLugarPublicacion());
        } else {
            System.out.println("No Periodico found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Periodico: " + e.getMessage());
        e.printStackTrace();
    }
    return periodico;
}

public Periodico selectPeriodicoByLugarPublicacion(ConnectionDb connection) {
    Periodico periodico = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getLugarPublicacion());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to periodico
            // periodico periodico = null;
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setNombrePeriodico(resultSet.getString("NombrePeriodico"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setNumeroPaginas(resultSet.getInt("NumeroPaginas"));
            setLugarPublicacion(resultSet.getString("LugarPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            periodico = new Periodico(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getNumeroPaginas(),getNombrePeriodico(),getLugarPublicacion());
        } else {
            System.out.println("No Periodico found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Periodico: " + e.getMessage());
        e.printStackTrace();
    }
    return periodico;
}

public Periodico selectPeriodicoByNombrePeriodico(ConnectionDb connection) {
    Periodico periodico = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getNombrePeriodico());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to periodico
            // periodico periodico = null;
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setNombrePeriodico(resultSet.getString("NombrePeriodico"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setNumeroPaginas(resultSet.getInt("NumeroPaginas"));
            setLugarPublicacion(resultSet.getString("LugarPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            periodico = new Periodico(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getNumeroPaginas(),getNombrePeriodico(),getLugarPublicacion());
        } else {
            System.out.println("No Periodico found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Periodico: " + e.getMessage());
        e.printStackTrace();
    }
    return periodico;
}

}
