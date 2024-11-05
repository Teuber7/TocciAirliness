package DLL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    private static String URL = "jdbc:mysql://localhost:3306/tocci_airlines";
    private static String USER = "root";
    private static String PASSWORD = "";
    
    private static Connection conect;
    private static conexion instance;

    private conexion() {
        try {
            conect = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión establecida con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }

    public static conexion getInstance() {
        if (instance == null) {
            instance = new conexion();
        }
        return instance;    
    }

    public Connection getConection() {
        return conect;
    }
}
