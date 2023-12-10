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
import sv.edu.udb.www.Recursos.Models.RecursosFisicos.Tesis;

@WebServlet("/TesisController")
public class TesisController extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve a specific Tesis by its code
        String busqueda = request.getParameter("busqueda");
        String action = request.getParameter("action");
        String jsonResponse = null;
        try (ConnectionDb connection = new ConnectionDb()) {
            if(action != null){

                switch (action) {
                    case "codigo":
                        // Handle the select by ID action
                        jsonResponse = handleSelectById(connection,busqueda);
                        response.setContentType("application/json");
                        response.getWriter().write(jsonResponse);
                        break;
                    case "titulo":
                        // Handle the select by Titulo action
                        jsonResponse = handleSelectByTitulo(connection,busqueda);
                        response.setContentType("application/json");
                        response.getWriter().write(jsonResponse);
                        break;
                    case "autor":
                        // Handle the select by Autor action
                        jsonResponse = handleSelectByAutor(connection,busqueda);
                        response.setContentType("application/json");
                        response.getWriter().write(jsonResponse);
                        break;
                    case "editorial":
                        // Handle the select by Editorial action
                        jsonResponse = handleSelectByEditorial(connection,busqueda);
                        response.setContentType("application/json");
                        response.getWriter().write(jsonResponse);
                        break;
                    default:
                    jsonResponse = GetAll(connection);
                    response.setContentType("application/json");
                    response.getWriter().write(jsonResponse);
                    break;
            }
            }else{
                    jsonResponse = GetAll(connection);
                    response.setContentType("application/json");
                    response.getWriter().write(jsonResponse);
            }
        } catch (SQLException e) {
            // Log and handle the error

            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving Tesis");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logic for handling POST requests (inserting a new Tesis into the database)
        Tesis newTesis = extractTesisFromRequest(request);

        try (ConnectionDb connection = new ConnectionDb()) {
            newTesis.insertTesis(connection);
            StatusResponseIntern message = new StatusResponseIntern("Successfully Created",200);
            String jsonResponse = message.StatusCode();
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse);

        } catch (SQLException e) {
            // Log and handle the error

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
                // Tesis tesisModel = new Tesis(tesisCode);
                // Tesis existingTesis = tesisModel.selectTesis(connection);
                // if (existingTesis != null) {
                    Tesis existingTesis = extractTesisFromRequest(request);
                    existingTesis.setCodigoIdentificacion(tesisCode);
                    // Update the Tesis in the database
                    existingTesis.updateTesis(connection);
                    StatusResponseIntern message = new StatusResponseIntern("Successfully Updated",200);
                    String jsonResponse = message.StatusCode();
                    response.setContentType("application/json");
                    response.getWriter().write(jsonResponse);
                    // }
                } catch (SQLException e) {
                response.getWriter().println("Tesis Not Found");
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
                // Tesis existingTesis = tesisModel.selectTesis(connection);
                // if (existingTesis != null) {
                    // Delete the Tesis from the database
                    tesisModel.deleteTesis(connection);
                    response.getWriter().println("Tesis Deleted Successfully");
                // } else {
                    // }
                } catch (SQLException e) {
                response.getWriter().println("Tesis Not Found");
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

    private String handleSelectById(ConnectionDb connection, String tesisCode) {
        if (tesisCode != null) {
            Tesis tesis = new Tesis(tesisCode);
            tesis = tesis.selectTesis(connection);

            if (tesis != null) {
                return tesis.toJson();
            } else {
                return "{\"message\": \"Tesis not found\"}";
            }
        } else {
            return "{\"message\": \"Tesis Code is required for selectById\"}";
        }
    }

    private String handleSelectByTitulo(ConnectionDb connection, String titulo) {
        if (titulo != null) {
            Tesis tesis = new Tesis();
            tesis.setTitulo(titulo);
            Tesis selectedTesis = tesis.selectTesisByTitulo(connection);

            if (selectedTesis != null) {
                return selectedTesis.toJson();
            } else {
                return "{\"message\": \"No Tesis found with the provided Titulo\"}";
            }
        } else {
            return "{\"message\": \"Titulo is required for selectByTitulo\"}";
        }
    }

    private String handleSelectByAutor(ConnectionDb connection, String autor) {
        if (autor != null) {
            Tesis tesis = new Tesis();
            tesis.setAutor(autor);
            Tesis selectedTesis = tesis.selectTesisByAutor(connection);

            if (selectedTesis != null) {
                return selectedTesis.toJson();
            } else {
                return "{\"message\": \"No Tesis found with the provided Autor\"}";
            }
        } else {
            return "{\"message\": \"Autor is required for selectByAutor\"}";
        }
    }

    private String handleSelectByEditorial(ConnectionDb connection, String editorial) {
        if (editorial != null) {
            Tesis tesis = new Tesis();
            tesis.setEditorial(editorial);
            Tesis selectedTesis = tesis.selectTesisByEditorial(connection);

            if (selectedTesis != null) {
                return selectedTesis.toJson();
            } else {
                return "{\"message\": \"No Tesis found with the provided Editorial\"}";
            }
        } else {
            return "{\"message\": \"Editorial is required for selectByEditorial\"}";
        }
    }

private String GetAll(ConnectionDb connection){
    Tesis ek = new Tesis();
    List<Tesis> eks = ek.selectAllTesis(connection);
    String jsonResponse = Tesis.listTesisToJson(eks);

    return jsonResponse;
}

}
