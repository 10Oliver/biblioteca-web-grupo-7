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

public class Libro extends RecursoFisico {
    private String autor;
    private String editorial;
    private int isbn;
    private String edicion;
    private String lugarPublicacion;
    private String genero;
    private String idioma;
    private String notas;

    private String UPDATE_STATEMENT = "UPDATE Libros SET Titulo = ?, NumeroPaginas = ?, ISBN = ?, Edicion = ?, FechaPublicacion = ?, Notas = ?, Stock = ?, idEstante = ?, idAutor = ?, idEditorial = ?, idLugarPublicacion = ?, idGenero = ?, idIdioma = ? WHERE CodigoIdentificacion = ?";
    private String INSERT_STATEMENT = "INSERT INTO Libros (Titulo, NumeroPaginas, ISBN, Edicion, FechaPublicacion, Notas, Stock, idEstante, idAutor, idEditorial, idLugarPublicacion, idGenero, idIdioma) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private String DELETE_STATEMENT = "DELETE FROM Libros WHERE CodigoIdentificacion = ?;";
    private String SELECT_SINGLE_STATEMENT = "SELECT Libros.*, Autores.NombreAutor, Generos.NombreGenero, Editoriales.NombreEditorial, Idiomas.NombreIdioma, LugaresPublicacion.NombreLugarPublicacion, Estantes.NombreEstante FROM Libros INNER JOIN Autores ON Libros.idAutor = Autores.id INNER JOIN Generos ON Libros.idGenero = Generos.id INNER JOIN Editoriales ON Libros.idEditorial = Editoriales.id INNER JOIN Idiomas ON Libros.idIdioma = Idiomas.id INNER JOIN LugaresPublicacion ON Libros.idLugarPublicacion = LugaresPublicacion.id INNER JOIN Estantes ON Libros.idEstante = Estantes.id WHERE Libros.id = ?";
    private String SELECT_ALL_STATEMENT = "SELECT Libros.*, Autores.NombreAutor, Generos.NombreGenero, Editoriales.NombreEditorial, Idiomas.NombreIdioma, LugaresPublicacion.NombreLugarPublicacion, Estantes.NombreEstante FROM Libros INNER JOIN Autores ON Libros.idAutor = Autores.id INNER JOIN Generos ON Libros.idGenero = Generos.id INNER JOIN Editoriales ON Libros.idEditorial = Editoriales.id INNER JOIN Idiomas ON Libros.idIdioma = Idiomas.id INNER JOIN LugaresPublicacion ON Libros.idLugarPublicacion = LugaresPublicacion.id INNER JOIN Estantes ON Libros.idEstante = Estantes.id";

    public Libro(int id, String codigoIdentificacion, String titulo, Date fechaPublicacion, int stock, String nombreEstante, int numeroPaginas, String autor, String editorial, int isbn, String edicion, String lugarPublicacion, String genero, String idioma, String notas) {
        super(id, codigoIdentificacion, titulo, fechaPublicacion, stock, nombreEstante, numeroPaginas);
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
        this.edicion = edicion;
        this.lugarPublicacion = lugarPublicacion;
        this.genero = genero;
        this.idioma = idioma;
        this.notas = notas;
    }
    public Libro(String titulo, Date fechaPublicacion, int stock, String nombreEstante, int numeroPaginas, String autor, String editorial, int isbn, String edicion, String lugarPublicacion, String genero, String idioma, String notas) {
        super(titulo, fechaPublicacion, stock, nombreEstante, numeroPaginas);
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
        this.edicion = edicion;
        this.lugarPublicacion = lugarPublicacion;
        this.genero = genero;
        this.idioma = idioma;
        this.notas = notas;
    }

    public Libro(String codigoIdentificacion) {
        setCodigoIdentificacion(codigoIdentificacion);
    }

