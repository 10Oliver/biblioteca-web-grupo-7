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
import sv.edu.udb.www.Recursos.Models.RecursosFisicos.Periodico;

@WebServlet("/PeriodicoController")
public class PeriodicoController extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve a specific Periodico by its code
        String busqueda = request.getParameter("busqueda");
        String action = request.getParameter("action");

        try (ConnectionDb connection = new ConnectionDb()) {
            String jsonResponse = null;
            if(action != null){

                switch (action) {
                    case "codigo":
                        jsonResponse = getByCode(connection, busqueda);
                        response.setContentType("application/json");
                        response.getWriter().write(jsonResponse);
                        break;
                    case "titulo":
                        jsonResponse = getByTitulo(connection, busqueda);
                        response.setContentType("application/json");
                        response.getWriter().write(jsonResponse);
                        break;
                    case "lugarPublicacion":
                        jsonResponse = getByLugarPublicacion(connection, busqueda);
                        response.setContentType("application/json");
                        response.getWriter().write(jsonResponse);
                        break;
                    case "nombrePeriodico":
                        jsonResponse = getByNombrePeriodico(connection, busqueda);
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

            // response.setContentType("application/json");
            // response.getWriter().write(jsonResponse);
        } catch (SQLException e) {
            // Log and handle the error

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

    private String getByCode(ConnectionDb connection, String periodicoCode) throws SQLException {
        Periodico periodicoModel = new Periodico(periodicoCode);
        Periodico periodico = periodicoModel.selectPeriodico(connection);

        if (periodico != null) {
            return periodico.toJson();
        } else {
            return "{\"error\": \"Periodico not found\"}";
        }
    }

    private String getByTitulo(ConnectionDb connection, String titulo) throws SQLException {
        Periodico periodicoModel = new Periodico();
        periodicoModel.setTitulo(titulo);
        Periodico periodico = periodicoModel.selectPeriodicoByTitulo(connection);

        if (periodico != null) {
            return periodico.toJson();
        } else {
            return "{\"error\": \"Periodico not found\"}";
        }
    }

    private String getByLugarPublicacion(ConnectionDb connection, String lugarPublicacion) throws SQLException {
        Periodico periodicoModel = new Periodico();
        periodicoModel.setLugarPublicacion(lugarPublicacion);
        Periodico periodico = periodicoModel.selectPeriodicoByLugarPublicacion(connection);

        if (periodico != null) {
            return periodico.toJson();
        } else {
            return "{\"error\": \"Periodico not found\"}";
        }
    }

    private String getByNombrePeriodico(ConnectionDb connection, String nombrePeriodico) throws SQLException {
        Periodico periodicoModel = new Periodico();
        periodicoModel.setNombrePeriodico(nombrePeriodico);
        Periodico periodico = periodicoModel.selectPeriodicoByNombrePeriodico(connection);

        if (periodico != null) {
            return periodico.toJson();
        } else {
            return "{\"error\": \"Periodico not found\"}";
        }
    }

    // private String getAllPeriodicos(ConnectionDb connection) throws SQLException {
    //     Periodico periodicoModel = new Periodico();
    //     List<Periodico> periodicos = periodicoModel.selectAllPeriodico(connection);

    //     if (!periodicos.isEmpty()) {
    //         StringBuilder jsonArray = new StringBuilder("[");
    //         for (Periodico periodico : periodicos) {
    //             jsonArray.append(periodico.toJson()).append(",");
    //         }
    //         jsonArray.deleteCharAt(jsonArray.length() - 1); // Remove the trailing comma
    //         jsonArray.append("]");
    //         return jsonArray.toString();
    //     } else {
    //         return "{\"error\": \"No Periodicos found\"}";
    //     }
    // }

    private String GetAll(ConnectionDb connection){
    Periodico pr = new Periodico();
    List<Periodico> prs = pr.selectAllPeriodico(connection);
    String jsonResponse = Periodico.listPeriodicoToJson(prs);

    return jsonResponse;
}
}
