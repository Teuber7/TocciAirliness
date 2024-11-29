package GUI;

import DLL.ControllerActividad; // Asegúrate de tener este controlador
import BLL.Actividad;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;

public class PantallaGestionActividad extends JFrame {
    private ControllerActividad actividadController;
    private DefaultTableModel tableModel;

    public PantallaGestionActividad() {
        setTitle("Gestión de Actividades");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        actividadController = new ControllerActividad();

        // Panel superior con botones CRUD
        JPanel panelSuperior = new JPanel();
        JButton btnCrear = new JButton("Crear Actividad");
        JButton btnActualizar = new JButton("Actualizar Actividad");
        JButton btnEliminar = new JButton("Eliminar Actividad");
        JButton btnCargar = new JButton("Cargar Actividades");
        JButton btnRegresar = new JButton("Regresar");

        panelSuperior.add(btnCrear);
        panelSuperior.add(btnActualizar);
        panelSuperior.add(btnEliminar);
        panelSuperior.add(btnCargar);
        panelSuperior.add(btnRegresar); // Agregar el botón regresar

        getContentPane().add(panelSuperior, BorderLayout.NORTH);

        // Tabla para mostrar las actividades
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Descripción");
        tableModel.addColumn("Precio");

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Acciones de los botones
        btnCrear.addActionListener(e -> {
            actividadController.crearActividad();
            cargarActividades(); // Refresca la tabla
        });

        btnActualizar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int idActividad = (int) tableModel.getValueAt(selectedRow, 0);
                // Obtenemos los nuevos valores para la actualización
                String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:");
                String nuevaDescripcion = JOptionPane.showInputDialog("Nueva descripción:");
                String nuevoPrecioStr = JOptionPane.showInputDialog("Nuevo precio:");
                double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);

                // Actualiza la actividad con los nuevos datos
                actividadController.actualizarActividad();
                cargarActividades(); // Refresca la tabla
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una actividad para actualizar.");
            }
        });

        btnEliminar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int idActividad = (int) tableModel.getValueAt(selectedRow, 0);
                actividadController.eliminarActividad(); // Elimina la actividad
                cargarActividades(); // Refresca la tabla
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una actividad para eliminar.");
            }
        });

        btnCargar.addActionListener(e -> cargarActividades());

        // Acción del botón "Regresar"
        btnRegresar.addActionListener(e -> {
            // Aquí asumo que tienes una clase llamada PantallaAdmin que representa la pantalla de administración.
            PantallaAdmin pantallaAdmin = new PantallaAdmin(); // Crear una nueva instancia de la pantalla de administración
            pantallaAdmin.setVisible(true); // Mostrar la pantalla de administración
            dispose(); // Cerrar la pantalla actual
        });

        setLocationRelativeTo(null);
        cargarActividades(); // Carga inicial de actividades
    }

    private void cargarActividades() {
        tableModel.setRowCount(0); // Limpia la tabla
        LinkedList<Actividad> actividades = actividadController.obtenerActividades();

        for (Actividad actividad : actividades) {
            tableModel.addRow(new Object[] {
                    actividad.getIdActividad(),
                    actividad.getNombre(),
                    actividad.getDescripcion(),
                    actividad.getPrecio()
            });
        }
    }
}

