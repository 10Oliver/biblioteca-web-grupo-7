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
import Recursos.Models.RecursosFisicos.Tesis;

@WebServlet("/TesisController")
public class TesisController extends HttpServlet{
    private static final Logger logger = LogManager.getLogger(TesisController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve a specific Tesis by its code
        String tesisCode = request.getParameter("code");

        try (ConnectionDb connection = new ConnectionDb()) {
            if (tesisCode != null) {
                // Logic to retrieve Tesis details from the database based on the code
                Tesis tesis = new Tesis(tesisCode);
                tesis = tesis.selectTesis(connection);

                // Set the Tesis details as an attribute and forward to the details JSP page
                request.setAttribute("tesisDetails", tesis);
                request.getRequestDispatcher("/tesisDetails.jsp").forward(request, response);
            } else {
                // Retrieve all Tesis
                Tesis tesisModel = new Tesis();
                List<Tesis> tesisList = tesisModel.selectAllTesis(connection);

                // You can use the list of Tesis to display details or perform other actions
                request.setAttribute("allTesis", tesisList);
                request.getRequestDispatcher("/allTesis.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            // Log and handle the error
            logger.error("Error retrieving Tesis: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving Tesis");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logic for handling POST requests (inserting a new Tesis into the database)
        Tesis newTesis = extractTesisFromRequest(request);

        try (ConnectionDb connection = new ConnectionDb()) {
            newTesis.insertTesis(connection);
            response.sendRedirect("/success.jsp");

        } catch (SQLException e) {
            // Log and handle the error
            logger.error("Error inserting new Tesis: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error inserting new Tesis");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logic for handling PUT requests (updating an existing Tesis in the database)
        String tesisCode = request.getParameter("code"); // assuming you pass the Tesis code as a parameter
        if (tesisCode != null) {
            // Logic to update Tesis details in the database based on the code

            try (ConnectionDb connection = new ConnectionDb()) {
                Tesis tesisModel = new Tesis(tesisCode);
                Tesis existingTesis = tesisModel.selectTesis(connection);
                if (existingTesis != null) {
                    // Update Tesis details based on request parameters
                    existingTesis.setTitulo(request.getParameter("titulo"));
                    existingTesis.setAutor(request.getParameter("autor"));
                    // ... (update other fields)

                    // Update the Tesis in the database
                    existingTesis.updateTesis(connection);
                    response.getWriter().println("Tesis Updated Successfully");
                } else {
                    response.getWriter().println("Tesis Not Found");
                }
            } catch (SQLException e) {
                // Handle database connection or update errors
                e.printStackTrace();
            }
        } else {
            response.getWriter().println("Tesis Code is required for update");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logic for handling DELETE requests (deleting a Tesis from the database)
        String tesisCode = request.getParameter("code"); // assuming you pass the Tesis code as a parameter
        if (tesisCode != null) {
            // Logic to delete Tesis from the database based on the code
            try (ConnectionDb connection = new ConnectionDb()) {
                Tesis tesisModel = new Tesis(tesisCode);
                Tesis existingTesis = tesisModel.selectTesis(connection);
                if (existingTesis != null) {
                    // Delete the Tesis from the database
                    existingTesis.deleteTesis(connection);
                    response.getWriter().println("Tesis Deleted Successfully");
                } else {
                    response.getWriter().println("Tesis Not Found");
                }
            } catch (SQLException e) {
                // Handle database connection or deletion errors
                e.printStackTrace();
            }
        } else {
            response.getWriter().println("Tesis Code is required for delete");
        }
    }

    private Tesis extractTesisFromRequest(HttpServletRequest request) {
        // Extract and set Tesis details from request parameters
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        int numeroPaginas = Integer.parseInt(request.getParameter("numeroPaginas"));
        String editorial = request.getParameter("editorial");
        String nivelAcademico = request.getParameter("nivelAcademico");
        String institucionAcademica = request.getParameter("institucionAcademica");
        String facultad = request.getParameter("facultad");
        Date fechaPublicacion = Date.valueOf(request.getParameter("fechaPublicacion"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String nombreEstante = request.getParameter("nombreEstante");

        // Create and return a new Tesis object
        return new Tesis(titulo, fechaPublicacion, stock, nombreEstante, numeroPaginas, autor, editorial, nivelAcademico, institucionAcademica, facultad);
    }
}
