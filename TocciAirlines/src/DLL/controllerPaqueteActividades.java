package DLL;

import javax.swing.*;
import java.util.ArrayList;
import BLL.Paquetes;

public class controllerPaqueteActividades {
    private ArrayList<Paquetes> paquetes;

    public void controllerPaquetes() {
        this.paquetes = new ArrayList<>();
    }

    // CRUD Paquetes de Actividades
    public void crearPaquete() {
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripción del paquete:");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del paquete:"));
        Paquetes nuevoPaquete = new Paquetes(descripcion, descripcion, precio);
        paquetes.add(nuevoPaquete);
        JOptionPane.showMessageDialog(null, "Paquete creado exitosamente.");
    }

    public void leerPaquete() {
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripción del paquete a buscar:");
        for (Paquetes paquete : paquetes) {
            if (paquete.getDescripcion().equalsIgnoreCase(descripcion)) {
                JOptionPane.showMessageDialog(null, "Paquete encontrado: " + paquete.toString());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Paquete no encontrado.");
    }

    public void actualizarPaquete() {
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripción del paquete a actualizar:");
        for (Paquetes paquete : paquetes) {
            if (paquete.getDescripcion().equalsIgnoreCase(descripcion)) {
                String nuevaDescripcion = JOptionPane.showInputDialog("Ingrese nueva descripción del paquete:");
                double nuevoPrecio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese nuevo precio del paquete:"));
                paquete.setDescripcion(nuevaDescripcion);
                paquete.setPrecio(nuevoPrecio);
                JOptionPane.showMessageDialog(null, "Paquete actualizado exitosamente.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Paquete no encontrado.");
    }

    public void eliminarPaquete() {
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripción del paquete a eliminar:");
        for (Paquetes paquete : paquetes) {
            if (paquete.getDescripcion().equalsIgnoreCase(descripcion)) {
                paquetes.remove(paquete);
                JOptionPane.showMessageDialog(null, "Paquete eliminado exitosamente.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Paquete no encontrado.");
    }

    // Método para mostrar todos los paquetes
    public void mostrarPaquetes() {
        StringBuilder listaPaquetes = new StringBuilder("Paquetes disponibles:\n");
        for (Paquetes paquete : paquetes) {
            listaPaquetes.append(paquete.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, listaPaquetes.toString());
    }

    // Método para seleccionar paquete por parte del cliente
    public void seleccionarPaqueteCliente() {
        if (paquetes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay paquetes disponibles.");
            return;
        }
        StringBuilder listaPaquetes = new StringBuilder("Seleccione un paquete:\n");
        for (int i = 0; i < paquetes.size(); i++) {
            listaPaquetes.append(i).append(": ").append(paquetes.get(i).toString()).append("\n");
        }
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(listaPaquetes.toString() + "\nSeleccione el número del paquete:"));
        if (opcion >= 0 && opcion < paquetes.size()) {
            Paquetes seleccionado = paquetes.get(opcion);
            JOptionPane.showMessageDialog(null, "Paquete seleccionado: " + seleccionado.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Selección inválida.");
        }
    }
}

