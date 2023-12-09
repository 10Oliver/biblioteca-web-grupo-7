package sv.edu.udb.www.Recursos.Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.udb.www.Recursos.Models.StatusResponseIntern;


@WebServlet("/another")  // URL mapping for the new servlet
public class AnotherServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Your logic to handle GET requests for the new servlet
        // response.getWriter().write("This is another servlet!");

        StatusResponseIntern simpleResponse = new StatusResponseIntern("Success",200);




        // Convert the response object to a JSON-like string
        String jsonResponse = "{\"statusCode\":" + simpleResponse.getCode() + ", \"message\":\"" + simpleResponse.getMessage() + "\"}";


        // Set the content type to indicate JSON-like response
        response.setContentType("application/json");

        // Write the JSON-like data to the response
        response.getWriter().write(jsonResponse);
    }
}