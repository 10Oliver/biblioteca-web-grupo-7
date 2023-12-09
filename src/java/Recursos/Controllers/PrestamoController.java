package Recursos.Controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Recursos.Conexion.ConnectionDb;
import Recursos.Models.Utils.Prestamo;

@WebServlet("/PrestamoController")

public class PrestamoController extends HttpServlet {
    ConnectionDb connection = new ConnectionDb();
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET request, e.g., retrieve loan information
        String loanId = request.getParameter("loanId");
        // Instantiate Prestamo and retrieve loan information
        Prestamo prestamo = new Prestamo();
        // Call method to get loan details based on loanId
        List<Prestamo> loanDetails = prestamo.selectAllPrestamosById(connection);
        // Set the loan details in the request attribute
        String jsonLoanDetails = convertLoanDetailsToJson(loanDetails);

        // Set content type to JSON
        response.setContentType("application/json");

        // Write JSON to the response
        response.getWriter().write(jsonLoanDetails);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST request, e.g., create a new loan
        String userId = request.getParameter("userId");
        String bookCode = request.getParameter("bookCode");
        // Instantiate Prestamo and create a new loan
        Prestamo prestamo = new Prestamo();
        // Call method to create a new loan
        prestamo.crearPrestamo(connection, userId, bookCode, Integer.parseInt(userId));

        // Return a JSON response indicating success
        String jsonResponse = "{\"status\": \"success\", \"message\": \"Loan created successfully\"}";

        // Set content type to JSON
        response.setContentType("application/json");

        // Write JSON to the response
        response.getWriter().write(jsonResponse);
    }

    private String convertLoanDetailsToJson(List<Prestamo> loanDetails) {
        // Convert loan details to JSON using a library like Gson or Jackson
        // For simplicity, using a basic approach here
        StringBuilder jsonBuilder = new StringBuilder("[");
        for (Prestamo loan : loanDetails) {
            jsonBuilder.append(loan.toJson()).append(",");
        }
        if (loanDetails.size() > 0) {
            jsonBuilder.deleteCharAt(jsonBuilder.length() - 1); // Remove the trailing comma
        }
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }
}
