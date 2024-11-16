package GUI;

import DLL.ControllerUsuario;
import BLL.Usuario;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaLogin extends JFrame {
    private JPanel contentPane;
    private JTextField emailField;
    private JPasswordField passwordField;
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PantallaLogin frame = new PantallaLogin();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PantallaLogin() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 363, 346);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(76, 0, 260, 143); // Ajusta la posición y tamaño según sea necesario
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/Img/logo.jpg"));
        lblLogo.setIcon(new ImageIcon(logoIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH)));
        contentPane.add(lblLogo);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 147, 100, 25);
        contentPane.add(lblEmail);

        emailField = new JTextField();
        emailField.setBounds(130, 147, 150, 25);
        contentPane.add(emailField);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(50, 194, 100, 25);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(130, 194, 150, 25);
        contentPane.add(passwordField);

        JButton btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(50, 250, 100, 30);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                Usuario usuario = ControllerUsuario.login(email, password);
                if (usuario != null) {
                    JOptionPane.showMessageDialog(null, "Bienvenido, " + usuario.getUsername());
                    // Abre la siguiente ventana (puedes cambiar esto según tu lógica)
                    // Ejemplo:
                    if (usuario.getRol().equalsIgnoreCase("admin")) {
                       // new PantallaAdmin(usuario).setVisible(true);
                    } else {
                   //     new PantallaCliente(usuario).setVisible(true);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Inicio de seccion incorrecto");
                }
            }
        });
        contentPane.add(btnLogin);

        JButton btnRegister = new JButton("Registrarse");
        btnRegister.setBounds(198, 250, 120, 30);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaRegistro().setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnRegister);
    }

   
}
