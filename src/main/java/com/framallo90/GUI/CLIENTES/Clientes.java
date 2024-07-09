package com.framallo90.GUI.CLIENTES;

import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.GUI.Interfaces.ClienteEncontrado;
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
    private Comprador selectedComprador; // To store the found Comprador
    private ClienteEncontrado callback;

    public void setCallback(ClienteEncontrado callback) {
        this.callback = callback;
    }

    public Clientes(GestionConsecionaria gestionConsecionaria){
        setContentPane(menuClientes);
        setTitle("Menu clientes");
        setSize(450,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        //VOLVER
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuAdmin(gestionConsecionaria);
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
                if (selectedComprador != null){
                    new ModificarCliente();
                    dispose();
                } else setEnabled(false);// Initially disabled (assuming modification requires search)
            }
        });
        //ELIMINAR COMPRADOR
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedComprador != null) {
                    // Eliminar el comprador seleccionado (implement logic)
                    // Reset selectedComprador after deletion
                    selectedComprador = null;
                } else setEnabled(false);
            }
        });
        //BUSCAR COMPRADOR
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create BuscarCliente instance with the callback
                new BuscarCliente(gestionConsecionaria);
                dispose();
            }
        });
        //VER COMPRADORES COMPRADOR


        // Implement ClienteEncontrado interface
        ClienteEncontrado callback = new ClienteEncontrado() {
            @Override
            public void clienteEncontrado(Comprador comprador) {
                // Store the found comprador in the Clientes class
                selectedComprador = comprador;
            }
        };
    }
}
