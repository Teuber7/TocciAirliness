package GUI;

import javax.swing.*;
import java.awt.*;

public class PantallaVuelo extends JFrame {

    public PantallaVuelo(String nombreUsuario) {
        setTitle("Gestión de Vuelos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        
        JLabel lblBienvenida = new JLabel("Bienvenido a Gestión de Vuelos, " + nombreUsuario, SwingConstants.CENTER);
        lblBienvenida.setBackground(new Color(0, 128, 128));
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 16));
        getContentPane().add(lblBienvenida, BorderLayout.NORTH);

       
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(new Color(0, 128, 128));
        panelCentral.setLayout(new BorderLayout());
        getContentPane().add(panelCentral, BorderLayout.CENTER);

        
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

        setLocationRelativeTo(null);
    }
}
