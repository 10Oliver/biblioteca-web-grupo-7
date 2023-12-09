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


public class Pelicula extends RecursoDigital {
    private String director;
    private String duracion;
    private String productor;
    private String paisCiudad;
    private String UPDATE_STATEMENT = "UPDATE Peliculas SET Titulo = ?, Duracion = ?, FechaPublicacion = ?, Stock = ?, idEstante = ?, idDirector = ?, idGenero = ?, idProductor = ?, idPaisCiudad = ? WHERE CodigoIdentificacion = ?";
    private String INSERT_STATEMENT = "INSERT INTO Peliculas (Titulo, Duracion, FechaPublicacion, Stock, idEstante, idDirector, idGenero, idProductor, idPaisCiudad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String DELETE_STATEMENT = "DELETE FROM Peliculas WHERE CodigoIdentificacion = ?;";
    private String SELECT_SINGLE_STATEMENT = "SELECT Peliculas.*, Directores.NombreDirector, Generos.NombreGenero, Productores.NombreProductor, PaisCiudad.NombrePaisCiudad, Estantes.NombreEstante FROM Peliculas INNER JOIN Directores ON Peliculas.idDirector = Directores.id INNER JOIN Generos ON Peliculas.idGenero = Generos.id INNER JOIN Productores ON Peliculas.idProductor = Productores.id INNER JOIN PaisCiudad ON Peliculas.idPaisCiudad = PaisCiudad.id INNER JOIN Estantes ON Peliculas.idEstante = Estantes.id WHERE Peliculas.id = ?";
    private String SELECT_ALL_STATEMENT = "SELECT Peliculas.*, Directores.NombreDirector, Generos.NombreGenero, Productores.NombreProductor, PaisCiudad.NombrePaisCiudad, Estantes.NombreEstante FROM Peliculas INNER JOIN Directores ON Peliculas.idDirector = Directores.id INNER JOIN Generos ON Peliculas.idGenero = Generos.id INNER JOIN Productores ON Peliculas.idProductor = Productores.id INNER JOIN PaisCiudad ON Peliculas.idPaisCiudad = PaisCiudad.id INNER JOIN Estantes ON Peliculas.idEstante = Estantes.id";

    public Pelicula(int id, String codigoIdentificacion, String titulo, Date fechaPublicacion, int stock, String nombreEstante, String genero, String director, String duracion, String productor, String paisCiudad) {
        super(id, codigoIdentificacion, titulo, fechaPublicacion, stock, nombreEstante, genero);
        this.director = director;
        this.duracion = duracion;
        this.productor = productor;
        this.paisCiudad = paisCiudad;
    }
    public Pelicula(String titulo, Date fechaPublicacion, int stock, String nombreEstante, String genero, String director, String duracion, String productor, String paisCiudad) {
        super(titulo, fechaPublicacion, stock, nombreEstante, genero);
        this.director = director;
        this.duracion = duracion;
        this.productor = productor;
        this.paisCiudad = paisCiudad;
    }

    public Pelicula(String codigoIdentificacion) {
        setCodigoIdentificacion(codigoIdentificacion);
    }

