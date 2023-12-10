package sv.edu.udb.www.Recursos.Models.RecursosDigitales;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sv.edu.udb.www.Recursos.Conexion.ConnectionDb;
import sv.edu.udb.www.Recursos.Models.RecursoDigital;



public class Cd extends RecursoDigital {
    private String autor;
    private String numCanciones;

    private String UPDATE_STATEMENT = "UPDATE Cds SET Titulo = ?, NumCanciones = ?, FechaPublicacion = ?, Stock = ?, idEstante = ?, idAutor = ?, idGenero = ? WHERE CodigoIdentificacion = ?";
    private String INSERT_STATEMENT = "INSERT INTO Cds (Titulo, NumCanciones, FechaPublicacion, Stock, idEstante, idAutor, idGenero) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private String DELETE_STATEMENT = "DELETE FROM Cds WHERE CodigoIdentificacion = ?;";
    private String SELECT_SINGLE_STATEMENT = "SELECT Cds.id, Cds.CodigoIdentificacion, Cds.Titulo, Cds.NumCanciones, Cds.FechaPublicacion, Cds.Stock, Estantes.NombreEstante, Autores.NombreAutor, Generos.NombreGenero FROM Cds LEFT JOIN Estantes ON Cds.idEstante = Estantes.id LEFT JOIN Autores ON Cds.idAutor = Autores.id LEFT JOIN Generos ON Cds.idGenero = Generos.id WHERE Cds.CodigoIdentificacion = ?";
    private String SELECT_ALL_STATEMENT = "SELECT Cds.id, Cds.CodigoIdentificacion, Cds.Titulo, Cds.NumCanciones, Cds.FechaPublicacion, Cds.Stock, Estantes.NombreEstante, Autores.NombreAutor, Generos.NombreGenero FROM Cds LEFT JOIN Estantes ON Cds.idEstante = Estantes.id LEFT JOIN Autores ON Cds.idAutor = Autores.id LEFT JOIN Generos ON Cds.idGenero = Generos.id";

    public Cd(int id, String codigoIdentificacion, String titulo, Date fechaPublicacion, int stock, String nombreEstante, String genero, String autor, String numCanciones) {
        super(id, codigoIdentificacion, titulo, fechaPublicacion, stock, nombreEstante, genero);
        this.autor = autor;
        this.numCanciones = numCanciones;
    }
    public Cd(String titulo, Date fechaPublicacion, int stock, String nombreEstante, String genero, String autor, String numCanciones) {
        super(titulo, fechaPublicacion, stock, nombreEstante, genero);
        this.autor = autor;
        this.numCanciones = numCanciones;
    }

