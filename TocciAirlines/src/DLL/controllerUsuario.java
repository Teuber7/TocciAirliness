package DLL;

import javax.swing.*;
import java.util.ArrayList;
import BLL.Usuario;
import BLL.Administrador;

public class controllerUsuario {
    private ArrayList<Usuario> usuarios;
    private Administrador admin;

    public controllerUsuario() {
        this.usuarios = new ArrayList<>();
        this.admin = new Administrador("admin", "1234"); // Inicializar el administrador
    }

    // CRUD
    public void crearUsuario() {
        if (!verificarAcceso()) return;

        String username = JOptionPane.showInputDialog("Ingrese nombre de usuario:");
        String password = JOptionPane.showInputDialog("Ingrese contraseña:");
        Usuario nuevoUsuario = new Usuario();
        usuarios.add(nuevoUsuario);
        JOptionPane.showMessageDialog(null, "Usuario creado exitosamente.");
    }

    public void leerUsuario() {
        if (!verificarAcceso()) return;

        String username = JOptionPane.showInputDialog("Ingrese el nombre del usuario a buscar:");
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                JOptionPane.showMessageDialog(null, "Usuario encontrado: " + usuario.getUsername());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
    }

    public void actualizarUsuario() {
        if (!verificarAcceso()) return;

        String username = JOptionPane.showInputDialog("Ingrese el nombre del usuario a actualizar:");
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                String nuevoUsername = JOptionPane.showInputDialog("Ingrese nuevo nombre de usuario:");
                String nuevaPassword = JOptionPane.showInputDialog("Ingrese nueva contraseña:");
                usuario.setUsername(nuevoUsername);
                usuario.setPassword(nuevaPassword);
                JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
    }

    public void eliminarUsuario() {
        if (!verificarAcceso()) return;

        String username = JOptionPane.showInputDialog("Ingrese el nombre del usuario a eliminar:");
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                usuarios.remove(usuario);
                JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
    }

    public void mostrarUsuarios() {
        if (!verificarAcceso()) return;

        StringBuilder listaUsuarios = new StringBuilder("Usuarios:\n");
        for (Usuario usuario : usuarios) {
            listaUsuarios.append(usuario.getUsername()).append("\n");
        }
        JOptionPane.showMessageDialog(null, listaUsuarios.toString());
    }

    // Verificación de acceso para administrador
    private boolean verificarAcceso() {
        String username = JOptionPane.showInputDialog("Usuario (Admin):");
        String password = JOptionPane.showInputDialog("Contraseña (Admin):");

        if (admin.login(username, password)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Acceso denegado. Solo el administrador puede realizar esta acción.");
            return false;
        }
    }
}
