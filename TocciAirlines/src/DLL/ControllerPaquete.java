package DLL;

import BLL.Paquete;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.LinkedList;

public class ControllerPaquete {

	 private static Connection getConnection() {
	        return conexion.getConnection();
	    }

    public static void crearPaquete() {
        int idVuelo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del vuelo:"));
        int idActividad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la actividad:"));
        int idAlojamiento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del alojamiento:"));
        double precioTotal = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio total del paquete:"));

        String query = "INSERT INTO paquete (id_vuelo, id_actividad, id_alojamiento, precio_total) VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, idVuelo);
            stmt.setInt(2, idActividad);
            stmt.setInt(3, idAlojamiento);
            stmt.setDouble(4, precioTotal);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Paquete creado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al crear el paquete.");
        }
    }

    public static LinkedList<Paquete> listarPaquetes() {
        LinkedList<Paquete> paquetes = new LinkedList<>();
        String query = "SELECT * FROM paquete";

        try (Connection con = getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Paquete paquete = new Paquete(
                    rs.getInt("id_paquete"),
                    rs.getInt("id_vuelo"),
                    rs.getInt("id_actividad"),
                    rs.getInt("id_alojamiento"),
                    rs.getDouble("precio_total")
                );
                paquetes.add(paquete);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al listar paquetes.");
        }
        return paquetes;
    }

    public static void actualizarPaquete(int id) {
        int idVuelo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo ID del vuelo:"));
        int idActividad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo ID de la actividad:"));
        int idAlojamiento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo ID del alojamiento:"));
        double precioTotal = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio total del paquete:"));

        String query = "UPDATE paquete SET id_vuelo = ?, id_actividad = ?, id_alojamiento = ?, precio_total = ? WHERE id_paquete = ?";

        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, idVuelo);
            stmt.setInt(2, idActividad);
            stmt.setInt(3, idAlojamiento);
            stmt.setDouble(4, precioTotal);
            stmt.setInt(5, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Paquete actualizado.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar el paquete.");
        }
    }

    public static void eliminarPaquete(int id) {
        String query = "DELETE FROM paquete WHERE id_paquete = ?";

        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Paquete eliminado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar el paquete.");
        }
    }
    public void comprarPaquete(int idUsuario, int idPaquete) {
        String query = "INSERT INTO usuario_paquete (id_usuario, id_paquete) VALUES (?, ?)";
        
        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idPaquete);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Compra de paquete registrada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al registrar la compra de paquete.");
        }
    }
}
