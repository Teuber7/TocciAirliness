import javax.swing.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Crear usuarios
        Administrador admin = new Administrador("admin", "1234");
        Cliente cliente = new Cliente("cliente", "1234");

        // Inicializar vuelos y paquetes
        Vuelo[] vuelos = {
            new Vuelo("Buenos Aires", 500),
            new Vuelo("Madrid", 1200),
            new Vuelo("Nueva York", 1000)
        };

        ArrayList<PaqueteActividades> paquetes = new ArrayList<>();
        paquetes.add(new PaqueteActividades("Tour en Buenos Aires", 150));
        paquetes.add(new PaqueteActividades("Visita al Museo del Prado", 200));
        paquetes.add(new PaqueteActividades("Crucero por el río Hudson", 300));

        ArrayList<Reserva> reservas = new ArrayList<>();

        // Login
        String username = JOptionPane.showInputDialog("Usuario:");
        String password = JOptionPane.showInputDialog("Contraseña:");

        if (admin.login(username, password)) {
            // Menú de administrador
            JOptionPane.showMessageDialog(null, "Bienvenido, Admin");
            
            String [] opcionAdmin = {"Crear vuelo", "Crear Paquete", "salir" };
            int opciAd = 0;
            do {
            	opciAd	= JOptionPane.showOptionDialog(null, "Que desea realizar ", null, 0, 0, null, opcionAdmin, opcionAdmin[0]);
			switch (opciAd) {
			case 0:
				String origen =  JOptionPane.showInputDialog("ingrese nuevo Origen");
				String destino = JOptionPane.showInputDialog("ingrese nuevo Destino");
				
			//	Vuelo vuelo1 = new Vuelo("AA101", "Airlander", origen, destino, "2024-09-25", 350.000);
				
			
				break;

                case 1:
                	admin.ver(paquetes);
                	String origen1 =  JOptionPane.showInputDialog("ingrese nuevo Origen");
    				String destino2 = JOptionPane.showInputDialog("ingrese nuevo Destino");
    				String actividades = JOptionPane.showInputDialog("ingrese actividades");
    			    String hotel = 	JOptionPane.showInputDialog("ingrese nuevo Hotel");
				
				break;

                case 2:
				break;
	
			}
			} while (opciAd!=2);
           //admin.ver(paquetes);  // Ver paquetes
            // Otras opciones de administración

        } else if (cliente.login(username, password)) {
            // Menú de cliente
            JOptionPane.showMessageDialog(null, "Bienvenido, Cliente");
            String [] opcionCliente = {"Comprar vuelo", "Elegir Paquete", "salir" };
            int opciCliente = 0;
            do {
            	opciCliente	= JOptionPane.showOptionDialog(null, "Que desea realizar ", null, 0, 0, null, opcionCliente, opcionCliente[0]);
			switch (opciCliente) {
			case 0:
				 cliente.seleccionarDestino(vuelos);
				String origen =  JOptionPane.showInputDialog("ingrese Origen");
				String destino = JOptionPane.showInputDialog("ingrese Destino");
				
			//	Vuelo vuelo1 = new Vuelo("AA101", "Airlander", origen, destino, "2024-09-25", 350.000);
				
			
				break;

                case 1:
                	cliente.elegirPaquete(paquetes);
				
				break;

                case 2:
				break;
	
			}
			} while (opciCliente!=2);
           // cliente.seleccionarDestino(vuelos);
            //cliente.elegirPaquete(paquetes);
            //cliente.realizarReserva(vuelos[0], paquetes.get(0), reservas);
        } else {
            JOptionPane.showMessageDialog(null, "Login fallido.");
        }
    }
}

