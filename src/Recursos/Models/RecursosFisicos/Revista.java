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

public class Revista extends RecursoFisico {
    private String autor;
    private int isbn;
    private String periodicidad;
    private String paisCiudad;
    private String notas;
    private String editorial;

    private String UPDATE_STATEMENT = "UPDATE Revistas SET Titulo = ?, NumeroPaginas = ?, ISBN = ?, Periodicidad = ?, FechaPublicacion = ?, Notas = ?, Stock = ?, idEstante = ?, idAutor = ?, idEditorial = ?, idPaisCiudad = ? WHERE CodigoIdentificacion = ?;";
    private String INSERT_STATEMENT = "INSERT INTO Revistas (Titulo, NumeroPaginas, ISBN, Periodicidad, FechaPublicacion, Notas, Stock, idEstante, idAutor, idEditorial, idPaisCiudad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String DELETE_STATEMENT = "DELETE FROM Revistas WHERE CodigoIdentificacion = ?;";
    private String SELECT_SINGLE_STATEMENT = "SELECT Revistas.*, Autores.NombreAutor, Editoriales.NombreEditorial, Estantes.NombreEstante, PaisCiudad.NombrePaisCiudad FROM Revistas INNER JOIN Autores ON Revistas.idAutor = Autores.id INNER JOIN Editoriales ON Revistas.idEditorial = Editoriales.id INNER JOIN Estantes ON Revistas.idEstante = Estantes.id INNER JOIN PaisCiudad ON Revistas.idPaisCiudad = PaisCiudad.id WHERE Revistas.id = ?";
    private String SELECT_ALL_STATEMENT = "SELECT Revistas.*, Autores.NombreAutor, Editoriales.NombreEditorial, Estantes.NombreEstante, PaisCiudad.NombrePaisCiudad FROM Revistas INNER JOIN Autores ON Revistas.idAutor = Autores.id INNER JOIN Editoriales ON Revistas.idEditorial = Editoriales.id INNER JOIN Estantes ON Revistas.idEstante = Estantes.id INNER JOIN PaisCiudad ON Revistas.idPaisCiudad = PaisCiudad.id";

    public Revista(int id, String codigoIdentificacion, String titulo, Date fechaPublicacion, int stock, String nombreEstante, int numeroPaginas, String autor, int isbn, String periodicidad, String paisCiudad, String notas, String editorial) {
        super(id, codigoIdentificacion, titulo, fechaPublicacion, stock, nombreEstante, numeroPaginas);
        this.autor = autor;
        this.isbn = isbn;
        this.periodicidad = periodicidad;
        this.paisCiudad = paisCiudad;
        this.notas = notas;
        this.editorial = editorial;
    }
    public Revista(String titulo, Date fechaPublicacion, int stock, String nombreEstante, int numeroPaginas, String autor, int isbn, String periodicidad, String paisCiudad, String notas, String editorial) {
        super(titulo, fechaPublicacion, stock, nombreEstante, numeroPaginas);
        this.autor = autor;
        this.isbn = isbn;
        this.periodicidad = periodicidad;
        this.paisCiudad = paisCiudad;
        this.notas = notas;
        this.editorial = editorial;
    }

    public Revista(String codigoIdentificacion) {
        setCodigoIdentificacion(codigoIdentificacion);
    }

