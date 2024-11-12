package DLL;

import BLL.Vuelo;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.LinkedList;

public class ControllerVuelo {

	 private static Connection getConnection() {
	        return conexion.getConnection();
	    }

	/* public void crearVuelo() {
		    // Información básica
		    String origen = JOptionPane.showInputDialog("Ingrese el origen del vuelo:");
		    String destino = JOptionPane.showInputDialog("Ingrese el destino del vuelo:");
		    String fechaSalida = JOptionPane.showInputDialog("Ingrese la fecha de salida del vuelo (YYYY-MM-DD):");
		    String fechaLlegada = JOptionPane.showInputDialog("Ingrese la fecha de llegada del vuelo (YYYY-MM-DD):");
		    String clase = JOptionPane.showInputDialog("Ingrese la clase del vuelo (economica/premium/primera):");
		    double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del vuelo:"));
		    
		    // Capacidad de cada clase
		    int capacidadEconomica = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad para clase económica:"));
		    int capacidadPremium = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad para clase premium:"));
		    int capacidadPrimera = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad para clase primera:"));

		    String query = "INSERT INTO vuelo (origen, destino, fecha_salida, fecha_llegada, clase, precio, capacidad_economica, capacidad_premium, capacidad_primera) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		    try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
		        stmt.setString(1, origen);
		        stmt.setString(2, destino);
		        stmt.setString(3, fechaSalida);
		        stmt.setString(4, fechaLlegada);
		        stmt.setString(5, clase);
		        stmt.setDouble(6, precio);
		        stmt.setInt(7, capacidadEconomica);
		        stmt.setInt(8, capacidadPremium);
		        stmt.setInt(9, capacidadPrimera);
		        stmt.executeUpdate();
		        JOptionPane.showMessageDialog(null, "Vuelo creado exitosamente.");
		    } catch (SQLException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Error al crear el vuelo.");
		    }
		}
*/
	 public void crearVuelo() {
		    String origen = JOptionPane.showInputDialog("Ingrese el origen del vuelo:");
		    String destino = JOptionPane.showInputDialog("Ingrese el destino del vuelo:");
		    String fechaSalida = JOptionPane.showInputDialog("Ingrese la fecha de salida del vuelo (YYYY-MM-DD):");
		    String fechaLlegada = JOptionPane.showInputDialog("Ingrese la fecha de llegada del vuelo (YYYY-MM-DD):");

		    // Precios para cada clase
		    double precioEconomica = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio para clase económica:"));
		    double precioPremium = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio para clase premium:"));
		    double precioPrimera = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio para primera clase:"));

		    // Capacidades para cada clase
		    int capacidadEconomica = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad para clase económica:"));
		    int capacidadPremium = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad para clase premium:"));
		    int capacidadPrimera = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad para primera clase:"));

		    String query = "INSERT INTO vuelo (origen, destino, fecha_salida, fecha_llegada, " +
		                   "precio_economica, precio_premium, precio_primera, capacidad_economica, capacidad_premium, capacidad_primera) " +
		                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		    try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
		        stmt.setString(1, origen);
		        stmt.setString(2, destino);
		        stmt.setString(3, fechaSalida);
		        stmt.setString(4, fechaLlegada);
		        stmt.setDouble(5, precioEconomica);
		        stmt.setDouble(6, precioPremium);
		        stmt.setDouble(7, precioPrimera);
		        stmt.setInt(8, capacidadEconomica);
		        stmt.setInt(9, capacidadPremium);
		        stmt.setInt(10, capacidadPrimera);
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
		                rs.getInt("capacidad_economica"),
		                rs.getInt("capacidad_premium"),
		                rs.getInt("capacidad_primera"),
		                rs.getDouble("precio_economica"),
		                rs.getDouble("precio_premium"),
		                rs.getDouble("precio_primera")
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

		    // Capacidad para cada clase
		    int capacidadEconomica = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva capacidad para clase económica:"));
		    int capacidadPremium = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva capacidad para clase premium:"));
		    int capacidadPrimera = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva capacidad para clase primera:"));

		    // Precio para cada clase
		    double precioEconomica = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio para clase económica:"));
		    double precioPremium = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio para clase premium:"));
		    double precioPrimera = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio para clase primera:"));

		    String query = "UPDATE vuelo SET origen = ?, destino = ?, fecha_salida = ?, fecha_llegada = ?, " +
		                   "capacidad_economica = ?, capacidad_premium = ?, capacidad_primera = ?, " +
		                   "precio_economica = ?, precio_premium = ?, precio_primera = ? WHERE id_vuelo = ?";

		    try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
		        stmt.setString(1, origen);
		        stmt.setString(2, destino);
		        stmt.setString(3, fechaSalida);
		        stmt.setString(4, fechaLlegada);
		        stmt.setInt(5, capacidadEconomica);
		        stmt.setInt(6, capacidadPremium);
		        stmt.setInt(7, capacidadPrimera);
		        stmt.setDouble(8, precioEconomica);
		        stmt.setDouble(9, precioPremium);
		        stmt.setDouble(10, precioPrimera);
		        stmt.setInt(11, id);
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
  /*  public void comprarVuelo(int idUsuario, int idVuelo) {
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
    public void comprarVuelo(int idUsuario, int idVuelo, String clase, int boletos) {
        String queryCheckCapacity = "SELECT capacidad_economica, capacidad_premium, capacidad_primera, asientos_ocupados_economica, asientos_ocupados_premium, asientos_ocupados_primera FROM vuelo WHERE id_vuelo = ?";
        String queryUpdateCapacity = null;
        int asientosDisponibles = 0;
        int asientosOcupados = 0;

        try (Connection con = getConnection(); PreparedStatement stmtCheck = con.prepareStatement(queryCheckCapacity)) {
            stmtCheck.setInt(1, idVuelo);
            ResultSet rs = stmtCheck.executeQuery();

            if (rs.next()) {
                switch (clase.toLowerCase()) {
                    case "economica":
                        asientosDisponibles = rs.getInt("capacidad_economica") - rs.getInt("asientos_ocupados_economica");
                        queryUpdateCapacity = "UPDATE vuelo SET asientos_ocupados_economica = asientos_ocupados_economica + ? WHERE id_vuelo = ?";
                        break;
                    case "premium":
                        asientosDisponibles = rs.getInt("capacidad_premium") - rs.getInt("asientos_ocupados_premium");
                        queryUpdateCapacity = "UPDATE vuelo SET asientos_ocupados_premium = asientos_ocupados_premium + ? WHERE id_vuelo = ?";
                        break;
                    case "primera":
                        asientosDisponibles = rs.getInt("capacidad_primera") - rs.getInt("asientos_ocupados_primera");
                        queryUpdateCapacity = "UPDATE vuelo SET asientos_ocupados_primera = asientos_ocupados_primera + ? WHERE id_vuelo = ?";
                        break;
                }

                if (asientosDisponibles >= boletos) {
                    try (PreparedStatement stmtUpdate = con.prepareStatement(queryUpdateCapacity)) {
                        stmtUpdate.setInt(1, boletos);
                        stmtUpdate.setInt(2, idVuelo);
                        stmtUpdate.executeUpdate();

                        String queryPurchase = "INSERT INTO usuario_vuelo (id_usuario, id_vuelo) VALUES (?, ?)";
                        try (PreparedStatement stmtPurchase = con.prepareStatement(queryPurchase)) {
                            stmtPurchase.setInt(1, idUsuario);
                            stmtPurchase.setInt(2, idVuelo);
                            stmtPurchase.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Compra de vuelo registrada exitosamente.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No hay suficientes asientos disponibles en esta clase.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al registrar la compra de vuelo.");
        }
    }
*/
    
    public void comprarVuelo(int idUsuario, int idVuelo, String clase, int boletos) {
        String verificarCapacidadQuery = "";
        String actualizarCapacidadQuery = "";
        
        // Determina la columna de capacidad y la consulta de verificación y actualización
        switch (clase.toLowerCase()) {
            case "economica":
         
                verificarCapacidadQuery = "SELECT capacidad_economica FROM vuelo WHERE id_vuelo = ?";
                actualizarCapacidadQuery = "UPDATE vuelo SET capacidad_economica = capacidad_economica - ? WHERE id_vuelo = ?";
                break;
            case "premium":
                verificarCapacidadQuery = "SELECT capacidad_premium FROM vuelo WHERE id_vuelo = ?";
                actualizarCapacidadQuery = "UPDATE vuelo SET capacidad_premium = capacidad_premium - ? WHERE id_vuelo = ?";
                break;
            case "primera":
                verificarCapacidadQuery = "SELECT capacidad_primera FROM vuelo WHERE id_vuelo = ?";
                actualizarCapacidadQuery = "UPDATE vuelo SET capacidad_primera = capacidad_primera - ? WHERE id_vuelo = ?";
                break;
            default:
                JOptionPane.showMessageDialog(null, "Clase no válida. Intente nuevamente.");
                return;
        }
        
        try (Connection con = getConnection()) {
            // Verificar la capacidad disponible
            try (PreparedStatement verificarStmt = con.prepareStatement(verificarCapacidadQuery)) {
                verificarStmt.setInt(1, idVuelo);
                ResultSet rs = verificarStmt.executeQuery();
                
                if (rs.next()) {
                    int capacidadDisponible = rs.getInt(1);
                    if (capacidadDisponible < boletos) {
                        JOptionPane.showMessageDialog(null, "No hay suficientes boletos disponibles en esta clase.");
                        return;
                    }
                }
            }

            // Registrar la compra en la tabla usuario_vuelo
            String compraQuery = "INSERT INTO usuario_vuelo (id_usuario, id_vuelo, clase, boletos) VALUES (?, ?, ?, ?)";
            try (PreparedStatement compraStmt = con.prepareStatement(compraQuery)) {
                compraStmt.setInt(1, idUsuario);
                compraStmt.setInt(2, idVuelo);
                compraStmt.setString(3, clase);
                compraStmt.setInt(4, boletos);
                compraStmt.executeUpdate();
            }

            // Actualizar la capacidad en la tabla vuelo
            try (PreparedStatement actualizarStmt = con.prepareStatement(actualizarCapacidadQuery)) {
                actualizarStmt.setInt(1, boletos);
                actualizarStmt.setInt(2, idVuelo);
                actualizarStmt.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Compra realizada con éxito.");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al realizar la compra.");
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
