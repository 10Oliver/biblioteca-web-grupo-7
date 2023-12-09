// package sv.edu.udb.www.Recursos.Controllers;
package sv.edu.udb.www.Recursos.Controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.udb.www.Recursos.Conexion.ConnectionDb;
import sv.edu.udb.www.Recursos.Models.StatusResponseIntern;
import sv.edu.udb.www.Recursos.Models.Utils.Usuario;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    ConnectionDb connection = new ConnectionDb();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle user-related actions based on the request
        String action = request.getParameter("action");
        String busqueda = request.getParameter("busqueda");
        if(action != null){
            switch (action) {
                case "nombreUsuario":
                    getUserByUserName(connection,busqueda);
                    break;
                case "correo":
                    getUserByCorreo(connection,busqueda);
                    break;
                default:
                    getAllUsersJson(connection);
                    break;
        }
        }else{
                    getAllUsersJson(connection);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle user-related actions based on the request
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            try {
                registerUser(request, response);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if ("changePassword".equals(action)) {
            changePassword(request, response);
        }else if("listUser".equals(action)){

        }
        // Add more actions as needed

        switch (action) {
            case "register":
                try {
                    registerUser(request, response);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case "changePassword":
            changePassword(request, response);
                break;
            case "createUser":
            // create method to add users
                break;
            case "getAllUser":
            getAllUsersJson(connection);
                break;

            default:
                break;
        }
    }
    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        // Get user information from the request
        String nombreUsuario = request.getParameter("nombreUsuario");
        String correo = request.getParameter("correo");
        String fechaNacimiento = request.getParameter("fechaNacimiento");
        String telefono = request.getParameter("telefono");
        String rolId = request.getParameter("rolId");
        String contrasena = request.getParameter("contrasena");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // Parse the string to a Date object
        Date parsedDate = dateFormat.parse(fechaNacimiento);

        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

        // Create a new User model
        Usuario user = new Usuario();
        user.setNombreUsuario(nombreUsuario);
        user.setCorreo(correo);
        user.setTelefono(Integer.parseInt(telefono));
        user.setFechaNacimiento(sqlDate);
        user.setIdRol(Integer.parseInt(rolId));
        user.setContrasena(contrasena);

        // Register the user
        ConnectionDb connection = new ConnectionDb();
        user.Registrar(connection);

        // Handle the response or redirect to a success page
        StatusResponseIntern message = new StatusResponseIntern("Successfully Created",200);
            String jsonResponse = message.StatusCode();
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse);
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the request
        int userId = Integer.parseInt(request.getParameter("userId"));
        String newPassword = request.getParameter("newPassword");
        String nombreUsuario = request.getParameter("nombreUsuario");

        // Create a new User model
        Usuario user = new Usuario();
        user.cambiarContrasena(new ConnectionDb(), newPassword, userId, nombreUsuario);

        // Handle the response or redirect to a success page
        StatusResponseIntern message = new StatusResponseIntern("Successfully Created",200);
            String jsonResponse = message.StatusCode();
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse);
    }
    private void getAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve all users from the database
        ConnectionDb connection = new ConnectionDb();
        Usuario user = new Usuario();
        List<Usuario> allUsers = user.selectAllUsuarios(connection);

        // Set the list of users as an attribute in the request
        request.setAttribute("allUsers", allUsers);

        // Forward the request to a JSP page for displaying the users
        RequestDispatcher dispatcher = request.getRequestDispatcher("/allUsers.jsp");
        dispatcher.forward(request, response);
    }

    private String getUserByCorreo(ConnectionDb connection, String busqueda){
        Usuario usr = new Usuario();
        usr.setCorreo(busqueda);
        usr.selectUsuarioByCorreo(connection);

        return usr.toJson();
    }

    private String getUserByUserName(ConnectionDb connection, String busqueda){
        Usuario usr = new Usuario();
        usr.setNombreUsuario(busqueda);
        usr.selectUsuarioByUsername(connection);

        return usr.toJson();
    }

    private String getAllUsersJson(ConnectionDb connection) {
        Usuario user = new Usuario();
        List<Usuario> allUsers = user.selectAllUsuarios(connection);

        StringBuilder jsonString = new StringBuilder("[");
        for (Usuario usuario : allUsers) {
            jsonString.append(usuario.toJson()).append(",");
        }
        if (!allUsers.isEmpty()) {
            jsonString.deleteCharAt(jsonString.length() - 1); // Remove the trailing comma
        }
        jsonString.append("]");

        return jsonString.toString();
    }


}
