import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		
		LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
		
		usuarios.add(new Usuario("Pitaev","1234","admin"));
		usuarios.add(new Usuario("Flor","4321","encar"));
		String mail = JOptionPane.showInputDialog("Ingrese mail");
		String cont = JOptionPane.showInputDialog("Ingrese contraseña");
		
		Usuario econtrado = Usuario.Login(usuarios, mail, cont) ;
		
		if(econtrado!=null) {
			JOptionPane.showMessageDialog(null, econtrado);
			if (econtrado.getRol().equals("admin")) {
				Admin nuevo = new Admin(econtrado.getMail(),econtrado.getContrasena(),econtrado.getRol());
				nuevo.menuPrincipal();
			
			
		}else {
			JOptionPane.showMessageDialog(null, "No existe");

		}
		
		}
	}
	}


