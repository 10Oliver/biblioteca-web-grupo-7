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

public class Ebook extends RecursoDigital {
    private String autor;
    private String editorial;
    private String numeroPaginas;
    private String url;
    private int isbn;
    private String edicion;
    private String lugarPublicacion;
    private String idioma;
    private String notas;

    private String UPDATE_STATEMENT = "UPDATE Ebooks SET Titulo = ?, NumeroPaginas = ?, URL = ?, ISBN = ?, Edicion = ?, FechaPublicacion = ?, Notas = ?, Stock = ?, idEstante = ?, idGenero = ?, idAutor = ?, idEditorial = ?, idLugarPublicacion = ?, idIdioma = ? WHERE CodigoIdentificacion = ?";
    private String INSERT_STATEMENT = "INSERT INTO Ebooks (Titulo, NumeroPaginas, URL, ISBN, Edicion, FechaPublicacion, Notas, Stock, idEstante, idGenero, idAutor, idEditorial, idLugarPublicacion, idIdioma) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String DELETE_STATEMENT =  "DELETE FROM Ebooks WHERE CodigoIdentificacion = ?;";
    private String SELECT_SINGLE_STATEMENT = "SELECT Ebooks.*, Autores.NombreAutor, Generos.NombreGenero, Editoriales.NombreEditorial, Idiomas.NombreIdioma, LugaresPublicacion.NombreLugarPublicacion, Estantes.NombreEstante FROM Ebooks INNER JOIN Autores ON Ebooks.idAutor = Autores.id INNER JOIN Generos ON Ebooks.idGenero = Generos.id INNER JOIN Editoriales ON Ebooks.idEditorial = Editoriales.id INNER JOIN Idiomas ON Ebooks.idIdioma = Idiomas.id INNER JOIN LugaresPublicacion ON Ebooks.idLugarPublicacion = LugaresPublicacion.id INNER JOIN Estantes ON Ebooks.idEstante = Estantes.id WHERE Ebooks.id = ?";
    private String SELECT_ALL_STATEMENT = "SELECT Ebooks.*, Autores.NombreAutor, Generos.NombreGenero, Editoriales.NombreEditorial, Idiomas.NombreIdioma, LugaresPublicacion.NombreLugarPublicacion, Estantes.NombreEstante FROM Ebooks INNER JOIN Autores ON Ebooks.idAutor = Autores.id INNER JOIN Generos ON Ebooks.idGenero = Generos.id INNER JOIN Editoriales ON Ebooks.idEditorial = Editoriales.id INNER JOIN Idiomas ON Ebooks.idIdioma = Idiomas.id INNER JOIN LugaresPublicacion ON Ebooks.idLugarPublicacion = LugaresPublicacion.id INNER JOIN Estantes ON Ebooks.idEstante = Estantes.id";

    public Ebook(int id, String codigoIdentificacion, String titulo, Date fechaPublicacion, int stock, String nombreEstante, String genero, String autor, String editorial, String numeroPaginas, String url, int isbn, String edicion, String lugarPublicacion, String idioma, String notas) {
        super(id, codigoIdentificacion, titulo, fechaPublicacion, stock, nombreEstante, genero);
        this.autor = autor;
        this.editorial = editorial;
        this.numeroPaginas = numeroPaginas;
        this.url = url;
        this.isbn = isbn;
        this.edicion = edicion;
        this.lugarPublicacion = lugarPublicacion;
        this.idioma = idioma;
        this.notas = notas;
    }
    public Ebook(String titulo, Date fechaPublicacion, int stock, String nombreEstante, String genero, String autor, String editorial, String numeroPaginas, String url, int isbn, String edicion, String lugarPublicacion, String idioma, String notas) {
        super(titulo, fechaPublicacion, stock, nombreEstante, genero);
        this.autor = autor;
        this.editorial = editorial;
        this.numeroPaginas = numeroPaginas;
        this.url = url;
        this.isbn = isbn;
        this.edicion = edicion;
        this.lugarPublicacion = lugarPublicacion;
        this.idioma = idioma;
        this.notas = notas;
    }

