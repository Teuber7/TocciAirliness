package GUI;

import javax.swing.*;
import java.awt.*;

public class PantallaVuelo extends JFrame {

    public PantallaVuelo(String nombreUsuario) {
        setTitle("Gestión de Vuelos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Etiqueta de bienvenida
        JLabel lblBienvenida = new JLabel("Bienvenido a Gestión de Vuelos, " + nombreUsuario, SwingConstants.CENTER);
        lblBienvenida.setBackground(new Color(0, 128, 128));
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 16));
        getContentPane().add(lblBienvenida, BorderLayout.NORTH);

        // Panel central con botones
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(new Color(0, 128, 128));
        panelCentral.setLayout(new GridLayout(4, 1, 10, 10)); // 4 filas, 1 columna, separación entre botones
        getContentPane().add(panelCentral, BorderLayout.CENTER);

        // Botones
        JButton btnCrearVuelo = new JButton("Crear Vuelo");
        JButton btnListarVuelos = new JButton("Listar Vuelos");
        JButton btnActualizarVuelo = new JButton("Actualizar Vuelo");
        JButton btnEliminarVuelo = new JButton("Eliminar Vuelo");

        // Agregar botones al panel
        panelCentral.add(btnCrearVuelo);
        panelCentral.add(btnListarVuelos);
        panelCentral.add(btnActualizarVuelo);
        panelCentral.add(btnEliminarVuelo);

        // Panel inferior con botón de volver
        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(0, 128, 128));
        panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBoton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton btnVolver = new JButton("Volver");

        btnVolver.addActionListener(e -> {
            new PantallaAdmin(nombreUsuario).setVisible(true);
            dispose();
        });

        panelBoton.add(btnVolver);
        getContentPane().add(panelBoton, BorderLayout.SOUTH);

        // Listeners para los botones
        btnCrearVuelo.addActionListener(e -> {
            new PantallaCrearVuelo(nombreUsuario).setVisible(true);
            dispose();
        });

        btnListarVuelos.addActionListener(e -> {
            new PantallaListarVuelos(nombreUsuario).setVisible(true);
            dispose();
        });

        btnActualizarVuelo.addActionListener(e -> {
            new PantallaActualizarVuelo(nombreUsuario).setVisible(true);
            dispose();
        });

        btnEliminarVuelo.addActionListener(e -> {
            new PantallaEliminarVuelo(nombreUsuario).setVisible(true);
            dispose();
        });

        setLocationRelativeTo(null);
    }
}
