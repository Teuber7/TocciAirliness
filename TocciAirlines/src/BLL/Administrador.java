package BLL;

import java.util.ArrayList;
import java.util.LinkedList;

import DLL.controllerPaqueteActividades;
import DLL.controllerUsuario;

public class Administrador {

    private String username;
    private String password;

    public Administrador(String email, String password) {
        this.username = email;
        this.password = password;
    }

    public boolean login(String email, String password) {
        // Verificar si el email y la contraseña coinciden
        return this.username.equals(email) && this.password.equals(password);
    }
    public static Usuario Logi2n(String nombre , String password) {
		LinkedList<Usuario> usuarios = controllerUsuario.mostrarUsuarios();
		for (Usuario persona : usuarios) {
			if (persona.getUsername().equals(nombre) && persona.getPassword().equals(password)) {
				
				return persona;
			} 
		}
		return null;
		
	}
    public ArrayList<Paquetes> verPaquetes() {
        ArrayList<Paquetes> paquetes = verPaquetes();
        paquetes.forEach(paquete -> System.out.println(paquete));
        return paquetes;
    }

    public void agregarPaquete(Paquetes paquete) {
        //agregarPaquete(paquete);
        controllerPaqueteActividades.crearPaquete();
    }

    public void editarPaquete(int id, Paquetes paquete) {
        //editarPaquete(id, paquete);
    	controllerPaqueteActividades.actualizarPaquete();
    }

    public void eliminarPaquete(int id) {
        eliminarPaquete(id);
    }
}
