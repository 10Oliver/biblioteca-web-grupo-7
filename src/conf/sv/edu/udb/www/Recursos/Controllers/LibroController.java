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
import conf.sv.edu.udb.www.Recursos.Models.RecursosFisicos.Libro;
@WebServlet("/LibroController")
public class LibroController extends HttpServlet{
    private static final Logger logger = LogManager.getLogger(LibroController.class);

     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String libroCode = request.getParameter("code");

        try (ConnectionDb connection = new ConnectionDb()) {
            if (libroCode != null) {
                Libro libro = new Libro(libroCode);
                libro = libro.selectLibro(connection);
                request.setAttribute("libroDetails", libro);
                request.getRequestDispatcher("/libroDetails.jsp").forward(request, response);
            } else {
                Libro libroModel = new Libro();
                List<Libro> libros = libroModel.selectAllLibros(connection);
                request.setAttribute("allLibros", libros);
                request.getRequestDispatcher("/allLibros.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving libros: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving libros");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (ConnectionDb connection = new ConnectionDb()) {
            Libro newLibro = extractLibroFromRequest(request);

            // Insert the new Libro into the database
            newLibro.insertLibro(connection);

            // Redirect or forward to another page as needed
            response.sendRedirect("/success.jsp");
        } catch (SQLException e) {
            // Log and handle the error
            logger.error("Error inserting new Libro: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error inserting new Libro");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String libroCode = request.getParameter("code");
        if (libroCode != null) {
            try (ConnectionDb connection = new ConnectionDb()) {
                Libro libroModel = new Libro(libroCode);
                Libro existingLibro = libroModel.selectLibro(connection);
                if (existingLibro != null) {
                    // Update Libro details based on request parameters
                    existingLibro.setTitulo(request.getParameter("titulo"));
                    existingLibro.setAutor(request.getParameter("autor"));
                    // ... (update other fields)

                    // Update the Libro in the database
                    existingLibro.updateLibro(connection);
                    response.getWriter().println("Libro Updated Successfully");
                } else {
                    response.getWriter().println("Libro Not Found");
                }
            } catch (SQLException e) {
                // Handle database connection or update errors
                e.printStackTrace();
            }
        } else {
            response.getWriter().println("Libro Code is required for update");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String libroCode = request.getParameter("code");
        if (libroCode != null) {
            try (ConnectionDb connection = new ConnectionDb()) {
                Libro libroModel = new Libro(libroCode);
                Libro existingLibro = libroModel.selectLibro(connection);
                if (existingLibro != null) {
                    // Delete the Libro from the database
                    existingLibro.deleteLibro(connection);
                    response.getWriter().println("Libro Deleted Successfully");
                } else {
                    response.getWriter().println("Libro Not Found");
                }
            } catch (SQLException e) {
                // Handle database connection or deletion errors
                e.printStackTrace();
            }
        } else {
            response.getWriter().println("Libro Code is required for delete");
        }
    }






    private Libro extractLibroFromRequest(HttpServletRequest request) {
        // Extract and set Libro details from request parameters
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String editorial = request.getParameter("editorial");
        String numeroPaginasStr = request.getParameter("numeroPaginas");
        int numeroPaginas = Integer.parseInt(numeroPaginasStr);
        int isbn = Integer.parseInt(request.getParameter("isbn"));
        String edicion = request.getParameter("edicion");
        String lugarPublicacion = request.getParameter("lugarPublicacion");
        Date fechaPublicacion = Date.valueOf(request.getParameter("fechaPublicacion"));
        String genero = request.getParameter("genero");
        String idioma = request.getParameter("idioma");
        String notas = request.getParameter("notas");
        int stock = Integer.parseInt(request.getParameter("stock"));
        String nombreEstante = request.getParameter("nombreEstante");

        // Create and return a new Libro object
        return new Libro(titulo, fechaPublicacion, stock, nombreEstante, numeroPaginas, autor, editorial, isbn, edicion, lugarPublicacion, genero, idioma, notas);
    }

}
