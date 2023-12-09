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
import sv.edu.udb.www.Recursos.Models.RecursosDigitales.Ebook;

@WebServlet("/EbookController")
public class EbookController extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve a specific Ebook by its code
        String action = request.getParameter("action");
        String busqueda = request.getParameter("busqueda");

            try(ConnectionDb connection = new ConnectionDb()) {
                String jsonResponse = null;
                if(action != null){
                    switch (action) {
                        case "codigo":
                            response.setContentType("application/json");

                            jsonResponse = ByCode(connection,busqueda);
                            response.getWriter().write(jsonResponse);
                            break;
                        case "autor":
                            response.setContentType("application/json");

                            jsonResponse = ByAutor(connection,busqueda);
                            response.getWriter().write(jsonResponse);
                            break;
                        case "editorial":
                            response.setContentType("application/json");

                            jsonResponse = ByEditorial(connection,busqueda);
                            response.getWriter().write(jsonResponse);
                            break;
                        case "genero":
                            response.setContentType("application/json");

                            jsonResponse = ByGenero(connection,busqueda);
                            response.getWriter().write(jsonResponse);
                            break;
                        case "idioma":
                            response.setContentType("application/json");

                            jsonResponse = ByIdioma(connection,busqueda);
                            response.getWriter().write(jsonResponse);
                            break;
                        case "titulo":
                            response.setContentType("application/json");

                            jsonResponse = ByTitulo(connection,busqueda);
                            response.getWriter().write(jsonResponse);
                            break;

                        default:
                            response.setContentType("application/json");

                            jsonResponse = GetAll(connection);
                            response.getWriter().write(jsonResponse);
                            break;
                        }
                    }else{
                        response.setContentType("application/json");

                                jsonResponse = GetAll(connection);
                                response.getWriter().write(jsonResponse);
                    }
            } catch (SQLException e) {
                // Log and handle the error
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving Ebook details");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logic for handling POST requests (inserting a new Ebook into the database)
        try(ConnectionDb connection = new ConnectionDb()) {
            Ebook newEbook = extractEbookFromRequest(request);
            newEbook.insertEbook(connection);
            // Redirect or forward to another page as needed
            StatusResponseIntern jsonResponse = new StatusResponseIntern("Ebook agregado exitosamente",200);
            response.getWriter().write(jsonResponse.StatusCode());
        } catch (SQLException e) {
            // Log and handle database connection or insertion errors
            StatusResponseIntern jsonResponse = new StatusResponseIntern("Ebook no se agrego correctamente",500);
            response.getWriter().write(jsonResponse.StatusCode());
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
            StatusResponseIntern jsonResponse = new StatusResponseIntern("Ebook actualizado exitosamente",200);
            response.getWriter().write(jsonResponse.StatusCode());
        } else {
            StatusResponseIntern jsonResponse = new StatusResponseIntern("Ebook no se actualizo exitosamente",500);
            response.getWriter().write(jsonResponse.StatusCode());
        }
    } catch (SQLException e) {
        // Log and handle database connection or update errors
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
                    StatusResponseIntern jsonResponse = new StatusResponseIntern("Ebook borrado exitosamente",200);
            response.getWriter().write(jsonResponse.StatusCode());
                } else {
                    StatusResponseIntern jsonResponse = new StatusResponseIntern("Ebook no se borro correctamente",500);
                    response.getWriter().write(jsonResponse.StatusCode());
                }
            } catch (SQLException e) {
                // Log and handle database connection or deletion errors
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

private String ByAutor(ConnectionDb connection,String busqueda)
{
    Ebook ebook = new Ebook();
    ebook.setAutor(busqueda);

    ebook.selectEbookByAutor(connection);

    return ebook.toJson();
}
private String ByTitulo(ConnectionDb connection,String busqueda)
{
    Ebook ebook = new Ebook();
    ebook.setTitulo(busqueda);

    ebook.selectEbookByTitulo(connection);

    return ebook.toJson();
}
private String ByGenero(ConnectionDb connection,String busqueda)
{
    Ebook ebook = new Ebook();
    ebook.setGenero(busqueda);

    ebook.selectEbookByGenero(connection);

    return ebook.toJson();
}
private String ByIdioma(ConnectionDb connection,String busqueda)
{
    Ebook ebook = new Ebook();
    ebook.setIdioma(busqueda);

    ebook.selectEbookByIdioma(connection);

    return ebook.toJson();
}
private String ByEditorial(ConnectionDb connection,String busqueda)
{
    Ebook ebook = new Ebook();
    ebook.setEditorial(busqueda);

    ebook.selectEbookByEditorial(connection);

    return ebook.toJson();
}

private String ByCode(ConnectionDb connection,String busqueda){
    Ebook ebook = new Ebook(busqueda);


    ebook.selectEbook(connection);

    return ebook.toJson();
}

private String GetAll(ConnectionDb connection){
    Ebook ek = new Ebook();
    List<Ebook> eks = ek.selectAllEbooks(connection);
    String jsonResponse = Ebook.listEbooksToJson(eks);

    return jsonResponse;
}

}
