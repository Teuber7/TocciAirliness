package GUI;

import DLL.ControllerAlojamiento; // Asegúrate de tener este controlador
import BLL.Alojamiento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;

public class PantallaGestionAlojamiento extends JFrame {
    private ControllerAlojamiento alojamientoController;
    private DefaultTableModel tableModel;

    public PantallaGestionAlojamiento() {
        setTitle("Gestión de Alojamientos");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        alojamientoController = new ControllerAlojamiento();

        // Panel superior con botones CRUD
        JPanel panelSuperior = new JPanel();
        JButton btnCrear = new JButton("Crear Alojamiento");
        JButton btnActualizar = new JButton("Actualizar Alojamiento");
        JButton btnEliminar = new JButton("Eliminar Alojamiento");
        JButton btnCargar = new JButton("Cargar Alojamientos");
        JButton btnRegresar = new JButton("Regresar a Pantalla Admin");  // Nuevo botón para regresar

        panelSuperior.add(btnCrear);
        panelSuperior.add(btnActualizar);
        panelSuperior.add(btnEliminar);
        panelSuperior.add(btnCargar);
        panelSuperior.add(btnRegresar);  // Agregamos el botón al panel superior

        getContentPane().add(panelSuperior, BorderLayout.NORTH);

        // Tabla para mostrar los alojamientos
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Ubicación");
        tableModel.addColumn("Tipo");
        tableModel.addColumn("Precio");
        tableModel.addColumn("Capacidad");

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Acciones de los botones
        btnCrear.addActionListener(e -> {
            alojamientoController.crearAlojamiento();
            cargarAlojamientos(); // Refresca la tabla
        });

        btnActualizar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int idAlojamiento = (int) tableModel.getValueAt(selectedRow, 0);
                alojamientoController.actualizarAlojamiento(idAlojamiento); // Actualiza el alojamiento
                cargarAlojamientos(); // Refresca la tabla
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un alojamiento para actualizar.");
            }
        });

        btnEliminar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int idAlojamiento = (int) tableModel.getValueAt(selectedRow, 0);
                alojamientoController.eliminarAlojamiento(idAlojamiento); // Elimina el alojamiento
                cargarAlojamientos(); // Refresca la tabla
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un alojamiento para eliminar.");
            }
        });

        btnCargar.addActionListener(e -> cargarAlojamientos());

        // Acción del botón Regresar
        btnRegresar.addActionListener(e -> {
            // Cierra la ventana actual y abre la pantalla de administración (PantallaAdmin)
            dispose();
            new PantallaAdmin().setVisible(true); // Asegúrate de que 'PantallaAdmin' sea tu clase de pantalla de administración
        });

        setLocationRelativeTo(null);
        cargarAlojamientos(); // Carga inicial de alojamientos
    }

    private void cargarAlojamientos() {
        tableModel.setRowCount(0); // Limpia la tabla
        LinkedList<Alojamiento> alojamientos = alojamientoController.listarAlojamientos();

        for (Alojamiento alojamiento : alojamientos) {
            tableModel.addRow(new Object[]{
                    alojamiento.getIdAlojamiento(),
                    alojamiento.getUbicacion(),
                    alojamiento.getTipo(),
                    alojamiento.getPrecio(),
                    alojamiento.getCapacidad()
            });
        }
    }
}
