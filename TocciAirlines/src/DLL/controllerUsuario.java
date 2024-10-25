package DLL;

import javax.swing.JOptionPane;

import BLL.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class controllerUsuario {

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/tocci_airlines", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.");
            return null;
        }
    }

    public void crearUsuario() {

        String username = JOptionPane.showInputDialog("Ingrese nombre de usuario:");
        String password = JOptionPane.showInputDialog("Ingrese contraseña:");
        String rol = JOptionPane.showInputDialog("Ingrese el rol del usuario:");

        String query = "INSERT INTO `usuario`(`nombre`, `apellido`, `email`, `contraseña`, ) VALUES "
        		+ "(?,?,?,?)";
        
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
             
            stmt.setString(1, username);
            stmt.setString(2, rol);
            stmt.setString(3, password);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        JOptionPane.showMessageDialog(null, "Usuario creado exitosamente. ID: " + generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al crear el usuario.");
        }
    }

    public void leerUsuario() {

        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del usuario a buscar:"));
        String query = "SELECT * FROM persona WHERE id = ?";
        
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
             
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                JOptionPane.showMessageDialog(null, "Usuario encontrado: " + nombre);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al buscar el usuario.");
        }
    }

    public void actualizarUsuario() {

        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del usuario a actualizar:"));
        String query = "SELECT * FROM persona WHERE id = ?";
        
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
             
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String nuevoUsername = JOptionPane.showInputDialog("Ingrese nuevo nombre de usuario:", rs.getString("nombre"));
                String nuevoRol = JOptionPane.showInputDialog("Ingrese nuevo rol del usuario:", rs.getString("rol"));
                String nuevaPassword = JOptionPane.showInputDialog("Ingrese nueva contraseña:", rs.getString("password"));
                
                String updateQuery = "UPDATE persona SET nombre = ?, rol = ?, password = ? WHERE id = ?";
                
                try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
                    updateStmt.setString(1, nuevoUsername);
                    updateStmt.setString(2, nuevoRol);
                    updateStmt.setString(3, nuevaPassword);
                    updateStmt.setInt(4, id);
                    
                    int rowsAffected = updateStmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar el usuario.");
        }
    }

    public void eliminarUsuario() {

        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del usuario a eliminar:"));
        String query = "DELETE FROM persona WHERE id = ?";
        
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
             
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar el usuario.");
        }
    }

    public static LinkedList<Usuario> mostrarUsuarios() {
    	 LinkedList<Usuario> usuarios= new LinkedList<Usuario> ();
     

        String query = "SELECT * FROM usuario";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
            	usuarios.add(new Usuario(
            			rs.getString("nombre"),
            			rs.getString("contraseña") 
            			) 
            			);
            }
            return usuarios;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al mostrar los usuarios.");
            return null;
        }
    }

     //Verificación de acceso para administrador
    
}

