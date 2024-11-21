package GUI;

import javax.swing.*;
import java.awt.*;

public class PantallaActividad extends JFrame {

    public PantallaActividad(String nombreUsuario) {
        setTitle("Gestión de Actividades");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

       
        JLabel lblBienvenida = new JLabel("Bienvenido a Gestión de Actividades, " + nombreUsuario, SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblBienvenida, BorderLayout.NORTH);

        
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBackground(new Color(0, 128, 128)); 
        add(panelCentral, BorderLayout.CENTER);

        
        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBoton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBoton.setBackground(new Color(0, 128, 128)); 
        JButton btnVolver = new JButton("Volver");

        btnVolver.addActionListener(e -> {
            new PantallaAdmin(nombreUsuario).setVisible(true);
            dispose();
        });

        panelBoton.add(btnVolver);
        add(panelBoton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }
}
