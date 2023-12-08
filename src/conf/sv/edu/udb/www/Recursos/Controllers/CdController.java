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
import conf.sv.edu.udb.www.Recursos.Models.RecursosDigitales.Cd;

@WebServlet("/cdController")
public class CdController extends HttpServlet {
    // ConnectionDb connection = new ConnectionDb();
    private static final Logger logger = LogManager.getLogger(CdController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve a specific CD by its code
    String cdCode = request.getParameter("code");

    try (ConnectionDb connection = new ConnectionDb()) {
        if (cdCode != null) {
            // Logic to retrieve CD details from the database based on the code
            Cd cd = new Cd(cdCode);
            cd = cd.selectCd(connection);

            // Set the CD details as an attribute and forward to the details JSP page
            request.setAttribute("cdDetails", cd);
            request.getRequestDispatcher("/cdDetails.jsp").forward(request, response);
        } else {
            // Retrieve all CDs
            Cd cdModel = new Cd();
            List<Cd> cds = cdModel.selectAllCds(connection);

            // You can use the list of CDs to display details or perform other actions
            request.setAttribute("allCds", cds);
            request.getRequestDispatcher("/allCds.jsp").forward(request, response);
        }
    } catch (SQLException e) {
        // Log and handle the error
        logger.error("Error retrieving CDs: " + e.getMessage());
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving CDs");
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
            response.sendRedirect("/success.jsp");

        } catch (SQLException e) {
            // Log and handle the error
            logger.error("Error inserting new CD: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error inserting new CD");
        }

        // Redirect or forward to another page as needed
        response.sendRedirect("/success.jsp");

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
                    // Update CD details based on request parameters
                    // (you need to implement setter methods in the Cd class)
                    existingCd.setTitulo(request.getParameter("titulo"));
                    existingCd.setAutor(request.getParameter("autor"));
                    // ... (update other fields)

                    // Update the CD in the database
                    existingCd.updateCd(connection);
                    response.getWriter().println("CD Updated Successfully");
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
                    response.getWriter().println("CD Deleted Successfully");
                } else {
                    response.getWriter().println("CD Not Found");
                }
            } catch (SQLException e) {
                // Handle database connection or deletion errors
                e.printStackTrace();
            }
        } else {
            response.getWriter().println("CD Code is required for delete");
        }
    }
}
