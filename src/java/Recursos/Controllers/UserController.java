package Recursos.Controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conf.sv.edu.udb.www.Recursos.Conexion.ConnectionDb;
import conf.sv.edu.udb.www.Recursos.Models.Utils.Usuario;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
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
            case "getUser":
            getUserByCorreo(request, response);
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

    private void getUserByCorreo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user email from the request parameters
        String userCorreo = request.getParameter("correo");

        if (userCorreo != null && !userCorreo.isEmpty()) {
            // Retrieve the user by email from the database
            ConnectionDb connection = new ConnectionDb();
            Usuario user = new Usuario();
            user.setCorreo(userCorreo);
            Usuario selectedUser = user.selectUsuarioByCorreo(connection);

            // Check if the user was found
            if (selectedUser != null) {
                // Set the selected user as an attribute in the request
                request.setAttribute("selectedUser", selectedUser);

                // Forward the request to a JSP page for displaying the user details
                RequestDispatcher dispatcher = request.getRequestDispatcher("/userDetails.jsp");
                dispatcher.forward(request, response);
            } else {
                // Handle the case when the user is not found
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            }
        } else {
            // Handle the case when the user email is not provided
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User email parameter is missing");
        }
    }


}
