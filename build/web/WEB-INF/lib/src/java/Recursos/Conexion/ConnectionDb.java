package Recursos.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb implements AutoCloseable{
    private Connection connection;
    // private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/BibliotecaWeb";
    private static final String JDBC_URL = "jdbc:mysql://roundhouse.proxy.rlwy.net:20661/railway";

    // private static final String JDBC_USER = "root";
    private static final String JDBC_USER = "root";

    // private static final String JDBC_PASS = "Nelsonmandela31";
    private static final String JDBC_PASS = "2e5eaFcHddED-1h4DFahe4A6DEH545ga";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
            System.out.println("Connection successful.");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Include the library in your project.");
        } catch (SQLException e) {
            System.err.println("Connection failed. Error message: " + e.getMessage());
        }
        return connection;
    }

    public void cerrarConexion() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión.");
            e.printStackTrace();
        }
    }
    @Override
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}