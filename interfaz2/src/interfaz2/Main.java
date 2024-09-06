package interfaz2;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
    	String[] opciones = { "Teléfono", "Contraseña", "Email", "Salir"};
    	
    	int seleccion1;
		do {
			seleccion1 = JOptionPane.showOptionDialog(null, "Seleccione lo que desea validar",
	    			"Menú", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        switch (seleccion1) {
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
            case 3:
            	 JOptionPane.showMessageDialog(null, "Gracias");
            	break;
            
        }
        } while (seleccion1!=3);
    }

}