package DLL;

import BLL.Actividad;
import java.sql.*;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class ControllerActividad {
    private static Connection getConnection() {
        return conexion.getConnection();
    }

    public void crearActividad() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la actividad:");
        String descripcion = JOptionPane.showInputDialog("Ingrese una descripción de la actividad:");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio de la actividad:"));

        String query = "INSERT INTO actividad (nombre, descripcion, precio) VALUES (?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.setDouble(3, precio);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Actividad creada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al crear la actividad.");
        }
    }

    public LinkedList<Actividad> obtenerActividades() {
        LinkedList<Actividad> actividades = new LinkedList<>();
        String query = "SELECT * FROM actividad";

        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("idActividad");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");

                actividades.add(new Actividad(id, nombre, descripcion, precio));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener las actividades.");
        }
        return actividades;
    }

    public void actualizarActividad() {
        int idActividad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la actividad a actualizar:"));
        String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");
        String nuevaDescripcion = JOptionPane.showInputDialog("Ingrese la nueva descripción:");
        double nuevoPrecio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio:"));

        String query = "UPDATE actividad SET nombre = ?, descripcion = ?, precio = ? WHERE idActividad = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, nuevoNombre);
            stmt.setString(2, nuevaDescripcion);
            stmt.setDouble(3, nuevoPrecio);
            stmt.setInt(4, idActividad);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Actividad actualizada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una actividad con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar la actividad.");
        }
    }

    public void eliminarActividad() {
        int idActividad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la actividad a eliminar:"));

        String query = "DELETE FROM actividad WHERE idActividad = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, idActividad);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Actividad eliminada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una actividad con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar la actividad.");
        }
    }
}
