package GUI;

import javax.swing.*;
import java.awt.*;

public class PantallaCliente extends JFrame {

    public PantallaCliente(String nombreUsuario, int idUsuario) {
        setTitle("Panel Cliente");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Mensaje de bienvenida
        JLabel lblBienvenida = new JLabel("Bienvenido, " + nombreUsuario, SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 18));
        lblBienvenida.setBackground(new Color(0, 128, 128));
        lblBienvenida.setOpaque(true);
        lblBienvenida.setForeground(Color.WHITE);
        getContentPane().add(lblBienvenida, BorderLayout.NORTH);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(0, 128, 128));
        panelBotones.setLayout(new GridLayout(3, 1, 10, 10));

        // Botón "Comprar Vuelo"
        JButton btnComprarVuelo = new JButton("Comprar Vuelo");
        btnComprarVuelo.setFont(new Font("Arial", Font.PLAIN, 12)); // Fuente más pequeña
        btnComprarVuelo.setPreferredSize(new Dimension(150, 30)); // Botón más estrecho
        btnComprarVuelo.setMaximumSize(new Dimension(150, 30));  // Limita el tamaño máximo del botón
        btnComprarVuelo.addActionListener(e -> {
            // Abrir la pantalla de compra de vuelo
            new PantallaCompra(idUsuario).setVisible(true); // Ajustado para pasar idUsuario
            dispose();
        });
        panelBotones.add(btnComprarVuelo);

        // Botón "Comprar Paquete"
        JButton btnComprarPaquete = new JButton("Comprar Paquete");
        btnComprarPaquete.setFont(new Font("Arial", Font.PLAIN, 12)); // Fuente más pequeña
        btnComprarPaquete.setPreferredSize(new Dimension(150, 30)); // Botón más estrecho
        btnComprarPaquete.setMaximumSize(new Dimension(150, 30)); // Limita el tamaño máximo del botón
        btnComprarPaquete.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Función 'Comprar Paquete' aún no implementada.");
        });
        panelBotones.add(btnComprarPaquete);

        // Botón "Salir"
        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 12)); // Fuente más pequeña
        btnSalir.setPreferredSize(new Dimension(150, 30)); // Botón más estrecho
        btnSalir.setMaximumSize(new Dimension(150, 30));  // Limita el tamaño máximo del botón
        btnSalir.addActionListener(e -> {
            dispose();
            new PantallaLogin().setVisible(true);
        });
        panelBotones.add(btnSalir);

        getContentPane().add(panelBotones, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }
}
