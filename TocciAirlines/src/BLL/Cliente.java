package BLL;

import java.util.ArrayList;

public class Cliente {

    private String username;
    private String password;

    public Cliente(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    public void seleccionarDestino(Vuelo[] vuelos) {
        for (int i = 0; i < vuelos.length; i++) {
            System.out.println((i + 1) + ". " + vuelos[i].getDestino() + " - $" + vuelos[i].getPrecio());
        }
        // Aquí puedes implementar la lógica de selección
    }

    public void elegirPaquete(ArrayList<Paquetes> paquetes) {
        for (int i = 0; i < paquetes.size(); i++) {
            System.out.println((i + 1) + ". " + paquetes.get(i));
        }
        // Aquí puedes implementar la lógica de selección de paquetes
    }
}

