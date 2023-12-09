package navegabilidad;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Oliver
 */
@WebServlet(name = "productoControlador", urlPatterns = {"/producto.do"})
public class producto extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String producto = request.getParameter("tipo");
            System.out.print("------------------------");
            System.out.print(producto);
            switch (producto) {
                case "libro":
                    request.setAttribute("vistaSeleccionada", 2);
                    // Se colocan los datos del usuario
                    request.setAttribute("vistaInventario", 2);
                    request.setAttribute("producto", 1);
                    break;
                case "e-book":
                    request.setAttribute("vistaSeleccionada", 2);
                    request.setAttribute("vistaInventario", 2);
                    request.setAttribute("producto", 2);
                    break;
                case "periodico":
                    request.setAttribute("vistaSeleccionada", 2);
                    request.setAttribute("vistaInventario", 2);
                    request.setAttribute("producto", 3);
                    break;
                case "tesis":
                    request.setAttribute("vistaSeleccionada", 2);
                    request.setAttribute("vistaInventario", 2);
                    request.setAttribute("producto", 4);
                    break;
                case "pelicula":
                    request.setAttribute("vistaSeleccionada", 2);
                    request.setAttribute("vistaInventario", 2);
                    request.setAttribute("producto", 5);
                    break;
                case "cd":
                    request.setAttribute("vistaSeleccionada", 2);
                    request.setAttribute("vistaInventario", 2);
                    request.setAttribute("producto", 6);
                    break;
                case "revista":
                    request.setAttribute("vistaSeleccionada", 2);
                    request.setAttribute("vistaInventario", 2);
                    request.setAttribute("producto", 7);
                    break;
                default:
                    System.out.println(producto);
                    System.out.println("No reconocido");
                    request.setAttribute("vistaSeleccionada", 0);
                    break;
            }
            // Se envia la petición al panel principal
            RequestDispatcher dispatcher = request.getRequestDispatcher("privada/panelPrincipal.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
        }
    }
}
