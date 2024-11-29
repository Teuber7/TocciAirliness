package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PantallaAdmin extends JFrame {

    public PantallaAdmin() {
        setTitle("Panel de Administración");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JLabel lblBienvenida = new JLabel("Bienvenido, ", SwingConstants.CENTER);
        lblBienvenida.setBackground(new Color(0, 128, 128));
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 18));
        getContentPane().add(lblBienvenida, BorderLayout.NORTH);

        
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(0, 128, 128));
        panelBotones.setLayout(new GridLayout(8, 1, 10, 10));

        
        JButton btnCrudVuelo = new JButton("Vuelo");
        JButton btnCrudPaquete = new JButton("Paquete");
        JButton btnCrudUsuario = new JButton("Usuario");
        JButton btnCrudAlojamiento = new JButton("Alojamiento");
        JButton btnCrudActividad = new JButton("Actividad");
        JButton btnCambiarVuelo = new JButton("Cambiar vuelo a cliente");
        JButton btnCambiarPaquete = new JButton("Cambiar paquete a cliente");
        JButton btnSalir = new JButton("Salir");

       
        panelBotones.add(btnCrudVuelo);
        panelBotones.add(btnCrudPaquete);
        panelBotones.add(btnCrudUsuario);
        panelBotones.add(btnCrudAlojamiento);
        panelBotones.add(btnCrudActividad);
        panelBotones.add(btnCambiarVuelo);
        panelBotones.add(btnCambiarPaquete);
        panelBotones.add(btnSalir);

        getContentPane().add(panelBotones, BorderLayout.CENTER);

        
        btnCrudVuelo.addActionListener(e -> {
            
        	new PantallaGestionVuelo().setVisible(true);
            dispose();
        });

        btnCrudPaquete.addActionListener(e -> {
            
            new PantallaGestionPaquete().setVisible(true);
        
            dispose();
        });

        btnCrudUsuario.addActionListener(e -> {
            
           new PantallaGestionUsuario().setVisible(true);
            dispose();
        });

        btnCrudAlojamiento.addActionListener(e -> {
           
         new PantallaGestionAlojamiento().setVisible(true);
            dispose();
        });

        btnCrudActividad.addActionListener(e -> {
          
        new PantallaGestionActividad().setVisible(true);
            dispose();
        });

        btnCambiarVuelo.addActionListener(e -> {
            
      //     new PantallaCambiarVuelo(nombreUsuario).setVisible(true);
            dispose();
        });

        btnCambiarPaquete.addActionListener(e -> {
            
        //  new PantallaCambiarPaquete(nombreUsuario).setVisible(true);
            dispose();
        });

        btnSalir.addActionListener(e -> {
           
            dispose();
            new PantallaLogin().setVisible(true);
        });

        setLocationRelativeTo(null);
    }
}
