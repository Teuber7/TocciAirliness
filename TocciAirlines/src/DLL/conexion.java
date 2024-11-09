package DLL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/agenciaviajes";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private conexion() {} 

    public static Connection getConnection() {
        try {
            Connection conect = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión establecida con éxito.");
            return conect;
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
            return null;
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada exitosamente.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}