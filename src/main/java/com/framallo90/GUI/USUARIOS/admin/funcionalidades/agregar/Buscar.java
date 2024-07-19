package com.framallo90.GUI.USUARIOS.admin.funcionalidades.agregar;

import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.Excepciones.NotFoundDNIException;
import com.framallo90.GUI.CLIENTES.auxiliar.ClienteEncontrado;
import com.framallo90.GUI.USUARIOS.auxiliar.EmpleadoEncontrado;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Buscar extends JFrame{
    private Empleados selectedEmpleados;
    private JPanel panel;
    private JList listaEmpleados;
    private JTextField dniInput;
    private JButton btnBuscar;
    private JButton cancelarButton;
    private JButton aceptarButton;
    private JLabel nombreField;
    private JLabel apellidoField;
    private JLabel usuarioField;
    private DefaultListModel<Empleados> listModel = new DefaultListModel<>();

    public Buscar(GestionConsecionaria gestionConsecionaria, Empleados empleados){
        setContentPane(panel);
        setTitle("Buscar empleados");
        setSize(450,450);
        setVisible(true);
        listaEmpleados.setModel(listModel);
        //VOLVER
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        //BUSCAR
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dniValue = null;
                List<Empleados> empleados = null;
                try{
                    dniValue = dniInput.getText().trim();
                    empleados = gestionConsecionaria.empleadosController.finXfiltro(dniValue);
                } catch (NotFoundDNIException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                if (empleados != null ){
                    listModel.clear();
                    for (Empleados empleados1: empleados){
                        listModel.addElement(empleados1);
                    }
                }
                else JOptionPane.showMessageDialog(null,"No se han encontrado empleados.");
            }
        });

        //JList selection listener
        listaEmpleados.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()){
                    selectedEmpleados = (Empleados) listaEmpleados.getSelectedValue();
                    if (selectedEmpleados != null){
                        EmpleadoEncontrado.setEmpleados(selectedEmpleados);
                        nombreField.setText(selectedEmpleados.getNombre());
                        apellidoField.setText(selectedEmpleados.getApellido());
                        usuarioField.setText(selectedEmpleados.getUsername());
                        aceptarButton.setEnabled(true);
                    }
                } else {
                    nombreField.setText("");
                    apellidoField.setText("");
                    usuarioField.setText("");
                    aceptarButton.setEnabled(false);

                }
            }
        });
        //SELECCION
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedEmpleados != null){
                    EmpleadoEncontrado.setEmpleados(selectedEmpleados);
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Aún no has seleccionado un empleado.");
                }
            }
        });
    }
}
