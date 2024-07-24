package com.framallo90.GUI.CLIENTES.admin.funcionalidades.buscar;
import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.GUI.CLIENTES.auxiliar.ClienteEncontrado;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class BuscarCliente extends JFrame {
    private Comprador selectedComprador;
    private JPanel buscarCliente;
    private JPanel panel;
    private JTextField dniInput;
    private JButton btnBuscar;
    private JTextField apellidoField;
    private JTextField dniField;
    private JTextField emailField;
    private JButton btnSeleccionar;
    private JButton btnVolver;
    private JTextField nombreField;
    private JList listaClientes;
    private JScrollPane scrollPane;
    private final DefaultListModel<Comprador> listaClientesModel = new DefaultListModel<Comprador>();
    public BuscarCliente(GestionConsecionaria gestionConsecionaria, Comprador clienteEncontrado)  {
        setContentPane(buscarCliente);
        setTitle("Buscar cliente");
        setSize(450,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        listaClientes.setModel(listaClientesModel);
        //VOLVER
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //BUSCAR
        btnBuscar.addActionListener(new ActionListener() {
            String dniValue=null;
            @Override
            public void actionPerformed(ActionEvent e) {
                dniValue = dniInput.getText().trim();
                // Valid DNI, perform search based on your logic (replace with your search implementation)
                List<Comprador> compradores = null;
                try {
                    compradores = gestionConsecionaria.compradorController.findXFiltro(dniValue);
                } catch (InvalidIdNotFound ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                if (compradores != null) {
                    listaClientesModel.clear();
                    for (Comprador comprador:compradores)
                        listaClientesModel.addElement(comprador);
                } else {
                    JOptionPane.showMessageDialog(null, "No se han encontrado clientes.");
                    // Reset fields and disable button (optional)
                }
            }
        });

        // JList selection listener
        listaClientes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Avoid updates during selection change
                    selectedComprador = (Comprador) listaClientes.getSelectedValue();
                    if (selectedComprador != null) {
                        // Fill form fields with selected Comprador data
                        ClienteEncontrado.setComprador(selectedComprador);
                        nombreField.setText(selectedComprador.getNombre());
                        apellidoField.setText(selectedComprador.getApellido());
                        dniField.setText(selectedComprador.getDni()); // Assuming DNI is a String
                        emailField.setText(selectedComprador.getEmail());
                        btnSeleccionar.setEnabled(true);
                    } else {
                        // Clear form fields and disable button if no selection
                        nombreField.setText("");
                        apellidoField.setText("");
                        dniField.setText("");
                        emailField.setText("");
                        btnSeleccionar.setEnabled(false);
                    }
                }
            }
        });
        //SELECCIÓN
        btnSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedComprador != null) {
                    ClienteEncontrado.setComprador(selectedComprador);
                    dispose(); // Close the window
                } else {
                    JOptionPane.showMessageDialog(null, "Aún no has seleccionado un cliente.");
                }
            }
        });
    }


}
