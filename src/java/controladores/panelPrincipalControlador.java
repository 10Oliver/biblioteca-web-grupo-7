/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oliver
 */
@WebServlet(name = "AutoresController", urlPatterns = {"/panel.do"})
public class panelPrincipalControlador extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            String vista = request.getParameter("vista");
            
            switch(vista) {
                case "usuario":
                    request.setAttribute("vistaSeleccionada", 1);
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("privada/panelPrincipal.jsp");
        dispatcher.forward(request, response);
        } catch(Exception e) {
        }
    }
}