    public Revista() {
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    public String getPaisCiudad() {
        return paisCiudad;
    }

    public void setPaisCiudad(String paisCiudad) {
        this.paisCiudad = paisCiudad;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

public void insertRevista(ConnectionDb connection) {
    try {
        int index = 1;
// private String INSERT_STATEMENT = "INSERT INTO Revistas (Titulo, NumeroPaginas, ISBN, Periodicidad, FechaPublicacion, Notas, Stock, idEstante, idAutor, idEditorial, idPaisCiudad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.getConnection().prepareStatement(INSERT_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setInt(index++, getNumeroPaginas());
        statement.setInt(index++, getIsbn());
        statement.setString(index++, getPeriodicidad());
        statement.setDate(index++, getFechaPublicacion());
        statement.setString(index++, getNotas());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        statement.setInt(index++, Integer.parseInt(getAutor()));
        statement.setInt(index++, Integer.parseInt(getEditorial()));
        statement.setInt(index++, Integer.parseInt(getPaisCiudad()));
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Revista was inserted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while inserting Revista: " + e.getMessage());
        e.printStackTrace();
    }
}

public void updateRevista(ConnectionDb connection) {
    try {
        int index = 1;
// private String UPDATE_STATEMENT = "UPDATE Revistas SET Titulo = ?, NumeroPaginas = ?, ISBN = ?, Periodicidad = ?, FechaPublicacion = ?, Notas = ?, Stock = ?, idEstante = ?, idAutor = ?, idEditorial = ?, idPaisCiudad = ? WHERE CodigoIdentificacion = ?;";
        PreparedStatement statement = connection.getConnection().prepareStatement(UPDATE_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setInt(index++, getNumeroPaginas());
        statement.setInt(index++, getIsbn());
        statement.setString(index++, getPeriodicidad());
        statement.setDate(index++, getFechaPublicacion());
        statement.setString(index++, getNotas());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        statement.setInt(index++, Integer.parseInt(getAutor()));
        statement.setInt(index++, Integer.parseInt(getEditorial()));
        statement.setInt(index++, Integer.parseInt(getPaisCiudad()));
        statement.setString(index++, getCodigoIdentificacion());
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Revista was updated successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while updating Revista: " + e.getMessage());
        e.printStackTrace();
    }
}

public void deleteRevista(ConnectionDb connection) {
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(DELETE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Revista was deleted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while deleting Revista: " + e.getMessage());
        e.printStackTrace();
    }
}

public Revista selectRevista(ConnectionDb connection) {
    Revista revista = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Revista
            revista = mapResultSetToRevistaWithEstante(resultSet);
        } else {
            System.out.println("No Revista found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Revista: " + e.getMessage());
        e.printStackTrace();
    }
    return revista;
}

public List<Revista> selectAllRevistas(ConnectionDb connection) {
    List<Revista> revistas = new ArrayList<>();
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            // Map ResultSet to Revista
            Revista revista = mapResultSetToRevistaWithEstante(resultSet);
            revistas.add(revista);
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while selecting all Revistas: " + e.getMessage());
        e.printStackTrace();
    }
    return revistas;
}

private Revista mapResultSetToRevistaWithEstante(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String codigo = resultSet.getString("CodigoIdentificacion");
        String titulo = resultSet.getString("Titulo");
        Date fecha = resultSet.getDate("FechaPublicacion");
        int stock = resultSet.getInt("Stock");
        String estante = resultSet.getString("NombreEstante");
        int numPaginas = resultSet.getInt("NumeroPaginas");
        String autor = resultSet.getString("NombreAutor");
        int isbn = resultSet.getInt("ISBN");
        String periodicidad = resultSet.getString("Periodicidad");
        String paisCiudad = resultSet.getString("NombrePaisCiudad");
        String notas = resultSet.getString("Notas");
        String editorial = resultSet.getString("NombreEditorial");

        setId(id);
        setCodigoIdentificacion(codigo);
        setTitulo(titulo);
        setFechaPublicacion(fecha);
        setStock(stock);
        setNombreEstante(estante);
        setNumeroPaginas(numPaginas);
        setAutor(autor);
        setIsbn(isbn);
        setPeriodicidad(periodicidad);
        setPaisCiudad(paisCiudad);
        setNotas(notas);
        setEditorial(editorial);

    return new Revista(
            id,codigo,titulo,fecha,stock,estante,numPaginas,autor,isbn,periodicidad,paisCiudad,notas,editorial
    );
}

public String toJson() {
    Map<String, Object> jsonMap = new HashMap<>();
    jsonMap.put("id", getId());
    jsonMap.put("codigoIdentificacion", getCodigoIdentificacion());
    jsonMap.put("titulo", getTitulo());
    jsonMap.put("autor", getAutor());
    jsonMap.put("numeroPaginas", getNumeroPaginas());
    jsonMap.put("isbn", getIsbn());
    jsonMap.put("editorial", getEditorial());
    jsonMap.put("periodicidad", getPeriodicidad());
    jsonMap.put("fechaPublicacion", getFechaPublicacion());
    jsonMap.put("paisCiudad", getPaisCiudad());
    jsonMap.put("notas", getNotas());
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

public Revista selectRevistaByTitulo(ConnectionDb connection) {
    Revista revista = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getTitulo());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Revista
            revista = mapResultSetToRevistaWithEstante(resultSet);
        } else {
            System.out.println("No Revista found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Revista: " + e.getMessage());
        e.printStackTrace();
    }
    return revista;
}


public Revista selectRevistaByAutor(ConnectionDb connection) {
    Revista revista = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getAutor());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Revista
            revista = mapResultSetToRevistaWithEstante(resultSet);
        } else {
            System.out.println("No Revista found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Revista: " + e.getMessage());
        e.printStackTrace();
    }
    return revista;
}

public Revista selectRevistaByEditorial(ConnectionDb connection) {
    Revista revista = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getEditorial());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Revista
            revista = mapResultSetToRevistaWithEstante(resultSet);
        } else {
            System.out.println("No Revista found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Revista: " + e.getMessage());
        e.printStackTrace();
    }
    return revista;
}

public static String listRevistaToJson(List<Revista> revistaList) {
    StringBuilder jsonListString = new StringBuilder("[");
    for (Revista revista : revistaList) {
        jsonListString.append(revista.toJson()).append(",");
    }
    if (!revistaList.isEmpty()) {
        jsonListString.deleteCharAt(jsonListString.length() - 1); // Remove the trailing comma
    }
    jsonListString.append("]");

    return jsonListString.toString();
}

}


