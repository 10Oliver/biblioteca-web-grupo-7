/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navegabilidad;
import Recursos.Models.Utils.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Recursos.Conexion.ConnectionDb;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Oliver
 */
@WebServlet(name = "panelControlador", urlPatterns = {"/panel.do"})
public class panelPrincipalControlador extends HttpServlet {
    ConnectionDb connection = new ConnectionDb();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        try {
            String vista = request.getParameter("vista");
            
            switch(vista) {
                case "usuario":
                    request.setAttribute("vistaSeleccionada", 1);
                    // Se colocan los datos del usuario
                    request.setAttribute("usuarios", this.generarUsuarios());
                    break;
                case "inventario":
                    request.setAttribute("vistaSeleccionada", 2);
                    break;
                case "prestamo":
                    request.setAttribute("vistaSeleccionada", 3);
                    break;
                case "configuracion":
                    request.setAttribute("vistaSeleccionada", 4);
                    break;
                default:
                    System.out.println(vista);
                    System.out.println("No reconocido");
                    request.setAttribute("vistaSeleccionada", 0);
                    break;
            }
            // Se envia la petición al panel principal
            RequestDispatcher dispatcher = request.getRequestDispatcher("privada/panelPrincipal.jsp");
            dispatcher.forward(request, response);
        } catch(Exception e) {
        }
    }
    private List<Usuario> generarUsuarios() {
        List<Usuario> usuarios;
        Usuario usuario = new Usuario();
        usuarios = usuario.selectAllUsuarios(connection);
        return usuarios;
    }
}