    public Ebook(String codigoIdentificacion) {
        setCodigoIdentificacion(codigoIdentificacion);
    }

    public Ebook() {
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

    public String getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(String numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
public void insertEbook(ConnectionDb connection) {
    try {
        int index = 1;
// private String INSERT_STATEMENT = "INSERT INTO Ebooks (Titulo, NumeroPaginas, URL, ISBN, Edicion, FechaPublicacion, Notas, Stock, idEstante, idGenero, idAutor, idEditorial, idLugarPublicacion, idIdioma) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.getConnection().prepareStatement(INSERT_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setString(index++, getNumeroPaginas());
        statement.setString(index++, getUrl());
        statement.setInt(index++, getIsbn());
        statement.setString(index++, getEdicion());
        statement.setDate(index++, getFechaPublicacion());
        statement.setString(index++, getNotas());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        statement.setInt(index++, Integer.parseInt(getGenero()));
        statement.setInt(index++, Integer.parseInt(getAutor()));
        statement.setInt(index++, Integer.parseInt(getEditorial()));
        statement.setInt(index++, Integer.parseInt(getLugarPublicacion()));
        statement.setInt(index++, Integer.parseInt(getIdioma()));
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Ebook was inserted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while inserting Ebook: " + e.getMessage());
        e.printStackTrace();
    }
}
public void updateEbook(ConnectionDb connection) {
    int index = 1;
    try {
// private String UPDATE_STATEMENT = "UPDATE Ebooks SET Titulo = ?, NumeroPaginas = ?, URL = ?, ISBN = ?, Edicion = ?, FechaPublicacion = ?, Notas = ?, Stock = ?, idEstante = ?, idGenero = ?, idAutor = ?, idEditorial = ?, idLugarPublicacion = ?, idIdioma = ? WHERE CodigoIdentificacion = ?";
        PreparedStatement statement = connection.getConnection().prepareStatement(UPDATE_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setString(index++, getNumeroPaginas());
        statement.setString(index++, getUrl());
        statement.setInt(index++, getIsbn());
        statement.setString(index++, getEdicion());
        statement.setDate(index++, getFechaPublicacion());
        statement.setString(index++, getNotas());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        statement.setInt(index++, Integer.parseInt(getGenero()));
        statement.setInt(index++, Integer.parseInt(getAutor()));
        statement.setInt(index++, Integer.parseInt(getEditorial()));
        statement.setInt(index++, Integer.parseInt(getLugarPublicacion()));
        statement.setInt(index++, Integer.parseInt(getIdioma()));
        statement.setString(index++, getCodigoIdentificacion());
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Ebook was updated successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while updating Ebook: " + e.getMessage());
        e.printStackTrace();
    }
}
public void deleteEbook(ConnectionDb connection) {
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(DELETE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Ebook was deleted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while deleting Ebook: " + e.getMessage());
        e.printStackTrace();
    }
}

public Ebook selectEbook(ConnectionDb connection) {
    Ebook ebook = null;
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
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            setGenero(resultSet.getString("NombreGenero"));
            setAutor(resultSet.getString("NombreAutor"));
            setEditorial(resultSet.getString("NombreEditorial"));
            setNumeroPaginas(resultSet.getString("NumeroPaginas"));
            setUrl(resultSet.getString("URL"));
            setIsbn(resultSet.getInt("ISBN"));
            setEdicion(resultSet.getString("Edicion"));
            setLugarPublicacion(resultSet.getString("NombreLugarPublicacion"));
            setIdioma(resultSet.getString("NombreIdioma"));
            setNotas(resultSet.getString("Notas"));
            ebook = new Ebook(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getGenero(),getAutor(),getEditorial(),getNumeroPaginas(),getUrl(),getIsbn(),getEdicion(),getLugarPublicacion(),getIdioma(),getNotas());
        } else {
            System.out.println("No Ebook found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Ebook: " + e.getMessage());
        e.printStackTrace();
    }
    return ebook;
}

public List<Ebook> selectAllEbooks(ConnectionDb connection) {
    List<Ebook> ebooks = new ArrayList<>();
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            // // Map ResultSet to Ebook
            // Ebook ebook = mapResultSetToEbook(resultSet);
            Ebook ebook = null;
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("idEstante"));
            setGenero(resultSet.getString("NombreGenero"));
            setAutor(resultSet.getString("NombreAutor"));
            setEditorial(resultSet.getString("NombreEditorial"));
            setNumeroPaginas(resultSet.getString("NumeroPaginas"));
            setUrl(resultSet.getString("URL"));
            setIsbn(resultSet.getInt("ISBN"));
            setEdicion(resultSet.getString("Edicion"));
            setLugarPublicacion(resultSet.getString("NombreLugarPublicacion"));
            setIdioma(resultSet.getString("NombreIdioma"));
            setNotas(resultSet.getString("Notas"));
            ebook = new Ebook(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getGenero(),getAutor(),getEditorial(),getNumeroPaginas(),getUrl(),getIsbn(),getEdicion(),getLugarPublicacion(),getIdioma(),getNotas());
            ebooks.add(ebook);
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while selecting all Ebooks: " + e.getMessage());
        e.printStackTrace();
    }
    return ebooks;
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
    jsonMap.put("editorial", getEditorial());
    jsonMap.put("numeroPaginas", getNumeroPaginas());
    jsonMap.put("url", getUrl());
    jsonMap.put("isbn", getIsbn());
    jsonMap.put("edicion", getEdicion());
    jsonMap.put("lugarPublicacion", getLugarPublicacion());
    jsonMap.put("idioma", getIdioma());
    jsonMap.put("notas", getNotas());

    StringBuilder jsonString = new StringBuilder("{");
    for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
        jsonString.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\",");
    }
    jsonString.deleteCharAt(jsonString.length() - 1); // Remove the trailing comma
    jsonString.append("}");

    return jsonString.toString();
}

