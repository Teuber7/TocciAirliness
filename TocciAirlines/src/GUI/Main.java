package GUI;

import DLL.ControllerVuelo;
import DLL.ControllerPaquete;
import DLL.ControllerUsuario;
import DLL.ControllerAlojamiento;
import DLL.ControllerActividad;
import BLL.Usuario;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        ControllerVuelo controllerVuelo = new ControllerVuelo();
        ControllerPaquete controllerPaquete = new ControllerPaquete();
        ControllerUsuario controllerUsuario = new ControllerUsuario();
        ControllerAlojamiento controllerAlojamiento = new ControllerAlojamiento();
        ControllerActividad controllerActividad = new ControllerActividad();

        Usuario usuario = login(controllerUsuario);
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Inicio de sesión fallido. Saliendo del sistema.");
            return;
        }

        if ("admin".equals(usuario.getRol())) {
            mostrarMenuAdmin(controllerVuelo, controllerPaquete, controllerUsuario, controllerAlojamiento, controllerActividad);
        } else {
            mostrarMenuCliente(controllerVuelo, controllerPaquete, usuario.getIdUsuario());
        }
    }

    private static Usuario login(ControllerUsuario controllerUsuario) {
        String email = JOptionPane.showInputDialog("Ingrese su email:");
        String password = JOptionPane.showInputDialog("Ingrese su contraseña:");
        return controllerUsuario.login(email, password);
    }

    private static void mostrarMenuAdmin(ControllerVuelo controllerVuelo, ControllerPaquete controllerPaquete,
                                         ControllerUsuario controllerUsuario, ControllerAlojamiento controllerAlojamiento,
                                         ControllerActividad controllerActividad) {
    	String[] opciones = { "CRUD Vuelo", "CRUD Paquete", "CRUD Usuario", "CRUD Alojamiento", "CRUD Actividad", "Salir" };
    	int option;
        do {
          /*  option = Integer.parseInt(JOptionPane.showInputDialog(
                "Menú principal (Admin):\n" +
                "1. CRUD Vuelo\n" +
                "2. CRUD Paquete\n" +
                "3. CRUD Usuario\n" +
                "4. CRUD Alojamiento\n" +
                "5. CRUD Actividad\n" +
                "6. Salir\n" +
                "Seleccione una opción:"
            ));*/
        	
        	option = JOptionPane.showOptionDialog(null, "Elija una opción", "Menù de Admin",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
            switch (option) {
                case 0:
                    menuVuelo(controllerVuelo);
                    break;
                case 1:
                    menuPaquete(controllerPaquete);
                    break;
                case 2:
                    menuUsuario(controllerUsuario);
                    break;
                case 3:
                    menuAlojamiento(controllerAlojamiento);
                    break;
                case 4:
                    menuActividad(controllerActividad);
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (option != 5);
    }

    private static void mostrarMenuCliente(ControllerVuelo controllerVuelo, ControllerPaquete controllerPaquete, int idUsuario) {
    	String[] opciones = { "Comprar Vuelo", " Comprar Paquet", "Salir"};

    	int option;
        do {
        	option = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Menù de cliente",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
           /* option = Integer.parseInt(JOptionPane.showInputDialog(
                "Menú principal (Cliente):\n" +
                "1. Comprar Vuelo\n" +
                "2. Comprar Paquete\n" +
                "3. Salir\n" +
                "Seleccione una opción:"
            ));*/

            switch (option) {
                case 0:               
                	 String origen = JOptionPane.showInputDialog("Ingrese el origen del vuelo:");
                     String destino = JOptionPane.showInputDialog("Ingrese el destino del vuelo:");
                     controllerVuelo.buscarVuelosPorOrigenDestino(origen, destino);
                     int idVuelo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del vuelo que desea comprar:"));
                     controllerVuelo.comprarVuelo(idUsuario, idVuelo);
                    break;
                case 1:
                    int idPaquete = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del paquete que desea comprar:"));
                    controllerPaquete.comprarPaquete(idUsuario, idPaquete);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (option != 2);
    }

    public static void menuVuelo(ControllerVuelo controllerVuelo) {
    	String[] opciones = { "Crear Vuelo", "Listar Vuelos", "Actualizar Vuelo", "Eliminar Vuelo", "Volver al Menú principal"};

    	int option;
        do {
           /* option = Integer.parseInt(JOptionPane.showInputDialog(
                "CRUD Vuelo:\n" +
                "1. Crear Vuelo\n" +
                "2. Listar Vuelos\n" +
                "3. Actualizar Vuelo\n" +
                "4. Eliminar Vuelo\n" +
                "5. Volver al Menú principal\n" +
                "Seleccione una opción:"
            ));*/
        	option = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Menù de Admin",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (option) {
                case 0:
                    controllerVuelo.crearVuelo();
                    break;
                case 1:
                    mostrarLista(controllerVuelo.listarVuelos(), "Lista de Vuelos");
                    break;
                case 2:
                    int idVueloActualizar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del vuelo a actualizar:"));
                    controllerVuelo.actualizarVuelo(idVueloActualizar);
                    break;
                case 3:
                    int idVueloEliminar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del vuelo a eliminar:"));
                    controllerVuelo.eliminarVuelo(idVueloEliminar);
                    break;
                case 4:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (option != 4);
    }

    public static void menuPaquete(ControllerPaquete controllerPaquete) {
    	String[] opciones = { "Crear Paquete", "Listar Paquetes", "Actualizar Paquete", "Eliminar Paquete", "Volver al Menú principal", "Salir" };

    	int option;
        do {
        	option = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Menù de Admin",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);


        	/*option = Integer.parseInt(JOptionPane.showInputDialog(
                "CRUD Paquete:\n" +
                "1. Crear Paquete\n" +
                "2. Listar Paquetes\n" +
                "3. Actualizar Paquete\n" +
                "4. Eliminar Paquete\n" +
                "5. Volver al Menú principal\n" +
                "Seleccione una opción:"
            ));*/

            switch (option) {
                case 0:
                    controllerPaquete.crearPaquete();
                    break;
                case 1:
                    mostrarLista(controllerPaquete.listarPaquetes(), "Lista de Paquetes");
                    break;
                case 2:
                    int idPaqueteActualizar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del paquete a actualizar:"));
                    controllerPaquete.actualizarPaquete(idPaqueteActualizar);
                    break;
                case 3:
                    int idPaqueteEliminar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del paquete a eliminar:"));
                    controllerPaquete.eliminarPaquete(idPaqueteEliminar);
                    break;
                case 4:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (option != 4);
    }

    public static void menuUsuario(ControllerUsuario controllerUsuario) {
    	String[] opciones = { "Crear Usuario", "Listar Usuarios", "Actualizar Usuario", "Eliminar Usuario", "Volver al Menú principal" };

    	int option;
        do {
        	option = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Menù de Admin",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

           /* option = Integer.parseInt(JOptionPane.showInputDialog(
                "CRUD Usuario:\n" +
                "1. Crear Usuario\n" +
                "2. Listar Usuarios\n" +
                "3. Actualizar Usuario\n" +
                "4. Eliminar Usuario\n" +
                "5. Volver al Menú principal\n" +
                "Seleccione una opción:"
            ));*/

            switch (option) {
                case 0:
                    controllerUsuario.crearUsuario();
                    break;
                case 1:
                    mostrarLista(controllerUsuario.listarUsuarios(), "Lista de Usuarios");
                    break;
                case 2:
                    int idUsuarioActualizar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del usuario a actualizar:"));
                    controllerUsuario.actualizarUsuario(idUsuarioActualizar);
                    break;
                case 3:
                    int idUsuarioEliminar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del usuario a eliminar:"));
                    controllerUsuario.eliminarUsuario(idUsuarioEliminar);
                    break;
                case 4:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (option != 4);
    }

    public static void menuAlojamiento(ControllerAlojamiento controllerAlojamiento) {
    	String[] opciones = { "Crear Alojamiento", "Listar Alojamientos", "Actualizar Alojamiento", "Eliminar Alojamiento", "Volver al Menú principal" };

    	int option;
        do {
        	option = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Menù de Admin",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        	/*option = Integer.parseInt(JOptionPane.showInputDialog(
                "CRUD Alojamiento:\n" +
                "1. Crear Alojamiento\n" +
                "2. Listar Alojamientos\n" +
                "3. Actualizar Alojamiento\n" +
                "4. Eliminar Alojamiento\n" +
                "5. Volver al Menú principal\n" +
                "Seleccione una opción:"
            ));*/

            switch (option) {
                case 0:
                    controllerAlojamiento.crearAlojamiento();
                    break;
                case 1:
                    mostrarLista(controllerAlojamiento.listarAlojamientos(), "Lista de Alojamientos");
                    break;
                case 2:
                    int idAlojamientoActualizar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del alojamiento a actualizar:"));
                    controllerAlojamiento.actualizarAlojamiento(idAlojamientoActualizar);
                    break;
                case 3:
                    int idAlojamientoEliminar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del alojamiento a eliminar:"));
                    controllerAlojamiento.eliminarAlojamiento(idAlojamientoEliminar);
                    break;
                case 4:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (option != 4);
    }

    public static void menuActividad(ControllerActividad controllerActividad) {
    	String[] opciones = { "Crear Actividad", "Listar Actividades", "Actualizar Actividad", "Eliminar Actividad", "Volver al Menú principal" };

    	int option;
        do {
        	option = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Menù de Admin",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        /*    option = Integer.parseInt(JOptionPane.showInputDialog(
                "CRUD Actividad:\n" +
                "1. Crear Actividad\n" +
                "2. Listar Actividades\n" +
                "3. Actualizar Actividad\n" +
                "4. Eliminar Actividad\n" +
                "5. Volver al Menú principal\n" +
                "Seleccione una opción:"
            ));*/

            switch (option) {
                case 0:
                    controllerActividad.crearActividad();
                    break;
                case 1:
                	mostrarLista(controllerActividad.obtenerActividades(), "Lista de Actividades");
                    break;
                case 2:
               //     int idActividadActualizar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la actividad a actualizar:"));
                    controllerActividad.actualizarActividad();
                    break;
                case 3:
                    //int idActividadEliminar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la actividad a eliminar:"));
                    controllerActividad.eliminarActividad();
                    break;
                case 4:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (option != 4);
    }

    public static <T> void mostrarLista(Iterable<T> lista, String titulo) {
        StringBuilder sb = new StringBuilder(titulo + ":\n");
        for (T item : lista) {
            sb.append(item.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
