package GUI;

import DLL.ControllerPaquete; // Asegúrate de tener este controlador
import BLL.Paquete;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;

public class PantallaGestionPaquete extends JFrame {
    private ControllerPaquete paqueteController;
    private DefaultTableModel tableModel;

    public PantallaGestionPaquete() {
        setTitle("Gestión de Paquetes");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        paqueteController = new ControllerPaquete();

        // Panel superior con botones CRUD
        JPanel panelSuperior = new JPanel();
        JButton btnCrear = new JButton("Crear Paquete");
        JButton btnActualizar = new JButton("Actualizar Paquete");
        JButton btnEliminar = new JButton("Eliminar Paquete");
        JButton btnCargar = new JButton("Cargar Paquetes");

        panelSuperior.add(btnCrear);
        panelSuperior.add(btnActualizar);
        panelSuperior.add(btnEliminar);
        panelSuperior.add(btnCargar);

        getContentPane().add(panelSuperior, BorderLayout.NORTH);

        // Tabla para mostrar los paquetes
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("ID Vuelo");
        tableModel.addColumn("ID Actividad");
        tableModel.addColumn("ID Alojamiento");
        tableModel.addColumn("Precio Total");

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Acciones de los botones
        btnCrear.addActionListener(e -> {
            paqueteController.crearPaquete();
            cargarPaquetes(); // Refresca la tabla
        });

        btnActualizar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int idPaquete = (int) tableModel.getValueAt(selectedRow, 0);
                paqueteController.actualizarPaquete(idPaquete); // Actualiza el paquete
                cargarPaquetes(); // Refresca la tabla
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un paquete para actualizar.");
            }
        });

        btnEliminar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int idPaquete = (int) tableModel.getValueAt(selectedRow, 0);
                paqueteController.eliminarPaquete(idPaquete); // Elimina el paquete
                cargarPaquetes(); // Refresca la tabla
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un paquete para eliminar.");
            }
        });

        btnCargar.addActionListener(e -> cargarPaquetes());

        setLocationRelativeTo(null);
        cargarPaquetes(); // Carga inicial de paquetes
    }

    private void cargarPaquetes() {
        tableModel.setRowCount(0); // Limpia la tabla
        LinkedList<Paquete> paquetes = paqueteController.listarPaquetes();

        for (Paquete paquete : paquetes) {
            tableModel.addRow(new Object[]{
                    paquete.getIdPaquete(),
                    paquete.getIdVuelo(),
                    paquete.getIdActividad(),
                    paquete.getIdAlojamiento(),
                    paquete.getPrecioTotal()
            });
        }
    }
}
