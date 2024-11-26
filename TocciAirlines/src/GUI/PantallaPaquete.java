package GUI;

import DLL.ControllerPaquete;
import BLL.Paquete;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;

public class PantallaPaquete extends JFrame {
    private JTable tablaPaquetes;
    private DefaultTableModel model;
    private ControllerPaquete paqueteController;

    public PantallaPaquete(String nombreUsuario) {
        paqueteController = new ControllerPaquete();

        setTitle("Gestión de Paquetes");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Etiqueta superior
        JLabel lblBienvenida = new JLabel("Gestión de Paquetes", SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        getContentPane().add(lblBienvenida, BorderLayout.NORTH);

        // Panel central para la tabla
        JPanel panelCentral = new JPanel(new BorderLayout());
        getContentPane().add(panelCentral, BorderLayout.CENTER);

        // Configurar tabla
        model = new DefaultTableModel(new Object[]{
                "ID", "ID Vuelo", "ID Actividad", "ID Alojamiento", "Precio Total", "Eliminar"
        }, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 5) return Boolean.class; // Checkbox para eliminar
                return Object.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 5; // No editable ID y checkbox
            }
        };

        tablaPaquetes = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tablaPaquetes);
        panelCentral.add(scrollPane, BorderLayout.CENTER);

        cargarPaquetes();

        
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        getContentPane().add(panelInferior, BorderLayout.SOUTH);

       
        JButton btnCrear = new JButton("Crear Paquete");
        btnCrear.addActionListener(e -> crearPaquete());
        panelInferior.add(btnCrear);

       
        JButton btnActualizar = new JButton("Actualizar Paquete");
        btnActualizar.addActionListener(e -> actualizarPaquete());
        panelInferior.add(btnActualizar);

        
        JButton btnEliminar = new JButton("Eliminar Paquete");
        btnEliminar.addActionListener(e -> eliminarPaquetes());
        panelInferior.add(btnEliminar);

        // Botón Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            new PantallaAdmin(nombreUsuario).setVisible(true);
            dispose();
        });
        panelInferior.add(btnVolver);

        setLocationRelativeTo(null);
    }

  
    private void cargarPaquetes() {
        model.setRowCount(0); // Limpiar tabla
        LinkedList<Paquete> paquetes = paqueteController.listarPaquetes();
        for (Paquete paquete : paquetes) {
            model.addRow(new Object[]{
                    paquete.getIdPaquete(), paquete.getIdVuelo(), paquete.getIdActividad(),
                    paquete.getIdAlojamiento(), paquete.getPrecioTotal(),
                    false // Checkbox de eliminación
            });
        }
    }

 
    private void crearPaquete() {
        try {
            int idVuelo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del vuelo:"));
            int idActividad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la actividad:"));
            int idAlojamiento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del alojamiento:"));
            double precioTotal = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio total del paquete:"));

            paqueteController.crearPaquete(idVuelo, idActividad, idAlojamiento, precioTotal);
            cargarPaquetes();
            JOptionPane.showMessageDialog(this, "Paquete creado exitosamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al crear el paquete.");
        }
    }

    // Actualizar paquetes
    private void actualizarPaquete() {
        try {
            for (int i = 0; i < model.getRowCount(); i++) {
                int idPaquete = (int) model.getValueAt(i, 0);
                int idVuelo = (int) model.getValueAt(i, 1);
                int idActividad = (int) model.getValueAt(i, 2);
                int idAlojamiento = (int) model.getValueAt(i, 3);
                double precioTotal = Double.parseDouble(model.getValueAt(i, 4).toString());

                paqueteController.actualizarPaquete(idPaquete, idVuelo, idActividad, idAlojamiento, precioTotal);
            }
            JOptionPane.showMessageDialog(this, "Paquetes actualizados exitosamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar los paquetes.");
        }
    }

    // Eliminar paquetes seleccionados
    private void eliminarPaquetes() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea eliminar los paquetes seleccionados?",
                "Confirmación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                boolean eliminar = (boolean) model.getValueAt(i, 5);
                if (eliminar) {
                    int idPaquete = (int) model.getValueAt(i, 0);
                    paqueteController.eliminarPaquete(idPaquete);
                    model.removeRow(i);
                }
            }
            JOptionPane.showMessageDialog(this, "Paquetes eliminados exitosamente.");
        }
    }
}

