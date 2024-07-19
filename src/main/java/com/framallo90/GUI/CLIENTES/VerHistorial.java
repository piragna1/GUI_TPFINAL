package com.framallo90.GUI.CLIENTES;

import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import com.framallo90.Comprador.Model.Entity.Comprador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class VerHistorial extends JFrame {
    private JPanel panel;
    private JTable tablaClientes;
    private JScrollPane scrollPane;

    public VerHistorial(GestionConsecionaria gestionConsecionaria) {

        panel = new JPanel();

        List<Comprador> compradores = new ArrayList(gestionConsecionaria.compradorController.getListaCompradores());
        // Crear la tabla
        String[] columnNames = {"Nombre", "Apellido", "DNI", "Correo"};
        Object[][] data = new Object[compradores.size()][4];
        for (int i = 0; i < compradores.size(); i++) {
            Comprador comprador = compradores.get(i);
            data[i][0] = comprador.getNombre();
            data[i][1] = comprador.getApellido();
            data[i][2] = comprador.getDni();
            data[i][3] = comprador.getEmail();
        }


        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        tablaClientes = new JTable(tableModel);
        tablaClientes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


        // Crear el JScrollPane
        scrollPane = new JScrollPane(tablaClientes);

        // Agregar la tabla al panel
        panel.add(scrollPane);

        // Configurar la ventana
        setContentPane(panel);
        setTitle("Lista de Clientes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        setVisible(true);
    }
}
