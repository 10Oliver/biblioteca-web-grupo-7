// package sv.edu.udb.www.Recursos.Controllers;
package sv.edu.udb.www.Recursos.Controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.udb.www.Recursos.Conexion.ConnectionDb;
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
            registerUser(request, response);
        } else if ("changePassword".equals(action)) {
            changePassword(request, response);
        }else if("listUser".equals(action)){

        }
        // Add more actions as needed

        switch (action) {
            case "register":
                registerUser(request, response);
                break;
            case "changePassword":
            changePassword(request, response);
                break;
            case "createUser":
            // create method to add users
                break;
            case "getAllUser":
            getAllUsers(request,response);
                break;

            default:
                break;
        }
    }
    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user information from the request
        String nombreUsuario = request.getParameter("nombreUsuario");
        String correo = request.getParameter("correo");
        // Get other user attributes...

        // Create a new User model
        Usuario user = new Usuario();
        user.setNombreUsuario(nombreUsuario);
        user.setCorreo(correo);
        // Set other user attributes...

        // Register the user
        ConnectionDb connection = new ConnectionDb();
        user.Registrar(connection);

        // Handle the response or redirect to a success page
        response.sendRedirect("/registrationSuccess.jsp");
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
        response.sendRedirect("/passwordChangeSuccess.jsp");
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
