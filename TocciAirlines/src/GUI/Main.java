package GUI;
import javax.swing.*;

import BLL.Administrador;
import BLL.Paquetes;
import BLL.Vuelo;
import DLL.controllerUsuario;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Crear usuarios
        Administrador admin = new Administrador("admin", "1234");
        Cliente cliente = new Cliente("cliente", "1234");

        controllerUsuario userController = new controllerUsuario(); // Controlador de usuarios

        // Inicializar vuelos y paquetes
        Vuelo[] vuelos = {
            new Vuelo("Buenos Aires", 500),
            new Vuelo("Madrid", 1200),
            new Vuelo("Nueva York", 1000)
        };

        ArrayList<Paquetes> paquetes = new ArrayList<>();
        paquetes.add(new Paquetes("Tour en Buenos Aires", null, 150));
        paquetes.add(new Paquetes("Visita al Museo del Prado", null, 200));
        paquetes.add(new Paquetes("Crucero por el río Hudson", null, 300));

       // ArrayList<Reserva> reservas = new ArrayList<>();

        // Login
        String username = JOptionPane.showInputDialog("Usuario:");
        String password = JOptionPane.showInputDialog("Contraseña:");

        if (admin.login(username, password)) {
            // Menú de administrador
            JOptionPane.showMessageDialog(null, "Bienvenido, Admin");
            
            String[] opcionAdmin = {"Crear vuelo", "Crear Paquete", "CRUD Usuarios", "Mostrar Usuarios", "Salir"};
            int opciAd = 0;
            do {
                opciAd = JOptionPane.showOptionDialog(null, "¿Qué desea realizar?", null, 0, 0, null, opcionAdmin, opcionAdmin[0]);
                switch (opciAd) {
                    case 0:
                        String origen =  JOptionPane.showInputDialog("Ingrese nuevo Origen");
                        String destino = JOptionPane.showInputDialog("Ingrese nuevo Destino");
                        // Vuelo vuelo1 = new Vuelo("AA101", "Airlander", origen, destino, "2024-09-25", 350.000);
                        break;
                    case 1:
                        admin.ver(paquetes);
                        String origen1 =  JOptionPane.showInputDialog("Ingrese nuevo Origen");
                        String destino2 = JOptionPane.showInputDialog("Ingrese nuevo Destino");
                        String actividades = JOptionPane.showInputDialog("Ingrese actividades");
                        String hotel =  JOptionPane.showInputDialog("Ingrese nuevo Hotel");
                        break;
                    case 2:
                        String[] crudOptions = {"Crear", "Leer", "Actualizar", "Eliminar"};
                        int crudAction = JOptionPane.showOptionDialog(null, "CRUD Usuarios", null, 0, 0, null, crudOptions, crudOptions[0]);
                        switch (crudAction) {
                            case 0:
                                userController.crearUsuario();
                                break;
                            case 1:
                                userController.leerUsuario();
                                break;
                            case 2:
                                userController.actualizarUsuario();
                                break;
                            case 3:
                                userController.eliminarUsuario();
                                break;
                        }
                        break;
                    case 3:
                        userController.mostrarUsuarios();
                        break;
                    case 4:
                        break;
                }
            } while (opciAd != 4);

        } else if (cliente.login(username, password)) {
            // Menú de cliente
            JOptionPane.showMessageDialog(null, "Bienvenido, Cliente");
            String[] opcionCliente = {"Comprar vuelo", "Elegir Paquete", "Salir"};
            int opciCliente = 0;
            do {
                opciCliente = JOptionPane.showOptionDialog(null, "¿Qué desea realizar?", null, 0, 0, null, opcionCliente, opcionCliente[0]);
                switch (opciCliente) {
                    case 0:
                        cliente.seleccionarDestino(vuelos);
                        break;
                    case 1:
                        cliente.elegirPaquete(paquetes);
                        break;
                    case 2:
                        break;
                }
            } while (opciCliente != 2);

        } else {
            JOptionPane.showMessageDialog(null, "Login fallido.");
        }
    }
}

