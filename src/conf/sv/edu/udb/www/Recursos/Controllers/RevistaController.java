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
import conf.sv.edu.udb.www.Recursos.Models.RecursosFisicos.Revista;

@WebServlet("/RevistaController")
public class RevistaController extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(RevistaController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve a specific Revista by its code
        String revistaCode = request.getParameter("code");

        try (ConnectionDb connection = new ConnectionDb()) {
            if (revistaCode != null) {
                // Logic to retrieve Revista details from the database based on the code
                Revista revista = new Revista(revistaCode);
                revista = revista.selectRevista(connection);

                // Set the Revista details as an attribute and forward to the details JSP page
                request.setAttribute("revistaDetails", revista);
                request.getRequestDispatcher("/revistaDetails.jsp").forward(request, response);
            } else {
                // Retrieve all Revistas
                Revista revistaModel = new Revista();
                List<Revista> revistas = revistaModel.selectAllRevistas(connection);

                // You can use the list of Revistas to display details or perform other actions
                request.setAttribute("allRevistas", revistas);
                request.getRequestDispatcher("/allRevistas.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            // Log and handle the error
            logger.error("Error retrieving Revistas: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving Revistas");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logic for handling POST requests (inserting a new Revista into the database)
        Revista newRevista = extractRevistaFromRequest(request);

        try (ConnectionDb connection = new ConnectionDb()) {
            newRevista.insertRevista(connection);
            response.sendRedirect("/success.jsp");

        } catch (SQLException e) {
            // Log and handle the error
            logger.error("Error inserting new Revista: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error inserting new Revista");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logic for handling PUT requests (updating an existing Revista in the database)
        String revistaCode = request.getParameter("code"); // assuming you pass the Revista code as a parameter
        if (revistaCode != null) {
            // Logic to update Revista details in the database based on the code

            try (ConnectionDb connection = new ConnectionDb()) {
                Revista revistaModel = new Revista(revistaCode);
                Revista existingRevista = revistaModel.selectRevista(connection);
                if (existingRevista != null) {
                    // Update Revista details based on request parameters
                    existingRevista.setTitulo(request.getParameter("titulo"));
                    existingRevista.setAutor(request.getParameter("autor"));
                    // ... (update other fields)

                    // Update the Revista in the database
                    existingRevista.updateRevista(connection);
                    response.getWriter().println("Revista Updated Successfully");
                } else {
                    response.getWriter().println("Revista Not Found");
                }
            } catch (SQLException e) {
                // Handle database connection or update errors
                e.printStackTrace();
            }
        } else {
            response.getWriter().println("Revista Code is required for update");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logic for handling DELETE requests (deleting a Revista from the database)
        String revistaCode = request.getParameter("code"); // assuming you pass the Revista code as a parameter
        if (revistaCode != null) {
            // Logic to delete Revista from the database based on the code
            try (ConnectionDb connection = new ConnectionDb()) {
                Revista revistaModel = new Revista(revistaCode);
                Revista existingRevista = revistaModel.selectRevista(connection);
                if (existingRevista != null) {
                    // Delete the Revista from the database
                    existingRevista.deleteRevista(connection);
                    response.getWriter().println("Revista Deleted Successfully");
                } else {
                    response.getWriter().println("Revista Not Found");
                }
            } catch (SQLException e) {
                // Handle database connection or deletion errors
                e.printStackTrace();
            }
        } else {
            response.getWriter().println("Revista Code is required for delete");
        }
    }





    private Revista extractRevistaFromRequest(HttpServletRequest request) {
        // Extract and set Revista details from request parameters
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        int numeroPaginas = Integer.parseInt(request.getParameter("numeroPaginas"));
        int isbn = Integer.parseInt(request.getParameter("isbn"));
        String editorial = request.getParameter("editorial");
        String periodicidad = request.getParameter("periodicidad");
        Date fechaPublicacion = Date.valueOf(request.getParameter("fechaPublicacion"));
        String paisCiudad = request.getParameter("paisCiudad");
        String notas = request.getParameter("notas");
        int stock = Integer.parseInt(request.getParameter("stock"));
        String nombreEstante = request.getParameter("nombreEstante");

        // Create and return a new Revista object
        return new Revista(titulo, fechaPublicacion, stock, nombreEstante, numeroPaginas, autor, isbn, periodicidad, paisCiudad, notas, editorial);
    }
}
