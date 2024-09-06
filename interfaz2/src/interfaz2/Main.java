package interfaz2;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        String[] opciones = { "Teléfono", "Contraseña", "Email", "Salir"};
        int seleccion = JOptionPane.showOptionDialog(null, "Seleccione lo que desea validar",
                "Menú", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        switch (seleccion) {
            case 0: 
                Telefono tel = new Telefono("Usuario", 0);
                tel.setTel(0);
                break;
            case 1: 
                Contraseña pass = new Contraseña("Usuario", "");
                pass.setContraseña("");
                break;
            case 2: 
                Email email = new Email("Usuario", "");
                email.setEmail("");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Gracias");
        }
    }
}
