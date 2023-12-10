package Recursos.Controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import Recursos.Conexion.ConnectionDb;
import Recursos.Models.RecursosDigitales.Pelicula;

@WebServlet("/PeliculaController")
public class PeliculaController extends HttpServlet {
private static final Logger logger = LogManager.getLogger(PeliculaController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logic to retrieve a specific Pelicula by its code
        String codigoIdentificacion = request.getParameter("codigoIdentificacion");
        if (codigoIdentificacion != null) {
            // Logic to retrieve Pelicula details from the database based on the code

            try(ConnectionDb connection = new ConnectionDb()) {
                Pelicula pelicula = new Pelicula(codigoIdentificacion);
                pelicula = pelicula.selectPelicula(connection);

                // Set the Pelicula details as an attribute and forward to the details JSP page
                request.setAttribute("peliculaDetails", pelicula);
                request.getRequestDispatcher("/peliculaDetails.jsp").forward(request, response);
            } catch (SQLException e) {
                // Log and handle the error
                logger.error("Error retrieving Pelicula details: " + e.getMessage());
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving Pelicula details");
            }
        } else {
            // Retrieve all Peliculas
            try(ConnectionDb connection = new ConnectionDb()) {
                Pelicula peliculaModel = new Pelicula();
                List<Pelicula> peliculas = peliculaModel.selectAllPeliculas(connection);

                // You can use the list of Peliculas to display details or perform other actions
                response.getWriter().println("All Peliculas: " + peliculas.toString());
            } catch (SQLException e) {
                // Log and handle the error
                logger.error("Error retrieving all Peliculas: " + e.getMessage());
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving all Peliculas");
            }
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

            response.getWriter().println("New Pelicula inserted successfully!");
        } catch (SQLException e) {
            // Log and handle the error
            logger.error("Error inserting new Pelicula: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error inserting new Pelicula");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
// Logic to update an existing Pelicula
try(ConnectionDb connection = new ConnectionDb()) {
    // Extract Pelicula details from the request
    Pelicula updatedPelicula = extractPeliculaFromRequest(request);

    // Update the existing Pelicula in the database
    updatedPelicula.updatePelicula(connection);

    response.getWriter().println("Pelicula updated successfully!");
} catch (SQLException e) {
    // Log and handle the error
    logger.error("Error updating Pelicula: " + e.getMessage());
    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating Pelicula");
}
}
protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logic to delete an existing Pelicula

        try(ConnectionDb connection = new ConnectionDb()) {
            // Extract Pelicula details from the request
            Pelicula deletedPelicula = extractPeliculaFromRequest(request);

            // Delete the existing Pelicula from the database
            deletedPelicula.deletePelicula(connection);

            response.getWriter().println("Pelicula deleted successfully!");
        } catch (SQLException e) {
            // Log and handle the error
            logger.error("Error deleting Pelicula: " + e.getMessage());
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
}
