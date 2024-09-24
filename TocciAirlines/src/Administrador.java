
import javax.swing.*;
import java.util.ArrayList;

public class Administrador implements Usuario, Gestionable<PaqueteActividades> {

    private String username;
    private String password;

    public Administrador(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public void ver(ArrayList<PaqueteActividades> paquetes) {
        StringBuilder paquetesActuales = new StringBuilder("\nPaquetes actuales:\n");
        for (int i = 0; i < paquetes.size(); i++) {
            paquetesActuales.append((i + 1)).append(". ").append(paquetes.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(null, paquetesActuales.toString());
    }

    @Override
    public void agregar(PaqueteActividades paquete) {
        JOptionPane.showMessageDialog(null, "Paquete agregado: " + paquete);
    }

    @Override
    public void editar(int index, PaqueteActividades paquete) {
        JOptionPane.showMessageDialog(null, "Paquete editado: " + paquete);
    }

    @Override
    public void eliminar(int index, ArrayList<PaqueteActividades> paquetes) {
        paquetes.remove(index);
        JOptionPane.showMessageDialog(null, "Paquete eliminado.");
    }

    public void gestionarReservas(ArrayList<Reserva> reservas) {
        StringBuilder reservasActuales = new StringBuilder("\nReservas actuales:\n");
        for (int i = 0; i < reservas.size(); i++) {
            reservasActuales.append((i + 1)).append(". ").append(reservas.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(null, reservasActuales.toString());
    }
}
