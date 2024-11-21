package GUI;

import DLL.ControllerUsuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class PantallaRegistro extends JFrame {
    private JPanel contentPane;
    private JTextField nameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JPasswordField passwordField;

    public PantallaRegistro() {
        setTitle("Registro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 401, 470);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
       
        
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(89, -6, 260, 143); // Ajusta la posición y tamaño según sea necesario
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/Img/logo.jpg"));
        lblLogo.setIcon(new ImageIcon(logoIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH)));
        contentPane.add(lblLogo);

        JLabel lblName = new JLabel("Nombre:");
        lblName.setBounds(50, 148, 100, 25);
        contentPane.add(lblName);

        nameField = new JTextField();
        nameField.setBounds(150, 148, 150, 25);
        contentPane.add(nameField);

        JLabel lblLastName = new JLabel("Apellido:");
        lblLastName.setBounds(50, 195, 100, 25);
        contentPane.add(lblLastName);

        lastNameField = new JTextField();
        lastNameField.setBounds(150, 195, 150, 25);
        contentPane.add(lastNameField);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 246, 100, 25);
        contentPane.add(lblEmail);

        emailField = new JTextField();
        emailField.setBounds(150, 246, 150, 25);
        contentPane.add(emailField);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(50, 299, 100, 25);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 299, 150, 25);
        contentPane.add(passwordField);

        JButton btnRegister = new JButton("Registrar");
        btnRegister.setForeground(new Color(0, 0, 0));
        btnRegister.setBackground(new Color(255, 255, 255));
        btnRegister.setBounds(100, 352, 150, 30);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nameField.getText();
                String apellido = lastNameField.getText();
                String email = emailField.getText();
                String contraseña = new String(passwordField.getPassword());

                ControllerUsuario.crearUsuario(nombre, apellido, email, contraseña);
                //JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.");
                new PantallaExito().setVisible(true);
               new PantallaLogin().setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnRegister);
    }
}

