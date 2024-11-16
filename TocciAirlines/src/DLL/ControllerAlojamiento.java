package DLL;

import BLL.Alojamiento;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.LinkedList;

public class ControllerAlojamiento {

	 private static Connection getConnection() {
	        return conexion.getConnection();
	    }

    public void crearAlojamiento() {
        String ubicacion = JOptionPane.showInputDialog("Ingrese la ubicación del alojamiento:");
        String tipo = JOptionPane.showInputDialog("Ingrese el tipo de alojamiento:");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del alojamiento:"));
        int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad del alojamiento:"));

        String query = "INSERT INTO alojamiento (ubicacion, tipo, precio, capacidad) VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, ubicacion);
            stmt.setString(2, tipo);
            stmt.setDouble(3, precio);
            stmt.setInt(4, capacidad);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Alojamiento creado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al crear el alojamiento.");
        }
    }

    public LinkedList<Alojamiento> listarAlojamientos() {
        LinkedList<Alojamiento> alojamientos = new LinkedList<>();
        String query = "SELECT * FROM alojamiento";

        try (Connection con = getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Alojamiento alojamiento = new Alojamiento(
                    rs.getInt("id_alojamiento"),
                    rs.getString("ubicacion"),
                    rs.getString("tipo"),
                    rs.getDouble("precio"),
                    rs.getInt("capacidad")
                );
                alojamientos.add(alojamiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al listar alojamientos.");
        }
        return alojamientos;
    }

    public void actualizarAlojamiento(int id) {
        String ubicacion = JOptionPane.showInputDialog("Ingrese la nueva ubicación del alojamiento:");
        String tipo = JOptionPane.showInputDialog("Ingrese el nuevo tipo de alojamiento:");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio del alojamiento:"));
        int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva capacidad del alojamiento:"));

        String query = "UPDATE alojamiento SET ubicacion = ?, tipo = ?, precio = ?, capacidad = ? WHERE id_alojamiento = ?";

        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, ubicacion);
            stmt.setString(2, tipo);
            stmt.setDouble(3, precio);
            stmt.setInt(4, capacidad);
            stmt.setInt(5, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Alojamiento actualizado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar el alojamiento.");
        }
    }

    public void eliminarAlojamiento(int id) {
        String query = "DELETE FROM alojamiento WHERE id_alojamiento = ?";

        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Alojamiento eliminado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar el alojamiento.");
        }
    }
    }
	
    