    public Libro() {
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getLugarPublicacion() {
        return lugarPublicacion;
    }

    public void setLugarPublicacion(String lugarPublicacion) {
        this.lugarPublicacion = lugarPublicacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

public Libro selectLibro(ConnectionDb connection) {
    Libro libro = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Ebook
            // Ebook ebook = null;
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setAutor(resultSet.getString("NombreAutor"));
            setEditorial(resultSet.getString("NombreEditorial"));
            setNumeroPaginas(resultSet.getInt("NumeroPaginas"));
            setIsbn(resultSet.getInt("ISBN"));
            setEdicion(resultSet.getString("Edicion"));
            setLugarPublicacion(resultSet.getString("NombreLugarPublicacion"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setGenero(resultSet.getString("NombreGenero"));
            setIdioma(resultSet.getString("NombreIdioma"));
            setNotas(resultSet.getString("Notas"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            libro = new Libro(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getNumeroPaginas(),getAutor(),getEditorial(),getIsbn(),getEdicion(),getLugarPublicacion(),getGenero(),getIdioma(),getNotas());
        } else {
            System.out.println("No Libro found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Libro: " + e.getMessage());
        e.printStackTrace();
    }
    return libro;
}

public List<Libro> selectAllLibros(ConnectionDb connection) {
    List<Libro> libros = new ArrayList<Libro>();
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            // Map ResultSet to Ebook
            // Ebook ebook = null;
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setAutor(resultSet.getString("NombreAutor"));
            setEditorial(resultSet.getString("NombreEditorial"));
            setNumeroPaginas(resultSet.getInt("NumeroPaginas"));
            setIsbn(resultSet.getInt("ISBN"));
            setEdicion(resultSet.getString("Edicion"));
            setLugarPublicacion(resultSet.getString("NombreLugarPublicacion"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setGenero(resultSet.getString("NombreGenero"));
            setIdioma(resultSet.getString("NombreIdioma"));
            setNotas(resultSet.getString("Notas"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            Libro libro = new Libro(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getNumeroPaginas(),getAutor(),getEditorial(),getIsbn(),getEdicion(),getLugarPublicacion(),getGenero(),getIdioma(),getNotas());
            libros.add(libro);
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Libro: " + e.getMessage());
        e.printStackTrace();
    }
    return libros;
}

public void insertLibro(ConnectionDb connection) {
    int index = 1;
    try {
// private String INSERT_STATEMENT = "INSERT INTO Libros (Titulo, NumeroPaginas, ISBN, Edicion, FechaPublicacion, Notas, Stock, idEstante, idAutor, idEditorial, idLugarPublicacion, idGenero, idIdioma) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = connection.getConnection().prepareStatement(INSERT_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setInt(index++, getNumeroPaginas());
        statement.setInt(index++, getIsbn());
        statement.setString(index++, getEdicion());
        statement.setDate(index++, getFechaPublicacion());
        statement.setString(index++, getNotas());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        statement.setInt(index++, Integer.parseInt(getAutor()));
        statement.setInt(index++, Integer.parseInt(getEditorial()));
        statement.setInt(index++, Integer.parseInt(getLugarPublicacion()));
        statement.setInt(index++, Integer.parseInt(getGenero()));
        statement.setInt(index++, Integer.parseInt(getIdioma()));
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Libro was inserted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while inserting Libro: " + e.getMessage());
        e.printStackTrace();
    }
}

public void updateLibro(ConnectionDb connection) {
    int index = 1;
    try {
// private String UPDATE_STATEMENT = "UPDATE Libros SET Titulo = ?, NumeroPaginas = ?, ISBN = ?, Edicion = ?, FechaPublicacion = ?, Notas = ?, Stock = ?, idEstante = ?, idAutor = ?, idEditorial = ?, idLugarPublicacion = ?, idGenero = ?, idIdioma = ? WHERE CodigoIdentificacion = ?";
        PreparedStatement statement = connection.getConnection().prepareStatement(UPDATE_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setInt(index++, getNumeroPaginas());
        statement.setInt(index++, getIsbn());
        statement.setString(index++, getEdicion());
        statement.setDate(index++, getFechaPublicacion());
        statement.setString(index++, getNotas());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        statement.setInt(index++, Integer.parseInt(getAutor()));
        statement.setInt(index++, Integer.parseInt(getEditorial()));
        statement.setInt(index++, Integer.parseInt(getLugarPublicacion()));
        statement.setInt(index++, Integer.parseInt(getGenero()));
        statement.setInt(index++, Integer.parseInt(getIdioma()));
        statement.setString(index++, getCodigoIdentificacion());
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Libro was updated successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while updating Libro: " + e.getMessage());
        e.printStackTrace();
    }
}

public void deleteLibro(ConnectionDb connection) {
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(DELETE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Libro was deleted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while deleting Libro: " + e.getMessage());
        e.printStackTrace();
    }
}

public String toJson() {
    Map<String, Object> jsonMap = new HashMap<>();
    jsonMap.put("id", getId());
    jsonMap.put("codigoIdentificacion", getCodigoIdentificacion());
    jsonMap.put("titulo", getTitulo());
    jsonMap.put("autor", getAutor());
    jsonMap.put("editorial", getEditorial());
    jsonMap.put("isbn", getIsbn());
    jsonMap.put("edicion", getEdicion());
    jsonMap.put("lugarPublicacion", getLugarPublicacion());
    jsonMap.put("genero", getGenero());
    jsonMap.put("idioma", getIdioma());
    jsonMap.put("notas", getNotas());
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

public Libro selectLibroByTitulo(ConnectionDb connection) {
    Libro libro = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getTitulo());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Ebook
            // Ebook ebook = null;
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setAutor(resultSet.getString("Autor"));
            setEditorial(resultSet.getString("Editorial"));
            setNumeroPaginas(resultSet.getInt("NumeroPaginas"));
            setIsbn(resultSet.getInt("ISBN"));
            setEdicion(resultSet.getString("Edicion"));
            setLugarPublicacion(resultSet.getString("LugarPublicacion"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setGenero(resultSet.getString("Genero"));
            setIdioma(resultSet.getString("Idioma"));
            setNotas(resultSet.getString("Notas"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            libro = new Libro(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getNumeroPaginas(),getAutor(),getEditorial(),getIsbn(),getEdicion(),getLugarPublicacion(),getGenero(),getIdioma(),getNotas());
        } else {
            System.out.println("No Libro found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Libro: " + e.getMessage());
        e.printStackTrace();
    }
    return libro;
}

public Libro selectLibroByAutor(ConnectionDb connection) {
    Libro libro = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getAutor());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Ebook
            // Ebook ebook = null;
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setAutor(resultSet.getString("Autor"));
            setEditorial(resultSet.getString("Editorial"));
            setNumeroPaginas(resultSet.getInt("NumeroPaginas"));
            setIsbn(resultSet.getInt("ISBN"));
            setEdicion(resultSet.getString("Edicion"));
            setLugarPublicacion(resultSet.getString("LugarPublicacion"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setGenero(resultSet.getString("Genero"));
            setIdioma(resultSet.getString("Idioma"));
            setNotas(resultSet.getString("Notas"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            libro = new Libro(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getNumeroPaginas(),getAutor(),getEditorial(),getIsbn(),getEdicion(),getLugarPublicacion(),getGenero(),getIdioma(),getNotas());
        } else {
            System.out.println("No Libro found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Libro: " + e.getMessage());
        e.printStackTrace();
    }
    return libro;
}

public Libro selectLibroByGenero(ConnectionDb connection) {
    Libro libro = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getGenero());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Ebook
            // Ebook ebook = null;
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setAutor(resultSet.getString("Autor"));
            setEditorial(resultSet.getString("Editorial"));
            setNumeroPaginas(resultSet.getInt("NumeroPaginas"));
            setIsbn(resultSet.getInt("ISBN"));
            setEdicion(resultSet.getString("Edicion"));
            setLugarPublicacion(resultSet.getString("LugarPublicacion"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setGenero(resultSet.getString("Genero"));
            setIdioma(resultSet.getString("Idioma"));
            setNotas(resultSet.getString("Notas"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            libro = new Libro(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getNumeroPaginas(),getAutor(),getEditorial(),getIsbn(),getEdicion(),getLugarPublicacion(),getGenero(),getIdioma(),getNotas());
        } else {
            System.out.println("No Libro found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Libro: " + e.getMessage());
        e.printStackTrace();
    }
    return libro;
}

public Libro selectLibroByEditorial(ConnectionDb connection) {
    Libro libro = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getEditorial());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Ebook
            // Ebook ebook = null;
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setAutor(resultSet.getString("Autor"));
            setEditorial(resultSet.getString("Editorial"));
            setNumeroPaginas(resultSet.getInt("NumeroPaginas"));
            setIsbn(resultSet.getInt("ISBN"));
            setEdicion(resultSet.getString("Edicion"));
            setLugarPublicacion(resultSet.getString("LugarPublicacion"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setGenero(resultSet.getString("Genero"));
            setIdioma(resultSet.getString("Idioma"));
            setNotas(resultSet.getString("Notas"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            libro = new Libro(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getNumeroPaginas(),getAutor(),getEditorial(),getIsbn(),getEdicion(),getLugarPublicacion(),getGenero(),getIdioma(),getNotas());
        } else {
            System.out.println("No Libro found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Libro: " + e.getMessage());
        e.printStackTrace();
    }
    return libro;
}

public static String listLibrosToJson(List<Libro> libroList) {
    StringBuilder jsonListString = new StringBuilder("[");
    for (Libro libro : libroList) {
        jsonListString.append(libro.toJson()).append(",");
    }
    if (!libroList.isEmpty()) {
        jsonListString.deleteCharAt(jsonListString.length() - 1); // Remove the trailing comma
    }
    jsonListString.append("]");

    return jsonListString.toString();
}




}
