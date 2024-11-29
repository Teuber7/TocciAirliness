package GUI;

import DLL.ControllerVuelo;
import BLL.Vuelo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;

public class PantallaCompra extends JFrame {
    private ControllerVuelo vueloController;

    public PantallaCompra(int idUsuario) {
        setTitle("Compra de Vuelos");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        vueloController = new ControllerVuelo();

        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new GridLayout(2, 2, 10, 10));

        JTextField txtOrigen = new JTextField();
        JTextField txtDestino = new JTextField();

        panelBusqueda.add(new JLabel("Origen:"));
        panelBusqueda.add(txtOrigen);
        panelBusqueda.add(new JLabel("Destino:"));
        panelBusqueda.add(txtDestino);

        JButton btnBuscar = new JButton("Buscar Vuelos");
        JButton btnComprar = new JButton("Comprar Vuelo");

        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(btnComprar);

        getContentPane().add(panelBusqueda, BorderLayout.NORTH);

        // Modelo de tabla para mostrar vuelos
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Origen");
        model.addColumn("Destino");
        model.addColumn("Salida");
        model.addColumn("Llegada");
        model.addColumn("Precio");

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Acción para buscar vuelos
        btnBuscar.addActionListener(e -> {
            model.setRowCount(0); // Limpiar la tabla
            String origen = txtOrigen.getText();
            String destino = txtDestino.getText();

            LinkedList<Vuelo> vuelos = vueloController.buscarVuelos(origen, destino);

            if (vuelos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron vuelos para el origen y destino ingresados.");
            } else {
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
            }
        });

        // Acción para comprar vuelos
        btnComprar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un vuelo de la tabla.");
            } else {
                int idVuelo = (int) model.getValueAt(selectedRow, 0); // Obtener ID del vuelo seleccionado
                vueloController.comprarVuelo(idUsuario, idVuelo);
                JOptionPane.showMessageDialog(this, "Compra realizada exitosamente para el vuelo con ID: " + idVuelo);
            }
        });

        setLocationRelativeTo(null);
    }
}

