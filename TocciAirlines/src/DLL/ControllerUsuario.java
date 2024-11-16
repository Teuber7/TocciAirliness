package DLL;

import BLL.Usuario;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.LinkedList;

public class ControllerUsuario {

    private static Connection getConnection() {
        return conexion.getConnection();
    }

    // Método para iniciar sesión
    public static Usuario login(String email, String password) {
        String query = "SELECT * FROM usuario WHERE email = ? AND contraseña = ?";
        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email"),
                    rs.getString("contraseña"),
                    rs.getString("rol")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para buscar usuario por email
    public Usuario buscarUsuarioPorEmail(String email) {
        String query = "SELECT * FROM usuario WHERE email = ?";
        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("contraseña"),
                        rs.getString("rol")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al buscar usuario por email.");
        }
        return null;
    }

    // Método para crear un usuario
    public void crearUsuario() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del usuario:");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del usuario:");
        String email = JOptionPane.showInputDialog("Ingrese el email del usuario:");
        String contraseña = JOptionPane.showInputDialog("Ingrese la contraseña del usuario:");
        String rol = JOptionPane.showInputDialog("Ingrese el rol del usuario (cliente/admin):");

        String query = "INSERT INTO usuario (nombre, apellido, email, contraseña, rol) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, email);
            stmt.setString(4, contraseña);
            stmt.setString(5, rol);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario creado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al crear el usuario.");
        }
    }

    // Método para listar todos los usuarios
    public LinkedList<Usuario> listarUsuarios() {
        LinkedList<Usuario> usuarios = new LinkedList<>();
        String query = "SELECT * FROM usuario";

        try (Connection con = getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email"),
                    rs.getString("contraseña"),
                    rs.getString("rol")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al listar usuarios.");
        }
        return usuarios;
    }

    // Método para actualizar un usuario
    public void actualizarUsuario(int id) {
        String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del usuario:");
        String apellido = JOptionPane.showInputDialog("Ingrese el nuevo apellido del usuario:");
        String email = JOptionPane.showInputDialog("Ingrese el nuevo email del usuario:");
        String contraseña = JOptionPane.showInputDialog("Ingrese la nueva contraseña del usuario:");
        String rol = JOptionPane.showInputDialog("Ingrese el nuevo rol del usuario (cliente/admin):");

        String query = "UPDATE usuario SET nombre = ?, apellido = ?, email = ?, contraseña = ?, rol = ? WHERE id_usuario = ?";

        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, email);
            stmt.setString(4, contraseña);
            stmt.setString(5, rol);
            stmt.setInt(6, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar el usuario.");
        }
    }

    // Método para eliminar un usuario
    public void eliminarUsuario(int id) {
        String query = "DELETE FROM usuario WHERE id_usuario = ?";

        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar el usuario.");
        }
    }

    // Menú para opciones CRUD de usuario
    public static void menuUsuarioCRUD() {
        String[] options = {"Crear Usuario", "Listar Usuarios", "Actualizar Usuario", "Eliminar Usuario", "Salir"};
        int option;
        ControllerUsuario controller = new ControllerUsuario();
        do {
            option = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Gestión de Usuarios",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (option) {
                case 0 -> controller.crearUsuario();
                case 1 -> {
                    LinkedList<Usuario> usuarios = controller.listarUsuarios();
                    StringBuilder userList = new StringBuilder("Usuarios:\n");
                    for (Usuario usuario : usuarios) {
                        userList.append(usuario).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, userList.toString());
                }
                case 2 -> {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del usuario a actualizar:"));
                    controller.actualizarUsuario(id);
                }
                case 3 -> {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del usuario a eliminar:"));
                    controller.eliminarUsuario(id);
                }
            }
        } while (option != 4);
    }
 // ControllerUsuario.java
    public void asignarVueloACliente(int idUsuario, int idVuelo) {
        String query = "UPDATE usuario_vuelo SET id_vuelo = ? WHERE id_usuario = ?";

        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, idVuelo);
            stmt.setInt(2, idUsuario);
            int filasActualizadas = stmt.executeUpdate();

            if (filasActualizadas == 0) {
                // Si no hay registro previo, insertar uno nuevo
                query = "INSERT INTO usuario_vuelo (id_usuario, id_vuelo) VALUES (?, ?)";
                try (PreparedStatement stmtInsert = con.prepareStatement(query)) {
                    stmtInsert.setInt(1, idUsuario);
                    stmtInsert.setInt(2, idVuelo);
                    stmtInsert.executeUpdate();
                }
            }

            JOptionPane.showMessageDialog(null, "Vuelo asignado o actualizado correctamente para el cliente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al asignar o actualizar el vuelo para el cliente.");
        }
    }

    public void asignarPaqueteACliente(int idUsuario, int idPaquete) {
        String query = "UPDATE usuario_paquete SET id_paquete = ? WHERE id_usuario = ?";

        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, idPaquete);
            stmt.setInt(2, idUsuario);
            int filasActualizadas = stmt.executeUpdate();

            if (filasActualizadas == 0) {
                query = "INSERT INTO usuario_paquete (id_usuario, id_paquete) VALUES (?, ?)";
                try (PreparedStatement stmtInsert = con.prepareStatement(query)) {
                    stmtInsert.setInt(1, idUsuario);
                    stmtInsert.setInt(2, idPaquete);
                    stmtInsert.executeUpdate();
                }
            }

            JOptionPane.showMessageDialog(null, "Paquete asignado o actualizado correctamente para el cliente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al asignar o actualizar el paquete para el cliente.");
        }
    }
    public static void crearUsuario(String nombre, String apellido, String email, String contraseña) {
        String query = "INSERT INTO usuario (nombre, apellido, email, contraseña, rol) VALUES (?, ?, ?, ?, 'cliente')";

        try (Connection con = conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, email);
            stmt.setString(4, contraseña);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }


}
