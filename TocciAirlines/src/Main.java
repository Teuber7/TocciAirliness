import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		
		
		
		
		//Login 
		
		
		//Admin 
		String[] opcionGeneral = {
				"Vuelos","Paquetes","Actividades","Seguros","Salir"
		};
		String[] opcionesVuelo  = {"Crear vuelo","Administrar vuelos","Ver vuelos","Eliminar","Salir"};
		
		String[] opcionesPaquetes  = {"Paquetes","Salir"};

		
		
		
		int elegido = JOptionPane.showOptionDialog(null, "Elija una opción", null, 0, 0, null, opcionGeneral, opcionGeneral[0]);
		switch (elegido) {
		//Vuelos
		case 0: 
			elegido = JOptionPane.showOptionDialog(null, "Elija que desea realizar en los vuelos", null, 0, 0, null, opcionesVuelo, opcionesVuelo[0]);
			
			
			//Hago otro switch 
			break;
		//Paquetes
		case 1: 
			elegido = JOptionPane.showOptionDialog(null, "Elija que desea realizar en los vuelos", null, 0, 0, null, opcionesPaquetes, opcionesPaquetes[0]);
			break;
		}
		
		
		
		
//		LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
//		
//		usuarios.add(new Usuario("Pitaev","1234","admin"));
//		usuarios.add(new Usuario("Flor","4321","encar"));
//		String mail = JOptionPane.showInputDialog("Ingrese mail");
//		String cont = JOptionPane.showInputDialog("Ingrese contraseña");
//		
//		Usuario econtrado = Usuario.Login(usuarios, mail, cont) ;
//		
//		if(econtrado!=null) {
//			JOptionPane.showMessageDialog(null, econtrado);
//			if (econtrado.getRol().equals("admin")) {
//				Admin nuevo = new Admin(econtrado.getMail(),econtrado.getContrasena(),econtrado.getRol());
//				nuevo.menuPrincipal();
//			
//			
//		}else {
//			JOptionPane.showMessageDialog(null, "No existe");
//
//		}
//		
//		}
	}
	}


