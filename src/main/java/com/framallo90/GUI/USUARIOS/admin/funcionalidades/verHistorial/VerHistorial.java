package com.framallo90.GUI.USUARIOS.admin.funcionalidades.verHistorial;

import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import com.framallo90.Empleados.Model.Entity.Empleados;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class VerHistorial extends JFrame {
    private JPanel panel;
    private JTable tablaUsuarios;
    private JScrollPane scrollPane;

    public VerHistorial(GestionConsecionaria gestionConsecionaria) {

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel();

        List<Empleados> empleados = new ArrayList(gestionConsecionaria.empleadosController.getListaEmpleados());
        // Crear la tabla
        String[] columnNames = {"ID","Tipo", "Nombre", "Apellido", "DNI", "Autos vendidos", "Username"};
        Object[][] data = new Object[empleados.size()][7];
        for (int i = 0; i < empleados.size(); i++) {
            Empleados empleado = empleados.get(i);
            data[i][0] = empleado.getId();
            data[i][6] = empleado.getTipo();
            data[i][1] = empleado.getNombre();
            data[i][2] = empleado.getApellido();
            data[i][3] = empleado.getDni();
            data[i][4] = empleado.getAutosvendidos();
            data[i][5] = empleado.getUsername();
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        tablaUsuarios = new JTable(tableModel);
        tablaUsuarios.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


        // Crear el JScrollPane
        scrollPane = new JScrollPane(tablaUsuarios);

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
