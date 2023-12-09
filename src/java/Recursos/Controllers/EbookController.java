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
import Recursos.Models.RecursosDigitales.Ebook;

@WebServlet("/EbookController")
public class EbookController extends HttpServlet{
    private static final Logger logger = LogManager.getLogger(EbookController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve a specific Ebook by its code
        String ebookCode = request.getParameter("code");
        if (ebookCode != null) {

            try(ConnectionDb connection = new ConnectionDb()) {

                Ebook ebook = new Ebook(ebookCode);
                ebook = ebook.selectEbook(connection);
                // Set the Ebook details as an attribute and forward to the details JSP page
                request.setAttribute("ebookDetails", ebook);
                request.getRequestDispatcher("/ebookDetails.jsp").forward(request, response);
            } catch (SQLException e) {
                // Log and handle the error
                logger.error("Error retrieving Ebook details: " + e.getMessage());
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving Ebook details");
            }
        } else {
            // Retrieve all Ebooks
            try(ConnectionDb connection = new ConnectionDb();) {
                Ebook ebookModel = new Ebook();
                List<Ebook> ebooks = ebookModel.selectAllEbooks(connection);
                // You can use the list of Ebooks to display details or perform other actions
                response.getWriter().println("All Ebooks: " + ebooks.toString());
            } catch (SQLException e) {
                // Log and handle the error
                logger.error("Error retrieving all Ebooks: " + e.getMessage());
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving all Ebooks");
            }
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logic for handling POST requests (inserting a new Ebook into the database)
        try(ConnectionDb connection = new ConnectionDb()) {
            Ebook newEbook = extractEbookFromRequest(request);
            newEbook.insertEbook(connection);
            // Redirect or forward to another page as needed
            response.sendRedirect("/success.jsp");
        } catch (SQLException e) {
            // Log and handle database connection or insertion errors
            logger.error("Error inserting Ebook: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error inserting Ebook");
        }
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
// Logic for handling PUT requests (updating an existing Ebook in the database)
String ebookCode = request.getParameter("code");
if (ebookCode != null) {

    try(ConnectionDb connection = new ConnectionDb()) {
        Ebook existingEbook = new Ebook(ebookCode);
        existingEbook = existingEbook.selectEbook(connection);
        if (existingEbook != null) {
            Ebook updatedEbook = extractEbookFromRequest(request);
            updatedEbook.setCodigoIdentificacion(ebookCode);
            updatedEbook.updateEbook(connection);
            response.getWriter().println("Ebook Updated Successfully");
        } else {
            response.getWriter().println("Ebook Not Found");
        }
    } catch (SQLException e) {
        // Log and handle database connection or update errors
        logger.error("Error updating Ebook: " + e.getMessage());
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating Ebook");
    }
} else {
    response.getWriter().println("Ebook Code is required for update");
}
}
protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logic for handling DELETE requests (deleting an Ebook from the database)
        String ebookCode = request.getParameter("code");
        if (ebookCode != null) {
            try(ConnectionDb connection = new ConnectionDb()) {
                Ebook existingEbook = new Ebook(ebookCode);
                existingEbook = existingEbook.selectEbook(connection);
                if (existingEbook != null) {
                    existingEbook.deleteEbook(connection);
                    response.getWriter().println("Ebook Deleted Successfully");
                } else {
                    response.getWriter().println("Ebook Not Found");
                }
            } catch (SQLException e) {
                // Log and handle database connection or deletion errors
                logger.error("Error deleting Ebook: " + e.getMessage());
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting Ebook");
            }
        } else {
            response.getWriter().println("Ebook Code is required for delete");
        }
    }

    private Ebook extractEbookFromRequest(HttpServletRequest request) {
        // Extract and set Ebook details from request parameters
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String editorial = request.getParameter("editorial");
        String numeroPaginas = request.getParameter("numeroPaginas");
        String url = request.getParameter("url");
        int isbn = Integer.parseInt(request.getParameter("isbn"));
        String edicion = request.getParameter("edicion");
        String lugarPublicacion = request.getParameter("lugarPublicacion");
        Date fechaPublicacion = Date.valueOf(request.getParameter("fechaPublicacion"));
        String genero = request.getParameter("genero");
        String idioma = request.getParameter("idioma");
        String notas = request.getParameter("notas");
        int stock = Integer.parseInt(request.getParameter("stock"));
        String nombreEstante = request.getParameter("nombreEstante");

        // Create and return a new Ebook object
        return new Ebook(titulo, fechaPublicacion, stock, nombreEstante, genero, autor, editorial, numeroPaginas, url, isbn, edicion, lugarPublicacion, idioma, notas);
    }


}
