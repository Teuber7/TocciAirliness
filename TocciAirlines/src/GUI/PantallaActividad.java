package GUI;

import DLL.ControllerActividad;
import BLL.Actividad;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;

public class PantallaActividad extends JFrame {
    private JTable tablaActividades;
    private DefaultTableModel model;
    private ControllerActividad actividadController;

    public PantallaActividad(String nombreUsuario) {
        actividadController = new ControllerActividad();

        setTitle("Gestión de Actividades");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Etiqueta superior
        JLabel lblBienvenida = new JLabel("Gestión de Actividades", SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        getContentPane().add(lblBienvenida, BorderLayout.NORTH);

        // Panel central para la tabla
        JPanel panelCentral = new JPanel(new BorderLayout());
        getContentPane().add(panelCentral, BorderLayout.CENTER);

        // Configurar tabla
        model = new DefaultTableModel(new Object[]{
                "ID", "Nombre", "Descripción", "Precio", "Eliminar"
        }, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 4) return Boolean.class; // Checkbox para eliminar
                return Object.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 4; // No editable ID y checkbox
            }
        };

        tablaActividades = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tablaActividades);
        panelCentral.add(scrollPane, BorderLayout.CENTER);

        cargarActividades();

        // Panel inferior para botones
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        getContentPane().add(panelInferior, BorderLayout.SOUTH);

        // Botón Crear Actividad
        JButton btnCrear = new JButton("Crear Actividad");
        btnCrear.addActionListener(e -> crearActividad());
        panelInferior.add(btnCrear);

        // Botón Actualizar Actividades
        JButton btnActualizar = new JButton("Actualizar Actividades");
        btnActualizar.addActionListener(e -> actualizarActividad());
        panelInferior.add(btnActualizar);

        // Botón Eliminar Actividades
        JButton btnEliminar = new JButton("Eliminar Actividades");
        btnEliminar.addActionListener(e -> eliminarActividades());
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

    // Cargar actividades en la tabla
    private void cargarActividades() {
        model.setRowCount(0); // Limpiar tabla
        LinkedList<Actividad> actividades = actividadController.obtenerActividades();
        for (Actividad actividad : actividades) {
            model.addRow(new Object[]{
                    actividad.getIdActividad(), actividad.getNombre(),
                    actividad.getDescripcion(), actividad.getPrecio(),
                    false // Checkbox de eliminación
            });
        }
    }

    // Crear una nueva actividad
    private void crearActividad() {
        try {
            actividadController.crearActividad();
            cargarActividades();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al crear la actividad.");
        }
    }

    // Actualizar actividades
    private void actualizarActividad() {
        try {
            for (int i = 0; i < model.getRowCount(); i++) {
                int idActividad = (int) model.getValueAt(i, 0);
                String nombre = (String) model.getValueAt(i, 1);
                String descripcion = (String) model.getValueAt(i, 2);
                double precio = Double.parseDouble(model.getValueAt(i, 3).toString());

                actividadController.actualizarActividad(new Actividad(idActividad, nombre, descripcion, precio));
            }
            JOptionPane.showMessageDialog(this, "Actividades actualizadas exitosamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar las actividades.");
        }
    }

    // Eliminar actividades seleccionadas
    private void eliminarActividades() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea eliminar las actividades seleccionadas?",
                "Confirmación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                boolean eliminar = (boolean) model.getValueAt(i, 4);
                if (eliminar) {
                    int idActividad = (int) model.getValueAt(i, 0);
                    actividadController.eliminarActividad(idActividad);
                    model.removeRow(i);
                }
            }
            JOptionPane.showMessageDialog(this, "Actividades eliminadas exitosamente.");
        }
    }
}