public static String listEbooksToJson(List<Ebook> ebookList) {
        StringBuilder jsonListString = new StringBuilder("[");
        for (Ebook ebook : ebookList) {
            jsonListString.append(ebook.toJson()).append(",");
        }
        if (!ebookList.isEmpty()) {
            jsonListString.deleteCharAt(jsonListString.length() - 1); // Remove the trailing comma
        }
        jsonListString.append("]");

        return jsonListString.toString();
    }
public Ebook selectEbookByTitulo(ConnectionDb connection) {
    Ebook ebook = null;
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
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            setGenero(resultSet.getString("Genero"));
            setAutor(resultSet.getString("Autor"));
            setEditorial(resultSet.getString("Editorial"));
            setNumeroPaginas(resultSet.getString("NumeroPaginas"));
            setUrl(resultSet.getString("URL"));
            setIsbn(resultSet.getInt("ISBN"));
            setEdicion(resultSet.getString("Edicion"));
            setLugarPublicacion(resultSet.getString("LugarPublicacion"));
            setIdioma(resultSet.getString("Idioma"));
            setNotas(resultSet.getString("Notas"));
            ebook = new Ebook(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getGenero(),getAutor(),getEditorial(),getNumeroPaginas(),getUrl(),getIsbn(),getEdicion(),getLugarPublicacion(),getIdioma(),getNotas());
        } else {
            System.out.println("No Ebook found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Ebook: " + e.getMessage());
        e.printStackTrace();
    }
    return ebook;
}


