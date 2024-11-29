package DLL;

import BLL.Vuelo;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.LinkedList;

public class ControllerVuelo {

	 private static Connection getConnection() {
	        return conexion.getConnection();
	    }

   /* public void crearVuelo(String origen, String destino, String fechaSalida, String fechaLlegada,String clase ,double precio) {
    //    String origen = JOptionPane.showInputDialog("Ingrese el origen del vuelo:");
      //   String destino = JOptionPane.showInputDialog("Ingrese el destino del vuelo:");
        //String fechaSalida = JOptionPane.showInputDialog("Ingrese la fecha de salida del vuelo (YYYY-MM-DD):");
        //String fechaLlegada = JOptionPane.showInputDialog("Ingrese la fecha de llegada del vuelo (YYYY-MM-DD):");
        //String clase = JOptionPane.showInputDialog("Ingrese la clase del vuelo (economica/premium/primera):");
        //double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del vuelo:"));

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
*/
	 public void crearVuelo(String origen, String destino, String fechaSalida, String fechaLlegada, String clase, double precio) {
		    String sql = "INSERT INTO vuelo (origen, destino, fecha_salida, fecha_llegada, clase, precio) VALUES (?, ?, ?, ?, ?, ?)";
		    try (Connection conn = getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        pstmt.setString(1, origen);
		        pstmt.setString(2, destino);
		        pstmt.setString(3, fechaSalida);
		        pstmt.setString(4, fechaLlegada);
		        pstmt.setString(5, clase); // Clase fija
		        pstmt.setDouble(6, precio);
		        pstmt.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
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
    public LinkedList<Vuelo> buscarVuelos(String origen, String destino) {
        LinkedList<Vuelo> vuelos = new LinkedList<>();
        String query = "SELECT * FROM vuelo WHERE origen = ? AND destino = ?";

        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, origen);
            stmt.setString(2, destino);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vuelo vuelo = new Vuelo(
                        rs.getInt("id_vuelo"),
                        rs.getString("origen"),
                        rs.getString("destino"),
                        rs.getString("fecha_salida"),
                        rs.getString("fecha_llegada"),
                        query, rs.getDouble("precio")
                    );
                    vuelos.add(vuelo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al buscar vuelos.");
        }
        return vuelos;
    }
    public void comprarVuelo(int idUsuario, int idVuelo) {
        String query = "INSERT INTO usuario_vuelo (id_usuario, id_vuelo) VALUES (?, ?)";

        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idVuelo);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Vuelo comprado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al comprar el vuelo.");
        }
    }


    
    public int buscarYComprarVuelo(int idUsuario, String origen, String destino) {
        // Buscar vuelos por origen y destino
        LinkedList<Vuelo> vuelos = buscarVuelos(origen, destino);

        if (vuelos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron vuelos para el origen y destino especificados.");
            return -1; // No hay vuelos disponibles
        }

        // Crear un array con las opciones para mostrar al usuario
        String[] opciones = new String[vuelos.size()];
        for (int i = 0; i < vuelos.size(); i++) {
            Vuelo vuelo = vuelos.get(i);
            opciones[i] = "ID: " + vuelo.getIdVuelo() + 
                          ", Precio: $" + vuelo.getPrecio() + 
                          ", Salida: " + vuelo.getFechaSalida() + 
                          ", Llegada: " + vuelo.getFechaLlegada();
        }

        // Mostrar vuelos disponibles al usuario y permitir la selección
        String seleccion = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione un vuelo para comprar:",
                "Compra de Vuelo",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        // Si el usuario seleccionó un vuelo
        if (seleccion != null) {
            int indexSeleccionado = java.util.Arrays.asList(opciones).indexOf(seleccion);
            int idVuelo = vuelos.get(indexSeleccionado).getIdVuelo();

            // Confirmar y registrar la compra
            comprarVuelo(idUsuario, idVuelo);
            return idVuelo; // Retorna el ID del vuelo comprado
        }

        // Si el usuario cancela la selección
        return -1; // Compra cancelada
    }


}


