package interfaz2;

import javax.swing.JOptionPane;

public interface Validaciones {
	boolean ingresartel (int tel);
	
	default int validaTel (String mensaje) {
		String tel; 
		boolean flag;
		do { 
			flag = true;
			tel = JOptionPane.showInputDialog(mensaje);
			while (tel.isEmpty()) {
				tel = JOptionPane.showInputDialog("Error, no puede estar vacio" + mensaje);
				
			}
			for (int i = 0; i < tel.length(); i++) {
				if (!Character.isDigit(tel.charAt(i))) {
					JOptionPane.showMessageDialog(null, "Error, no es un numero");
					flag = false;
					break;
				}
			}
			if (tel.length() != 10) {
                JOptionPane.showMessageDialog(null, "Error, el número debe tener 10 dígitos");
                flag = false;
            }
		}while (!flag) ;
			JOptionPane.showMessageDialog(null,"Su numero es: " + tel);
			return Integer.parseInt(tel);
		}
	
	
	 default String validaContrasena(String mensaje) {
	        String contraseña;
	        boolean flag;
	        do {
	            flag = true;
	            contraseña = JOptionPane.showInputDialog(mensaje);
	            while (contraseña.isEmpty()) {
	                contraseña = JOptionPane.showInputDialog("Error, la contraseña no puede estar vacía. " + mensaje);
	            }

	            if (contraseña.length() < 8) {
	                JOptionPane.showMessageDialog(null, "Error, la contraseña debe tener al menos 8 caracteres.");
	                flag = false;
	            } else if (!contraseña.matches(".*[A-Z].*")) {
	                JOptionPane.showMessageDialog(null, "Error, la contraseña debe contener al menos una letra mayúscula.");
	                flag = false;
	            } else if (!contraseña.matches(".*\\d.*")) {
	                JOptionPane.showMessageDialog(null, "Error, la contraseña debe contener al menos un número.");
	                flag = false;
	            }
	        } while (!flag);

	        JOptionPane.showMessageDialog(null, "Contraseña válida: " + contraseña);
	        return contraseña;
	    }

	   
	    default String validaEmail(String mensaje) {
	        String email;
	        boolean flag;
	        do {
	            flag = true;
	            email = JOptionPane.showInputDialog(mensaje);
	            while (email.isEmpty()) {
	                email = JOptionPane.showInputDialog("Error, el email no puede estar vacío. " + mensaje);
	            }
	            if (!email.contains("@") || !email.contains(".com")) {
	                JOptionPane.showMessageDialog(null, "Error, el email debe contener '@' y terminar en '.com'.");
	                flag = false;
	            }
	        } while (!flag);

	        JOptionPane.showMessageDialog(null, "Email válido: " + email);
	        return email;
	    }
	}
	
	
