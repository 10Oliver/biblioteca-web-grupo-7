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
import sv.edu.udb.www.Recursos.Models.RecursosDigitales.Cd;

@WebServlet("/cdController")
public class CdController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve a specific CD by its code
    String action = request.getParameter("action");
    String busqueda = request.getParameter("busqueda");

    try (ConnectionDb connection = new ConnectionDb()) {
        String jsonResponse = null;
        if (action != null) {
            // Logic to retrieve CD details from the database based on the code
            switch (action) {
                case "codigo":
                jsonResponse = BuscarPorCodigo(connection,busqueda);
                response.setContentType("application/json");

                // Write the JSON-like data to the response
                response.getWriter().write(jsonResponse);
                    break;
                case "autor":

                jsonResponse = BuscarPorAutor(connection, busqueda);
                response.setContentType("application/json");

                // Write the JSON-like data to the response
                response.getWriter().write(jsonResponse);
                    break;
                case "titulo":
                    jsonResponse = BuscarPorTitulo(connection,busqueda);
                    response.setContentType("application/json");

                    // Write the JSON-like data to the response
                    response.getWriter().write(jsonResponse);
                    break;
                case "genero":
                    jsonResponse = BuscarPorGenero(connection, busqueda);
                    response.setContentType("application/json");

                    response.getWriter().write(jsonResponse);
                    break;

                default:

                    break;
            }

            // Set the CD details as an attribute and forward to the details JSP page
            // request.setAttribute("cdDetails", cd);
            // request.getRequestDispatcher("/cdDetails.jsp").forward(request, response);
        } else {
                jsonResponse = BuscarTodos(connection);
                response.setContentType("application/json");

                // Write the JSON-like data to the response
                response.getWriter().write(jsonResponse);
        }
    } catch (SQLException e) {
        // Log and handle the error
        // logger.error("Error retrieving CDs: " + e.getMessage());
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving CDs");
        response.getWriter().write(action);
    }


    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logic for handling POST requests (inserting a new CD into the database)
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String numCanciones = request.getParameter("numCanciones");
        String genero = request.getParameter("genero");
        String fechaPublicacionStr = request.getParameter("fechaPublicacion");
        Date fechaPublicacion = Date.valueOf(fechaPublicacionStr);
        int stock = Integer.parseInt(request.getParameter("stock"));
        String nombreEstante = request.getParameter("nombreEstante");

        // Create a Cd object using the form data
        Cd newCd = new Cd(titulo, fechaPublicacion, stock, nombreEstante, genero, autor, numCanciones);

        // Insert the new CD into the database
        // ConnectionDb connection = null; // Replace with your connection logic
        try(ConnectionDb connection = new ConnectionDb()) {
            // ConnectionDb connection = new ConnectionDb();
            newCd.insertCd(connection);
            StatusResponseIntern message = new StatusResponseIntern("Successfully Created",200);
            String jsonResponse = message.StatusCode();
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse);

        } catch (SQLException e) {
            // Log and handle the error
            // logger.error("Error inserting new CD: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error inserting new CD");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logic for handling PUT requests (updating an existing CD in the database)
        String cdCode = request.getParameter("code"); // assuming you pass the CD code as a parameter
        if (cdCode != null) {
            // Logic to update CD details in the database based on the code

            try(ConnectionDb connection = new ConnectionDb()) {
                Cd cdModel = new Cd(cdCode);
                Cd existingCd = cdModel.selectCd(connection);
                if (existingCd != null) {
                    String titulo = request.getParameter("titulo");
                    String autor = request.getParameter("autor");
                    String numCanciones = request.getParameter("numCanciones");
                    String genero = request.getParameter("genero");
                    String fechaPublicacionStr = request.getParameter("fechaPublicacion");
                    Date fechaPublicacion = Date.valueOf(fechaPublicacionStr);
                    int stock = Integer.parseInt(request.getParameter("stock"));
                    String nombreEstante = request.getParameter("nombreEstante");

                    // Update the CD in the database
                    existingCd.setTitulo(titulo);
                    existingCd.setAutor(autor);
                    existingCd.setNumCanciones(numCanciones);
                    existingCd.setGenero(genero);
                    existingCd.setFechaPublicacion(fechaPublicacion);
                    existingCd.setStock(stock);
                    existingCd.setNombreEstante(nombreEstante);
                    // Update the CD in the database
                    existingCd.updateCd(connection);
                    // response.getWriter().println("CD Updated Successfully");
                    StatusResponseIntern jsonResponse = new StatusResponseIntern("Cd actualizado exitosamente",200);
                    response.getWriter().write(jsonResponse.StatusCode());
                } else {
                    response.getWriter().println("CD Not Found");
                }
            } catch (SQLException e) {
                // Handle database connection or update errors
                e.printStackTrace();
            }
        } else {
            response.getWriter().println("CD Code is required for update");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logic for handling DELETE requests (deleting a CD from the database)
        String cdCode = request.getParameter("code"); // assuming you pass the CD code as a parameter
        if (cdCode != null) {
            // Logic to delete CD from the database based on the code
            try(ConnectionDb connection = new ConnectionDb()) {
                Cd cdModel = new Cd(cdCode);
                Cd existingCd = cdModel.selectCd(connection);
                if (existingCd != null) {
                    // Delete the CD from the database
                    existingCd.deleteCd(connection);
                    // response.getWriter().println("CD Deleted Successfully");

                    StatusResponseIntern jsonResponse = new StatusResponseIntern("Cd eliminado exitosamente",200);
                    response.getWriter().write(jsonResponse.StatusCode());
                } else {
                    StatusResponseIntern jsonResponse = new StatusResponseIntern("Cd no se encontro en la bd",200);
                    response.getWriter().write(jsonResponse.StatusCode());
                    // response.getWriter().write("CD Not Found");
                }
            } catch (SQLException e) {
                // Handle database connection or deletion errors
                e.printStackTrace();
            }
        } else {
            response.getWriter().println("CD Code is required for delete");
        }
    }


    private String BuscarPorGenero(ConnectionDb connection, String busqueda){
        Cd cd = new Cd();
        cd.setGenero(busqueda);
        cd.selectCdByGenero(connection);

        String jsonResponse = cd.toJson();
        return jsonResponse;
    }

    private String BuscarPorTitulo(ConnectionDb connection, String busqueda){
        Cd cd = new Cd();
        cd.setTitulo(busqueda);
        cd.selectCdByTitulo(connection);

        String jsonResponse = cd.toJson();
        return jsonResponse;
    }

    private String BuscarPorCodigo(ConnectionDb connection, String busqueda){
        Cd cd = new Cd(busqueda);
        cd.selectCd(connection);

        String jsonResponse = cd.toJson();
        return jsonResponse;
    }

    private String BuscarPorAutor(ConnectionDb connection, String busqueda){
        Cd cd = new Cd();
        cd.setAutor(busqueda);
        cd.selectCdByAutor(connection);

        String jsonResponse = cd.toJson();
        return jsonResponse;
    }

    private String BuscarTodos(ConnectionDb connection){

        Cd cdModel = new Cd();
        List<Cd> cds = cdModel.selectAllCds(connection);
        String jsonResponse = Cd.listToJson(cds);

        return jsonResponse;
    }
}

