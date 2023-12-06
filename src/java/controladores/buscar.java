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
@WebServlet(name = "registrarControlador", urlPatterns = {"/registarProducto.do"})
public class buscar extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("vistaInventario", 1);
        request.setAttribute("vistaSeleccionada", 2);
        // Se envia la petición al panel principal
        RequestDispatcher dispatcher = request.getRequestDispatcher("privada/panelPrincipal.jsp");
        dispatcher.forward(request, response);
    }
}
