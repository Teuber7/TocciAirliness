package DLL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/agenciaviajes";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection conect;

    private conexion() {}

    public static Connection getConnection() {
        if (conect == null) {
            try {
                conect = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión establecida con éxito.");
            } catch (SQLException e) {
                System.out.println("Error al conectar: " + e.getMessage());
            }
        }
        return conect;
    }

    public static void closeConnection() {
        if (conect != null) {
            try {
                conect.close();
                System.out.println("Conexión cerrada exitosamente.");
                conect = null;
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
