package DLL;

import BLL.Vuelo;
import java.sql.*;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class ControllerVuelo {

	 private static Connection getConnection() {
	        return conexion.getConnection();
	    }

    public void crearVuelo() {
        String origen = JOptionPane.showInputDialog("Ingrese el origen del vuelo:");
        String destino = JOptionPane.showInputDialog("Ingrese el destino del vuelo:");
        String fechaSalida = JOptionPane.showInputDialog("Ingrese la fecha de salida del vuelo (YYYY-MM-DD):");
        String fechaLlegada = JOptionPane.showInputDialog("Ingrese la fecha de llegada del vuelo (YYYY-MM-DD):");
        String clase = JOptionPane.showInputDialog("Ingrese la clase del vuelo (economica/premium/primera):");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del vuelo:"));

        String query = "INSERT INTO vuelo (origen, destino, fecha_salida, fecha_llegada, clase, precio) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, origen);
            stmt.setString(2, destino);
            stmt.setString(3, fechaSalida);
            stmt.setString(4, fechaLlegada);
            stmt.setString(5, clase);
            stmt.setDouble(6, precio);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Vuelo creado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al crear el vuelo.");
        }
    }

    public LinkedList<Vuelo> listarVuelos() {
        LinkedList<Vuelo> vuelos = new LinkedList<>();
        String query = "SELECT * FROM vuelo";

        try (Connection con = getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Vuelo vuelo = new Vuelo(
                    rs.getInt("id_vuelo"),
                    rs.getString("origen"),
                    rs.getString("destino"),
                    rs.getString("fecha_salida"),
                    rs.getString("fecha_llegada"),
                    rs.getString("clase"),
                    rs.getDouble("precio")
                );
                vuelos.add(vuelo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al listar vuelos.");
        }
        return vuelos;
    }

    public void actualizarVuelo(int id) {
        String origen = JOptionPane.showInputDialog("Ingrese el nuevo origen del vuelo:");
        String destino = JOptionPane.showInputDialog("Ingrese el nuevo destino del vuelo:");
        String fechaSalida = JOptionPane.showInputDialog("Ingrese la nueva fecha de salida del vuelo (YYYY-MM-DD):");
        String fechaLlegada = JOptionPane.showInputDialog("Ingrese la nueva fecha de llegada del vuelo (YYYY-MM-DD):");
        String clase = JOptionPane.showInputDialog("Ingrese la nueva clase del vuelo (economica/premium/primera):");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio del vuelo:"));

        String query = "UPDATE vuelo SET origen = ?, destino = ?, fecha_salida = ?, fecha_llegada = ?, clase = ?, precio = ? WHERE id_vuelo = ?";

        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, origen);
            stmt.setString(2, destino);
            stmt.setString(3, fechaSalida);
            stmt.setString(4, fechaLlegada);
            stmt.setString(5, clase);
            stmt.setDouble(6, precio);
            stmt.setInt(7, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Vuelo actualizado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar el vuelo.");
        }
    }

    public void eliminarVuelo(int id) {
        String query = "DELETE FROM vuelo WHERE id_vuelo = ?";

        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Vuelo eliminado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar el vuelo.");
        }
    }
    public void comprarVuelo(int idUsuario, int idVuelo) {
        String query = "INSERT INTO usuario_vuelo (id_usuario, id_vuelo) VALUES (?, ?)";
        
        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idVuelo);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Compra de vuelo registrada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al registrar la compra de vuelo.");
        }
    }
   public void buscarVuelosPorOrigenDestino(String origen, String destino) {
        String query = "SELECT * FROM vuelo WHERE origen = ? AND destino = ?";
        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, origen);
            stmt.setString(2, destino);

            ResultSet rs = stmt.executeQuery();
            StringBuilder resultado = new StringBuilder("Vuelos encontrados:\n");

            while (rs.next()) {
                resultado.append("ID: ").append(rs.getInt("id_Vuelo"))
                        .append(", Origen: ").append(rs.getString("origen"))
                        .append(", Destino: ").append(rs.getString("destino"))
                        .append(", Fecha de Salida: ").append(rs.getString("fecha_salida"))
                        .append(", Fecha de Llegada: ").append(rs.getString("fecha_llegada"))
                        .append(", Clase: ").append(rs.getString("clase"))
                        .append(", Precio: ").append(rs.getDouble("precio"))
                        .append("\n");
            }

            if (resultado.length() > "Vuelos encontrados:\n".length()) {
                JOptionPane.showMessageDialog(null, resultado.toString());
                
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron vuelos con los criterios especificados.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al buscar vuelos.");
        }
    }
   

   

}

