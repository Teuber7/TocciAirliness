package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaCliente extends JFrame {
    private JPanel contentPane;

    public PantallaCliente(String username) {
        setTitle("Pantalla Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Mensaje de bienvenida
        JLabel lblBienvenido = new JLabel("Bienvenido, " + username + "!");
        lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
        lblBienvenido.setFont(new Font("Arial", Font.BOLD, 18));
        lblBienvenido.setBounds(20, 10, 350, 30);
        contentPane.add(lblBienvenido);

        // Botón "Comprar Vuelo"
        JButton btnComprarVuelo = new JButton("Comprar Vuelo");
        btnComprarVuelo.setBounds(120, 70, 150, 30);
        btnComprarVuelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la pantalla de compra de vuelo
                JOptionPane.showMessageDialog(null, "Función 'Comprar Vuelo' aún no implementada.");
            }
        });
        contentPane.add(btnComprarVuelo);

        // Botón "Comprar Paquete"
        JButton btnComprarPaquete = new JButton("Comprar Paquete");
        btnComprarPaquete.setBounds(120, 120, 150, 30);
        btnComprarPaquete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la pantalla de compra de paquete
                JOptionPane.showMessageDialog(null, "Función 'Comprar Paquete' aún no implementada.");
            }
        });
        contentPane.add(btnComprarPaquete);

        // Botón "Salir"
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(120, 170, 150, 30);
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });
        contentPane.add(btnSalir);
    }

    public static void main(String[] args) {
        // Ejemplo de uso con un nombre de usuario ficticio
        EventQueue.invokeLater(() -> {
            try {
                PantallaCliente frame = new PantallaCliente("UsuarioEjemplo");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
