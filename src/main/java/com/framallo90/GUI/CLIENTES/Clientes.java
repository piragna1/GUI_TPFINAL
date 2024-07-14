package com.framallo90.GUI.CLIENTES;

import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.GUI.CLIENTES.auxiliar.ClienteEncontrado;
import com.framallo90.GUI.MenuAdmin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clientes extends JFrame{
    private JPanel menuClientes;
    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnVerHistorial;
    private JButton btnVolver;
    private JTextField selectedDni;
    private JTextField selectedApellido;
    private JTextField selectedNombre;

    public Clientes(GestionConsecionaria gestionConsecionaria, Comprador comprador){
        setContentPane(menuClientes);
        setTitle("Menu clientes");
        setSize(450,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        if (comprador != null){
            selectedDni.setText(comprador.getDni());
            selectedNombre.setText(comprador.getNombre());
            selectedApellido.setText(comprador.getApellido());
        }


        //VOLVER
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuAdmin(gestionConsecionaria);
                ClienteEncontrado.comprador = null;
                dispose();
            }
        });
        //AGREGAR COMPRADOR
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GENERO CLIENTE
                AddCliente addCliente = new AddCliente(gestionConsecionaria);
                dispose();
            }
        });

        // MODIFICAR COMPRADOR (placeholder for future implementation)
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ClienteEncontrado.comprador != null){
                    new ModificarCliente(gestionConsecionaria,ClienteEncontrado.comprador);
                    dispose();
                } else JOptionPane.showMessageDialog(null, "Aún no hay un cliente seleccionado.");
            }
        });
        //ELIMINAR COMPRADOR
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ClienteEncontrado.comprador != null) {
                    // Eliminar el comprador seleccionado (implement logic)
                    gestionConsecionaria.compradorController.remove(ClienteEncontrado.comprador.getId());
                    // Reset selectedComprador after deletion
                    ClienteEncontrado.comprador = null;
                    selectedDni.setText("");
                    selectedNombre.setText("");
                    selectedApellido.setText("");
                    JOptionPane.showMessageDialog(null,"El comprador ha sido removido correctamente.");
                } else JOptionPane.showMessageDialog(null, "Aún no hay un cliente seleccionado.");
            }
        });
        //BUSCAR COMPRADOR
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create BuscarCliente instance with the callback
                new BuscarCliente(gestionConsecionaria, ClienteEncontrado.comprador);
                if (ClienteEncontrado.comprador != null ){
                    selectedDni.setText(ClienteEncontrado.comprador.getDni());
                    selectedNombre.setText(ClienteEncontrado.comprador.getNombre());
                    selectedApellido.setText(ClienteEncontrado.comprador.getApellido());
                    JOptionPane.showMessageDialog(null,"QUEPSA");
                }
                else JOptionPane.showMessageDialog(null,"no se escribe");
            }
        });
        //VER COMPRADORES COMPRADOR
    }
}
