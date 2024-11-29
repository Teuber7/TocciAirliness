package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;
import DLL.ControllerVuelo;
import BLL.Vuelo;

public class PantallaGestionVuelo extends JFrame {

    public PantallaGestionVuelo() {
        setTitle("Gestión de Vuelos");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Controlador para vuelos
        ControllerVuelo vueloController = new ControllerVuelo();
        LinkedList<Vuelo> vuelos = vueloController.listarVuelos();

        // Crear modelo para la tabla
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // Hacer todas las columnas editables excepto la de ID
            }
        };

        model.addColumn("ID");
        model.addColumn("Origen");
        model.addColumn("Destino");
        model.addColumn("Fecha Salida");
        model.addColumn("Fecha Llegada");
        model.addColumn("Precio");

        // Llenar la tabla con datos
        for (Vuelo vuelo : vuelos) {
            model.addRow(new Object[]{
                vuelo.getIdVuelo(),
                vuelo.getOrigen(),
                vuelo.getDestino(),
                vuelo.getFechaSalida(),
                vuelo.getFechaLlegada(),
                vuelo.getPrecio()
            });
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        JButton btnCrear = new JButton("Crear Vuelo");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnVolver = new JButton("Volver");

        // Acción para crear vuelos
        btnCrear.addActionListener(e -> abrirVentanaCrear(vueloController, model));

        // Acción para actualizar vuelos
        btnActualizar.addActionListener(e -> {
            for (int i = 0; i < model.getRowCount(); i++) {
                int idVuelo = (int) model.getValueAt(i, 0);
                String origen = (String) model.getValueAt(i, 1);
                String destino = (String) model.getValueAt(i, 2);
                String fechaSalida = (String) model.getValueAt(i, 3);
                String fechaLlegada = (String) model.getValueAt(i, 4);
                double precio = Double.parseDouble(model.getValueAt(i, 5).toString());

                vueloController.actualizarVuelo(idVuelo);
            }
            JOptionPane.showMessageDialog(this, "Vuelos actualizados exitosamente.");
        });

        // Acción para eliminar vuelos seleccionados
        btnEliminar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int idVuelo = (int) model.getValueAt(selectedRow, 0);
                vueloController.eliminarVuelo(idVuelo);
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Vuelo eliminado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un vuelo para eliminar.");
            }
        });

        // Acción para volver
        btnVolver.addActionListener(e -> {
            new PantallaAdmin().setVisible(true);
            dispose();
        });

        panelBotones.add(btnCrear);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVolver);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private void abrirVentanaCrear(ControllerVuelo vueloController, DefaultTableModel model) {
        JTextField txtOrigen = new JTextField();
        JTextField txtDestino = new JTextField();
        JTextField txtFechaSalida = new JTextField();
        JTextField txtFechaLlegada = new JTextField();
        JTextField txtPrecio = new JTextField();

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Origen:"));
        panel.add(txtOrigen);
        panel.add(new JLabel("Destino:"));
        panel.add(txtDestino);
        panel.add(new JLabel("Fecha Salida (YYYY-MM-DD):"));
        panel.add(txtFechaSalida);
        panel.add(new JLabel("Fecha Llegada (YYYY-MM-DD):"));
        panel.add(txtFechaLlegada);
        panel.add(new JLabel("Precio (Económica):"));
        panel.add(txtPrecio);

        int result = JOptionPane.showConfirmDialog(this, panel, "Crear Vuelo", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // Clase fija "Económica"
            String clase = "Económica";
            
            // Crear vuelo en el controlador
            vueloController.crearVuelo(txtOrigen.getText(), txtDestino.getText(), txtFechaSalida.getText(), 
                                       txtFechaLlegada.getText(), clase, Double.parseDouble(txtPrecio.getText()));

            // Agregar el vuelo a la tabla
            model.addRow(new Object[]{
                null, txtOrigen.getText(), txtDestino.getText(), txtFechaSalida.getText(),
                txtFechaLlegada.getText(), txtPrecio.getText()
            });
            JOptionPane.showMessageDialog(this, "Vuelo creado exitosamente.");
        }
    }
}