    public Cd(String codigoIdentificacion) {
        setCodigoIdentificacion(codigoIdentificacion);
    }
    public Cd() {
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNumCanciones() {
        return numCanciones;
    }

    public void setNumCanciones(String numCanciones) {
        this.numCanciones = numCanciones;
    }

    public void insertCd(ConnectionDb connection) {
    try {
        int index = 1;
// private String INSERT_STATEMENT = "INSERT INTO Cds (Titulo, NumCanciones, FechaPublicacion, Stock, idEstante, idAutor, idGenero) VALUES (?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement statement = connection.getConnection().prepareStatement(INSERT_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setString(index++, getNumCanciones());
        statement.setDate(index++, getFechaPublicacion());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        statement.setInt(index++, Integer.parseInt(getAutor()));
        statement.setInt(index++, Integer.parseInt(getGenero()));
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Cd was inserted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while inserting Cd: " + e.getMessage());
        e.printStackTrace();
    }
}

public void updateCd(ConnectionDb connection) {
// private String UPDATE_STATEMENT = "UPDATE Cds SET Titulo = ?, NumCanciones = ?, FechaPublicacion = ?, Stock = ?, idEstante = ?, idAutor = ?, idGenero = ? WHERE CodigoIdentificacion = ?";
    try {
        int index = 1;
        PreparedStatement statement = connection.getConnection().prepareStatement(UPDATE_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setString(index++, getNumCanciones());
        statement.setDate(index++, getFechaPublicacion());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        statement.setInt(index++, Integer.parseInt(getAutor()));
        statement.setInt(index++, Integer.parseInt(getGenero()));
        statement.setString(index++, getCodigoIdentificacion());
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Cd was updated successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while updating Cd: " + e.getMessage());
        e.printStackTrace();
    }
}

public void deleteCd(ConnectionDb connection) {
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(DELETE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Cd was deleted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while deleting Cd: " + e.getMessage());
        e.printStackTrace();
    }
}

public Cd selectCd(ConnectionDb connection) {
    Cd cd = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Cd
            cd = mapResultSetToCdWithEstante(resultSet);
        } else {
            System.out.println("No Cd found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Cd: " + e.getMessage());
        e.printStackTrace();
    }
    return cd;
}

public List<Cd> selectAllCds(ConnectionDb connection) {
    List<Cd> cds = new ArrayList<>();
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            // Map ResultSet to Cd
            Cd cd = mapResultSetToCdWithEstante(resultSet);
            cds.add(cd);
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while selecting all Cds: " + e.getMessage());
        e.printStackTrace();
    }
    return cds;
}

private Cd mapResultSetToCdWithEstante(ResultSet resultSet) throws SQLException {
    try{
    int id = resultSet.getInt("id");
    String codigo = resultSet.getString("CodigoIdentificacion");
    String titulo = resultSet.getString("Titulo");
    Date fecha = resultSet.getDate("FechaPublicacion");
    int stock = resultSet.getInt("Stock");
    String estante = resultSet.getString("NombreEstante");
    String genero = resultSet.getString("NombreGenero");
    String autor = resultSet.getString("NombreAutor");
    String numCanciones = resultSet.getString("NumCanciones");

    setId(id);
    setCodigoIdentificacion(codigo);
    setTitulo(titulo);
    setFechaPublicacion(fecha);
    setStock(stock);
    setNombreEstante(estante);
    setGenero(genero);
    setAutor(autor);
    setNumCanciones(numCanciones);
    return new Cd(
//    resultSet.getInt("id"),resultSet.getString("CodigoIdentificacion"),resultSet.getString("Titulo"),resultSet.getDate("FechaPublicacion"),resultSet.getInt("Stock"),resultSet.getString("NombreEstante"),resultSet.getString("Genero"),resultSet.getString("Autor"),resultSet.getString("NumCanciones")
            id,codigo,titulo,fecha,stock,estante,genero,autor,numCanciones
    );
} catch (SQLException e) {
    // Log or print the actual SQL exception message
    System.out.println("Error mapping ResultSet to Cd: " + e.getMessage());
    e.printStackTrace();
    return null;
}
}

public String toJson() {
    Map<String, Object> jsonMap = new HashMap<>();
    jsonMap.put("id", getId());
    jsonMap.put("codigoIdentificacion", getCodigoIdentificacion());
    jsonMap.put("titulo", getTitulo());
    jsonMap.put("fechaPublicacion", getFechaPublicacion());
    jsonMap.put("stock", getStock());
    jsonMap.put("nombreEstante", getNombreEstante());
    jsonMap.put("genero", getGenero());
    jsonMap.put("autor", getAutor());
    jsonMap.put("numCanciones", getNumCanciones());

    StringBuilder jsonString = new StringBuilder("{");
    for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
        jsonString.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\",");
    }
    jsonString.deleteCharAt(jsonString.length() - 1); // Remove the trailing comma
    jsonString.append("}");

    return jsonString.toString();
}


public static String listToJson(List<Cd> cdList) {
    StringBuilder jsonListString = new StringBuilder("[");
    for (Cd cd : cdList) {
        jsonListString.append(cd.toJson()).append(",");
    }
    if (!cdList.isEmpty()) {
        jsonListString.deleteCharAt(jsonListString.length() - 1); // Remove the trailing comma
    }
    jsonListString.append("]");

    return jsonListString.toString();
}

public Cd selectCdByAutor(ConnectionDb connection) {
    Cd cd = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getAutor());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Cd
            cd = mapResultSetToCdWithEstante(resultSet);
        } else {
            System.out.println("No Cd found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Cd: " + e.getMessage());
        e.printStackTrace();
    }
    return cd;
}

public Cd selectCdByTitulo(ConnectionDb connection) {
    Cd cd = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getTitulo());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Cd
            cd = mapResultSetToCdWithEstante(resultSet);
        } else {
            System.out.println("No Cd found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Cd: " + e.getMessage());
        e.printStackTrace();
    }
    return cd;
}

public Cd selectCdByGenero(ConnectionDb connection) {
    Cd cd = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getGenero());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Cd
            cd = mapResultSetToCdWithEstante(resultSet);
        } else {
            System.out.println("No Cd found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Cd: " + e.getMessage());
        e.printStackTrace();
    }
    return cd;
}



}