public Ebook selectEbookByGenero(ConnectionDb connection) {
    Ebook ebook = null;
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
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            setGenero(resultSet.getString("Genero"));
            setAutor(resultSet.getString("Autor"));
            setEditorial(resultSet.getString("Editorial"));
            setNumeroPaginas(resultSet.getString("NumeroPaginas"));
            setUrl(resultSet.getString("URL"));
            setIsbn(resultSet.getInt("ISBN"));
            setEdicion(resultSet.getString("Edicion"));
            setLugarPublicacion(resultSet.getString("LugarPublicacion"));
            setIdioma(resultSet.getString("Idioma"));
            setNotas(resultSet.getString("Notas"));
            ebook = new Ebook(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getGenero(),getAutor(),getEditorial(),getNumeroPaginas(),getUrl(),getIsbn(),getEdicion(),getLugarPublicacion(),getIdioma(),getNotas());
        } else {
            System.out.println("No Ebook found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Ebook: " + e.getMessage());
        e.printStackTrace();
    }
    return ebook;
}


public Ebook selectEbookByAutor(ConnectionDb connection) {
    Ebook ebook = null;
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
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            setGenero(resultSet.getString("Genero"));
            setAutor(resultSet.getString("Autor"));
            setEditorial(resultSet.getString("Editorial"));
            setNumeroPaginas(resultSet.getString("NumeroPaginas"));
            setUrl(resultSet.getString("URL"));
            setIsbn(resultSet.getInt("ISBN"));
            setEdicion(resultSet.getString("Edicion"));
            setLugarPublicacion(resultSet.getString("LugarPublicacion"));
            setIdioma(resultSet.getString("Idioma"));
            setNotas(resultSet.getString("Notas"));
            ebook = new Ebook(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getGenero(),getAutor(),getEditorial(),getNumeroPaginas(),getUrl(),getIsbn(),getEdicion(),getLugarPublicacion(),getIdioma(),getNotas());
        } else {
            System.out.println("No Ebook found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Ebook: " + e.getMessage());
        e.printStackTrace();
    }
    return ebook;
}

public Ebook selectEbookByEditorial(ConnectionDb connection) {
    Ebook ebook = null;
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
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            setGenero(resultSet.getString("Genero"));
            setAutor(resultSet.getString("Autor"));
            setEditorial(resultSet.getString("Editorial"));
            setNumeroPaginas(resultSet.getString("NumeroPaginas"));
            setUrl(resultSet.getString("URL"));
            setIsbn(resultSet.getInt("ISBN"));
            setEdicion(resultSet.getString("Edicion"));
            setLugarPublicacion(resultSet.getString("LugarPublicacion"));
            setIdioma(resultSet.getString("Idioma"));
            setNotas(resultSet.getString("Notas"));
            ebook = new Ebook(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getGenero(),getAutor(),getEditorial(),getNumeroPaginas(),getUrl(),getIsbn(),getEdicion(),getLugarPublicacion(),getIdioma(),getNotas());
        } else {
            System.out.println("No Ebook found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Ebook: " + e.getMessage());
        e.printStackTrace();
    }
    return ebook;
}

public Ebook selectEbookByIdioma(ConnectionDb connection) {
    Ebook ebook = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getIdioma());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Ebook
            // Ebook ebook = null;
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            setGenero(resultSet.getString("Genero"));
            setAutor(resultSet.getString("Autor"));
            setEditorial(resultSet.getString("Editorial"));
            setNumeroPaginas(resultSet.getString("NumeroPaginas"));
            setUrl(resultSet.getString("URL"));
            setIsbn(resultSet.getInt("ISBN"));
            setEdicion(resultSet.getString("Edicion"));
            setLugarPublicacion(resultSet.getString("LugarPublicacion"));
            setIdioma(resultSet.getString("Idioma"));
            setNotas(resultSet.getString("Notas"));
            ebook = new Ebook(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getGenero(),getAutor(),getEditorial(),getNumeroPaginas(),getUrl(),getIsbn(),getEdicion(),getLugarPublicacion(),getIdioma(),getNotas());
        } else {
            System.out.println("No Ebook found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Ebook: " + e.getMessage());
        e.printStackTrace();
    }
    return ebook;
}


}
