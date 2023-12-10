package sv.edu.udb.www.Recursos.Controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.udb.www.Recursos.Conexion.ConnectionDb;
import sv.edu.udb.www.Recursos.Models.StatusResponseIntern;
import sv.edu.udb.www.Recursos.Models.RecursosDigitales.Pelicula;

@WebServlet("/PeliculaController")
public class PeliculaController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logic to retrieve a specific Pelicula by its code
        String busqueda = request.getParameter("busqueda");
        String action = request.getParameter("action");
        String jsonResponse = null;
            try(ConnectionDb connection = new ConnectionDb()) {
                if(action != null){

                    switch (action) {
                        case "codigo":
                            jsonResponse = ByCode(connection, busqueda);
                            response.setContentType("application/json");
                            response.getWriter().write(jsonResponse);
                            break;
                        case "titulo":
                            jsonResponse = ByTitulo(connection, busqueda);
                            response.setContentType("application/json");
                            response.getWriter().write(jsonResponse);
                            break;
                        case "genero":
                            jsonResponse = ByGenero(connection, busqueda);
                            response.setContentType("application/json");
                            response.getWriter().write(jsonResponse);
                            break;
                        case "director":
                            jsonResponse = ByDirector(connection, busqueda);
                            response.setContentType("application/json");
                            response.getWriter().write(jsonResponse);
                            break;
                        case "productor":
                            jsonResponse = ByProductor(connection, busqueda);
                            response.setContentType("application/json");
                            response.getWriter().write(jsonResponse);
                            break;

                        default:
                        response.setContentType("application/json");

                        jsonResponse = GetAll(connection);
                        response.getWriter().write(jsonResponse);
                            break;
                }
                }else{
                    response.setContentType("application/json");

                        jsonResponse = GetAll(connection);
                        response.getWriter().write(jsonResponse);
                }
            } catch (SQLException e) {
                // Log and handle the error
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving Pelicula details");
            }
        }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logic to insert a new Pelicula
        try(ConnectionDb connection = new ConnectionDb()) {
            // Extract Pelicula details from the request
            Pelicula newPelicula = extractPeliculaFromRequest(request);

            // Insert the new Pelicula into the database
            newPelicula.insertPelicula(connection);
            StatusResponseIntern message = new StatusResponseIntern("Successfully Created",200);
            String jsonResponse = message.StatusCode();
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse);
        } catch (SQLException e) {
            // Log and handle the error

            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error inserting new Pelicula");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
// Logic to update an existing Pelicula
try(ConnectionDb connection = new ConnectionDb()) {
    // Extract Pelicula details from the request
    String code = request.getParameter("code");
    if(code != null)
    {
        Pelicula updatedPelicula = extractPeliculaFromRequest(request);
        updatedPelicula.setCodigoIdentificacion(code);
        // Update the existing Pelicula in the database
        updatedPelicula.updatePelicula(connection);

        StatusResponseIntern res = new StatusResponseIntern("pelicula was successfully updated",200);
        response.getWriter().write(res.StatusCode());

    }
} catch (SQLException e) {
    // Log and handle the error

    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating Pelicula");
}
}
protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logic to delete an existing Pelicula
        String code = request.getParameter("code");
        try(ConnectionDb connection = new ConnectionDb()) {
            // Extract Pelicula details from the request
            Pelicula deletedPelicula = new Pelicula(code);

            // Delete the existing Pelicula from the database
            deletedPelicula.deletePelicula(connection);

            StatusResponseIntern res = new StatusResponseIntern("pelicula was successfully registered",200);
            response.getWriter().write(res.StatusCode());
        } catch (SQLException e) {
            // Log and handle the error

            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting Pelicula");
        }
    }




    private Pelicula extractPeliculaFromRequest(HttpServletRequest request) {
    // Extract and set Pelicula details from request parameters
    String titulo = request.getParameter("titulo");
    String director = request.getParameter("director");
    String duracion = request.getParameter("duracion");
    String genero = request.getParameter("genero");
    String productor = request.getParameter("productor");
    String paisCiudad = request.getParameter("paisCiudad");
    Date fechaPublicacion = Date.valueOf(request.getParameter("fechaPublicacion"));
    int stock = Integer.parseInt(request.getParameter("stock"));
    String nombreEstante = request.getParameter("nombreEstante");

    // Create and return a new Pelicula object
    return new Pelicula(titulo, fechaPublicacion, stock, nombreEstante, genero, director, duracion, productor, paisCiudad);
}

private String ByTitulo(ConnectionDb connection,String busqueda){
    Pelicula pl = new Pelicula();
    pl.setTitulo(busqueda);
    pl.selectPeliculaByTitulo(connection);

    return pl.toJson();
}
private String ByDirector(ConnectionDb connection,String busqueda){
    Pelicula pl = new Pelicula();
    pl.setDirector(busqueda);
    pl.selectPeliculaByDirector(connection);

    return pl.toJson();
}
private String ByGenero(ConnectionDb connection,String busqueda){
    Pelicula pl = new Pelicula();
    pl.setGenero(busqueda);
    pl.selectPeliculaByGenero(connection);

    return pl.toJson();
}
private String ByProductor(ConnectionDb connection,String busqueda){
    Pelicula pl = new Pelicula();
    pl.setProductor(busqueda);
    pl.selectPeliculaByProductor(connection);

    return pl.toJson();
}
private String ByCode(ConnectionDb connection,String busqueda){
    Pelicula pl = new Pelicula(busqueda);
    pl.selectPelicula(connection);

    return pl.toJson();
}

private String GetAll(ConnectionDb connection){
    Pelicula pl = new Pelicula();
    List<Pelicula> pls = pl.selectAllPeliculas(connection);
    String jsonResponse = Pelicula.listPeliculasToJson(pls);

    return jsonResponse;
}
}