    public Pelicula() {
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public String getPaisCiudad() {
        return paisCiudad;
    }

    public void setPaisCiudad(String paisCiudad) {
        this.paisCiudad = paisCiudad;
    }

public void insertPelicula(ConnectionDb connection) {
    try {
        int index = 1;
// private String INSERT_STATEMENT = "INSERT INTO Peliculas (Titulo, Duracion, FechaPublicacion, Stock, idEstante, idDirector, idGenero, idProductor, idPaisCiudad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.getConnection().prepareStatement(INSERT_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setString(index++, getDuracion());
        statement.setDate(index++, getFechaPublicacion());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        statement.setInt(index++, Integer.parseInt(getDirector()));
        statement.setInt(index++, Integer.parseInt(getGenero()));
        statement.setInt(index++, Integer.parseInt(getProductor()));
        statement.setInt(index++, Integer.parseInt(getPaisCiudad()));
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Pelicula was inserted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while inserting Pelicula: " + e.getMessage());
        e.printStackTrace();
    }
}

public void updatePelicula(ConnectionDb connection) {
    try {
// private String UPDATE_STATEMENT = "UPDATE Peliculas SET Titulo = ?, Duracion = ?, FechaPublicacion = ?, Stock = ?, idEstante = ?, idDirector = ?, idGenero = ?, idProductor = ?, idPaisCiudad = ? WHERE CodigoIdentificacion = ?";
        int index = 1;
        PreparedStatement statement = connection.getConnection().prepareStatement(UPDATE_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setString(index++, getDuracion());
        statement.setDate(index++, getFechaPublicacion());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        statement.setInt(index++, Integer.parseInt(getDirector()));
        statement.setInt(index++, Integer.parseInt(getGenero()));
        statement.setInt(index++, Integer.parseInt(getProductor()));
        statement.setInt(index++, Integer.parseInt(getPaisCiudad()));
        statement.setString(index++, getCodigoIdentificacion());
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Pelicula was updated successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while updating Pelicula: " + e.getMessage());
        e.printStackTrace();
    }
}

public void deletePelicula(ConnectionDb connection) {
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(DELETE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Pelicula was deleted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while deleting Pelicula: " + e.getMessage());
        e.printStackTrace();
    }
}

public Pelicula selectPelicula(ConnectionDb connection) {
    Pelicula pelicula = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Pelicula
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setDirector(resultSet.getString("NombreDirector"));
            setDuracion(resultSet.getString("Duracion"));
            setGenero(resultSet.getString("NombreGenero"));
            setProductor(resultSet.getString("NombreProductor"));
            setPaisCiudad(resultSet.getString("NombrePaisCiudad"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            pelicula = new Pelicula(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getGenero(),getDirector(),getDuracion(),getProductor(),getPaisCiudad());
        } else {
            System.out.println("No Pelicula found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Pelicula: " + e.getMessage());
        e.printStackTrace();
    }
    return pelicula;
}

public List<Pelicula> selectAllPeliculas(ConnectionDb connection) {
    List<Pelicula> peliculas = new ArrayList<>();
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setDirector(resultSet.getString("NombreDirector"));
            setDuracion(resultSet.getString("Duracion"));
            setGenero(resultSet.getString("NombreGenero"));
            setProductor(resultSet.getString("NombreProductor"));
            setPaisCiudad(resultSet.getString("NombrePaisCiudad"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            Pelicula pelicula = new Pelicula(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getGenero(),getDirector(),getDuracion(),getProductor(),getPaisCiudad());
            peliculas.add(pelicula);
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while selecting all Peliculas: " + e.getMessage());
        e.printStackTrace();
    }
    return peliculas;
}


public String toJson() {
    Map<String, Object> jsonMap = new HashMap<>();
    jsonMap.put("id", getId());
    jsonMap.put("codigoIdentificacion", getCodigoIdentificacion());
    jsonMap.put("titulo", getTitulo());
    jsonMap.put("director", getDirector());
    jsonMap.put("duracion", getDuracion());
    jsonMap.put("genero", getGenero());
    jsonMap.put("productor", getProductor());
    jsonMap.put("paisCiudad", getPaisCiudad());
    jsonMap.put("fechaPublicacion", getFechaPublicacion());
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

public static String listPeliculasToJson(List<Pelicula> peliculaList) {
    StringBuilder jsonListString = new StringBuilder("[");
    for (Pelicula pelicula : peliculaList) {
        jsonListString.append(pelicula.toJson()).append(",");
    }
    if (!peliculaList.isEmpty()) {
        jsonListString.deleteCharAt(jsonListString.length() - 1); // Remove the trailing comma
    }
    jsonListString.append("]");

    return jsonListString.toString();
}
public Pelicula selectPeliculaByTitulo(ConnectionDb connection) {
    Pelicula pelicula = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getTitulo());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Pelicula
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setDirector(resultSet.getString("Director"));
            setDuracion(resultSet.getString("Duracion"));
            setGenero(resultSet.getString("Tipo"));
            setProductor(resultSet.getString("Productor"));
            setPaisCiudad(resultSet.getString("PaisCiudad"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            pelicula = new Pelicula(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getGenero(),getDirector(),getDuracion(),getProductor(),getPaisCiudad());
        } else {
            System.out.println("No Pelicula found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Pelicula: " + e.getMessage());
        e.printStackTrace();
    }
    return pelicula;
}

public Pelicula selectPeliculaByDirector(ConnectionDb connection) {
    Pelicula pelicula = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getDirector());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Pelicula
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setDirector(resultSet.getString("Director"));
            setDuracion(resultSet.getString("Duracion"));
            setGenero(resultSet.getString("Tipo"));
            setProductor(resultSet.getString("Productor"));
            setPaisCiudad(resultSet.getString("PaisCiudad"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            pelicula = new Pelicula(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getGenero(),getDirector(),getDuracion(),getProductor(),getPaisCiudad());
        } else {
            System.out.println("No Pelicula found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Pelicula: " + e.getMessage());
        e.printStackTrace();
    }
    return pelicula;
}

public Pelicula selectPeliculaByGenero(ConnectionDb connection) {
    Pelicula pelicula = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getGenero());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Pelicula
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setDirector(resultSet.getString("Director"));
            setDuracion(resultSet.getString("Duracion"));
            setGenero(resultSet.getString("Genero"));
            setProductor(resultSet.getString("Productor"));
            setPaisCiudad(resultSet.getString("PaisCiudad"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            pelicula = new Pelicula(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getGenero(),getDirector(),getDuracion(),getProductor(),getPaisCiudad());
        } else {
            System.out.println("No Pelicula found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Pelicula: " + e.getMessage());
        e.printStackTrace();
    }
    return pelicula;
}

public Pelicula selectPeliculaByProductor(ConnectionDb connection) {
    Pelicula pelicula = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getProductor());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Pelicula
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setDirector(resultSet.getString("Director"));
            setDuracion(resultSet.getString("Duracion"));
            setGenero(resultSet.getString("Tipo"));
            setProductor(resultSet.getString("Productor"));
            setPaisCiudad(resultSet.getString("PaisCiudad"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            pelicula = new Pelicula(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getGenero(),getDirector(),getDuracion(),getProductor(),getPaisCiudad());
        } else {
            System.out.println("No Pelicula found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Pelicula: " + e.getMessage());
        e.printStackTrace();
    }
    return pelicula;
}



}
