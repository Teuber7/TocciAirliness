package BLL;

import java.util.ArrayList;

import DLL.controllerPaqueteActividades;

public class Administrador extends controllerPaqueteActividades {

    private String email;
    private String password;

    public Administrador(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean login(String email, String password) {
        // Verificar si el email y la contraseña coinciden
        return this.email.equals(email) && this.password.equals(password);
    }

    public ArrayList<Paquetes> verPaquetes() {
        ArrayList<Paquetes> paquetes = verPaquetes();
        paquetes.forEach(paquete -> System.out.println(paquete));
        return paquetes;
    }

    public void agregarPaquete(Paquetes paquete) {
        agregarPaquete(paquete);
    }

    public void editarPaquete(int id, Paquetes paquete) {
        editarPaquete(id, paquete);
    }

    public void eliminarPaquete(int id) {
        eliminarPaquete(id);
    }
}
