package GUI;

import BLL.Usuario;
import DLL.ControllerUsuario;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class PantallaGestionUsuario extends JFrame {
    private ControllerUsuario controllerUsuario;

    public PantallaGestionUsuario() {
        controllerUsuario = new ControllerUsuario();
        initUI();
    }

    private void initUI() {
        setTitle("Gestión de Usuarios");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Crear botones de acción
        JButton btnCrear = new JButton("Crear Usuario");
        JButton btnListar = new JButton("Listar Usuarios");
        JButton btnActualizar = new JButton("Actualizar Usuario");
        JButton btnEliminar = new JButton("Eliminar Usuario");

        btnCrear.addActionListener(e -> crearUsuario());
        btnListar.addActionListener(e -> listarUsuarios());
        btnActualizar.addActionListener(e -> actualizarUsuario());
        btnEliminar.addActionListener(e -> eliminarUsuario());

        // Agregar botones al panel
        panel.add(btnCrear);
        panel.add(btnListar);
        panel.add(btnActualizar);
        panel.add(btnEliminar);

        add(panel);
    }

    // Método para crear un usuario
    private void crearUsuario() {
        String username = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del usuario:");
        String email = JOptionPane.showInputDialog("Ingrese el email del usuario:");
        String contraseña = JOptionPane.showInputDialog("Ingrese la contraseña del usuario:");
        String rol = JOptionPane.showInputDialog("Ingrese el rol del usuario (cliente/admin):");

        controllerUsuario.crearUsuario();
    }

    // Método para listar todos los usuarios
    private void listarUsuarios() {
        LinkedList<Usuario> usuarios = controllerUsuario.listarUsuarios();
        StringBuilder sb = new StringBuilder();
        for (Usuario usuario : usuarios) {
            sb.append("ID: ").append(usuario.getIdUsuario())
              .append(", Username: ").append(usuario.getUsername())
              .append(", Apellido: ").append(usuario.getApellido())
              .append(", Email: ").append(usuario.getEmail())
              .append(", Rol: ").append(usuario.getRol()).append("\n");
        }

        JOptionPane.showMessageDialog(this, sb.toString(), "Usuarios", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para actualizar un usuario
    private void actualizarUsuario() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del usuario a actualizar:"));
        String username = JOptionPane.showInputDialog("Ingrese el nuevo nombre de usuario:");
        String apellido = JOptionPane.showInputDialog("Ingrese el nuevo apellido del usuario:");
        String email = JOptionPane.showInputDialog("Ingrese el nuevo email del usuario:");
        String contraseña = JOptionPane.showInputDialog("Ingrese la nueva contraseña del usuario:");
        String rol = JOptionPane.showInputDialog("Ingrese el nuevo rol del usuario (cliente/admin):");

        controllerUsuario.actualizarUsuario(id);
    }

    // Método para eliminar un usuario
    private void eliminarUsuario() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del usuario a eliminar:"));
        controllerUsuario.eliminarUsuario(id);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PantallaGestionUsuario().setVisible(true);
        });
    }
}
