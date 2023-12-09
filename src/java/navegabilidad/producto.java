package navegabilidad;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Recursos.Models.Utils.Autor;
import Recursos.Conexion.ConnectionDb;
import Recursos.Models.Utils.Editorial;
import Recursos.Models.Utils.Estante;
import Recursos.Models.Utils.Genero;
import Recursos.Models.Utils.Idioma;
import java.util.ArrayList;
import java.util.List;

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
        Autor autor = new Autor();
        Editorial editorial = new Editorial();
        Idioma idioma = new Idioma();
        Genero genero = new Genero();
        Estante estante = new Estante();
        List<Autor> autores;
        List<Idioma> idiomas;
        List<Genero> generos;
        List<Editorial> editoriales;
        List<Estante> estantes;
        ConnectionDb connection = new ConnectionDb();
        try {
            String producto = request.getParameter("tipo");
            switch (producto) {
                case "libro":
                    request.setAttribute("vistaSeleccionada", 2);
                    // Se colocan los datos del usuario
                    request.setAttribute("vistaInventario", 2);
                    request.setAttribute("producto", 1);
                    // Cargamos los autores
                    autores = autor.selectAllAutors(connection);
                    editoriales = editorial.selectAllEditorials(connection);
                    idiomas = idioma.selectAllIdiomas(connection);
                    generos = genero.selectAllGeneros(connection);
                    estantes = estante.selectAllEstantes(connection);
                    request.setAttribute("listaAutores", autores);
                    request.setAttribute("listaEditoriales", editoriales);
                    request.setAttribute("listaIdiomas", idiomas);
                    request.setAttribute("listaGeneros", generos);
                    request.setAttribute("listaEstantes", estantes);
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
