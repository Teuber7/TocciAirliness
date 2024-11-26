package GUI;

import DLL.ControllerAlojamiento;
import BLL.Alojamiento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;

public class PantallaGestionAlojamientos extends JFrame {
    private JTable tablaAlojamientos;
    private DefaultTableModel model;
    private ControllerAlojamiento alojamientoController;

    public PantallaGestionAlojamientos(String nombreUsuario) {
        alojamientoController = new ControllerAlojamiento();

        setTitle("Gestión de Alojamientos");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Etiqueta superior
        JLabel lblBienvenida = new JLabel("Gestión de Alojamientos", SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        getContentPane().add(lblBienvenida, BorderLayout.NORTH);

        // Panel central para la tabla
        JPanel panelCentral = new JPanel(new BorderLayout());
        getContentPane().add(panelCentral, BorderLayout.CENTER);

        // Configurar tabla
        model = new DefaultTableModel(new Object[]{
                "ID", "Ubicación", "Tipo", "Precio", "Capacidad", "Eliminar"
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

        tablaAlojamientos = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tablaAlojamientos);
        panelCentral.add(scrollPane, BorderLayout.CENTER);

        cargarAlojamientos();

        // Panel inferior para botones
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        getContentPane().add(panelInferior, BorderLayout.SOUTH);

        // Botón Crear Alojamiento
        JButton btnCrear = new JButton("Crear Alojamiento");
        btnCrear.addActionListener(e -> crearAlojamiento());
        panelInferior.add(btnCrear);

        // Botón Actualizar Alojamiento
        JButton btnActualizar = new JButton("Actualizar Alojamiento");
        btnActualizar.addActionListener(e -> actualizarAlojamiento());
        panelInferior.add(btnActualizar);

        // Botón Eliminar Alojamiento
        JButton btnEliminar = new JButton("Eliminar Alojamiento");
        btnEliminar.addActionListener(e -> eliminarAlojamientos());
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

    // Cargar alojamientos en la tabla
    private void cargarAlojamientos() {
        model.setRowCount(0); // Limpiar tabla
        LinkedList<Alojamiento> alojamientos = alojamientoController.listarAlojamientos();
        for (Alojamiento alojamiento : alojamientos) {
            model.addRow(new Object[]{
                    alojamiento.getIdAlojamiento(), alojamiento.getUbicacion(), alojamiento.getTipo(),
                    alojamiento.getPrecio(), alojamiento.getCapacidad(), false // Checkbox de eliminación
            });
        }
    }

    // Crear un nuevo alojamiento
    private void crearAlojamiento() {
        alojamientoController.crearAlojamiento();
        cargarAlojamientos();
    }

    // Actualizar alojamientos
    private void actualizarAlojamiento() {
        try {
            for (int i = 0; i < model.getRowCount(); i++) {
                int idAlojamiento = (int) model.getValueAt(i, 0);
                String ubicacion = (String) model.getValueAt(i, 1);
                String tipo = (String) model.getValueAt(i, 2);
                double precio = Double.parseDouble(model.getValueAt(i, 3).toString());
                int capacidad = Integer.parseInt(model.getValueAt(i, 4).toString());

                alojamientoController.actualizarAlojamiento(idAlojamiento);
            }
            cargarAlojamientos();
            JOptionPane.showMessageDialog(this, "Alojamientos actualizados exitosamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar los alojamientos.");
        }
    }

    // Eliminar alojamientos seleccionados
    private void eliminarAlojamientos() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea eliminar los alojamientos seleccionados?",
                "Confirmación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                boolean eliminar = (boolean) model.getValueAt(i, 5);
                if (eliminar) {
                    int idAlojamiento = (int) model.getValueAt(i, 0);
                    alojamientoController.eliminarAlojamiento(idAlojamiento);
                    model.removeRow(i);
                }
            }
            JOptionPane.showMessageDialog(this, "Alojamientos eliminados exitosamente.");
        }
    }
}
