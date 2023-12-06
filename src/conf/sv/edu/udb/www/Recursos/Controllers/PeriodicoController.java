package conf.sv.edu.udb.www.Recursos.Controllers;

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

import conf.sv.edu.udb.www.Recursos.Conexion.ConnectionDb;
import conf.sv.edu.udb.www.Recursos.Models.RecursosFisicos.Periodico;

@WebServlet("/PeriodicoController")
public class PeriodicoController extends HttpServlet{
    private static final Logger logger = LogManager.getLogger(PeriodicoController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve a specific Periodico by its code
        String periodicoCode = request.getParameter("code");

        try (ConnectionDb connection = new ConnectionDb()) {
            if (periodicoCode != null) {
                // Logic to retrieve Periodico details from the database based on the code
                Periodico periodico = new Periodico(periodicoCode);
                periodico = periodico.selectPeriodico(connection);

                // Set the Periodico details as an attribute and forward to the details JSP page
                request.setAttribute("periodicoDetails", periodico);
                request.getRequestDispatcher("/periodicoDetails.jsp").forward(request, response);
            } else {
                // Retrieve all Periodicos
                Periodico periodicoModel = new Periodico();
                List<Periodico> periodicos = periodicoModel.selectAllPeriodico(connection);

                // You can use the list of Periodicos to display details or perform other actions
                request.setAttribute("allPeriodicos", periodicos);
                request.getRequestDispatcher("/allPeriodicos.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            // Log and handle the error
            logger.error("Error retrieving Periodicos: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving Periodicos");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
// Logic for handling POST requests (inserting a new Periodico into the database)
Periodico newPeriodico = extractPeriodicoFromRequest(request);

try (ConnectionDb connection = new ConnectionDb()) {
    newPeriodico.insertPeriodico(connection);
    response.sendRedirect("/success.jsp");

} catch (SQLException e) {
    // Log and handle the error
    logger.error("Error inserting new Periodico: " + e.getMessage());
    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error inserting new Periodico");
}
}

protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logic for handling PUT requests (updating an existing Periodico in the database)
        String periodicoCode = request.getParameter("code"); // assuming you pass the Periodico code as a parameter
        if (periodicoCode != null) {
            // Logic to update Periodico details in the database based on the code

            try (ConnectionDb connection = new ConnectionDb()) {
                Periodico periodicoModel = new Periodico(periodicoCode);
                Periodico existingPeriodico = periodicoModel.selectPeriodico(connection);
                if (existingPeriodico != null) {
                    // Update Periodico details based on request parameters
                    existingPeriodico.setTitulo(request.getParameter("titulo"));
                    existingPeriodico.setNombrePeriodico(request.getParameter("nombrePeriodico"));
                    // ... (update other fields)

                    // Update the Periodico in the database
                    existingPeriodico.updatePeriodico(connection);
                    response.getWriter().println("Periodico Updated Successfully");
                } else {
                    response.getWriter().println("Periodico Not Found");
                }
            } catch (SQLException e) {
                // Handle database connection or update errors
                e.printStackTrace();
            }
        } else {
            response.getWriter().println("Periodico Code is required for update");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
// Logic for handling DELETE requests (deleting a Periodico from the database)
String periodicoCode = request.getParameter("code"); // assuming you pass the Periodico code as a parameter
if (periodicoCode != null) {
    // Logic to delete Periodico from the database based on the code
    try (ConnectionDb connection = new ConnectionDb()) {
        Periodico periodicoModel = new Periodico(periodicoCode);
        Periodico existingPeriodico = periodicoModel.selectPeriodico(connection);
        if (existingPeriodico != null) {
            // Delete the Periodico from the database
            existingPeriodico.deletePeriodico(connection);
            response.getWriter().println("Periodico Deleted Successfully");
        } else {
            response.getWriter().println("Periodico Not Found");
        }
    } catch (SQLException e) {
        // Handle database connection or deletion errors
        e.printStackTrace();
    }
} else {
    response.getWriter().println("Periodico Code is required for delete");
}
}


    private Periodico extractPeriodicoFromRequest(HttpServletRequest request) {
        // Extract and set Periodico details from request parameters
        String titulo = request.getParameter("titulo");
        String nombrePeriodico = request.getParameter("nombrePeriodico");
        String lugarPublicacion = request.getParameter("lugarPublicacion");
        Date fechaPublicacion = Date.valueOf(request.getParameter("fechaPublicacion"));
        int numeroPaginas = Integer.parseInt(request.getParameter("numeroPaginas"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String nombreEstante = request.getParameter("nombreEstante");

        // Create and return a new Periodico object
        return new Periodico(titulo, fechaPublicacion, stock, nombreEstante, numeroPaginas, nombrePeriodico, lugarPublicacion);
    }
}
