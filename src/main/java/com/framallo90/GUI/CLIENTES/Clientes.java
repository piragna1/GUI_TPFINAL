package com.framallo90.GUI.CLIENTES;

import com.framallo90.Automovil.Controller.AutomovilController;
import com.framallo90.Comprador.Controller.CompradorController;
import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Empleados.Controller.EmpleadosController;
import com.framallo90.GUI.MenuAdmin;
import com.framallo90.Login.Login;
import com.framallo90.Venta.Controller.VentaController;

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
    private Comprador selectedComprador; // To store the found Comprador

    public Clientes(Login login, CompradorController compradorController,
                    AutomovilController automovilController, EmpleadosController empleadosController,
                    VentaController ventaController){
        setContentPane(menuClientes);
        setTitle("Menu clientes");
        setSize(450,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        //VOLVER


        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuAdmin(login,compradorController,automovilController,empleadosController,ventaController);
                dispose();
            }
        });
        //AGREGAR COMPRADOR
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GENERO CLIENTE
                AddCliente addCliente = new AddCliente(login,compradorController,automovilController,empleadosController,ventaController);
                dispose();
            }
        });
        // MODIFICAR COMPRADOR (placeholder for future implementation)
        btnModificar.setEnabled(false); // Initially disabled (assuming modification requires search)
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedComprador != null) {
                    // Modificar el comprador seleccionado (implement logic)
                    // You can access selectedComprador attributes here (e.g., nombre, dni)
                    System.out.println("Modificando comprador: " + selectedComprador.getNombre());
                } else {
                    JOptionPane.showMessageDialog(null, "Debe buscar un cliente antes de modificarlo.");
                }
            }
        });
        //ELIMINAR COMPRADOR
        btnEliminar.setEnabled(false); // Initially disabled (assuming deletion requires search)
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedComprador != null) {
                    // Eliminar el comprador seleccionado (implement logic)
                    // You can access selectedComprador attributes here (e.g., dni)
                    System.out.println("Eliminando comprador: " + selectedComprador.getNombre());
                    // Reset selectedComprador after deletion
                    selectedComprador = null;
                } else {
                    JOptionPane.showMessageDialog(null, "Debe buscar un cliente antes de eliminarlo.");
                }
            }
        });
        //BUSCAR COMPRADOR
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement ClienteEncontrado interface
                BuscarCliente.ClienteEncontrado callback = new BuscarCliente.ClienteEncontrado() {
                    @Override
                    public void clienteEncontrado(Comprador comprador) {
                        // Store the found comprador in the Clientes class
                        selectedComprador = comprador;
                        // Enable modification and deletion buttons if a comprador is found
                        btnModificar.setEnabled(true);
                        btnEliminar.setEnabled(true);
                    }
                };

                // Create BuscarCliente instance with the callback
                new BuscarCliente(login, compradorController, automovilController, empleadosController, ventaController, callback);
                dispose();
            }
        });
        //VER COMPRADORES COMPRADOR


        // Implement ClienteEncontrado interface
        BuscarCliente.ClienteEncontrado callback = new BuscarCliente.ClienteEncontrado() {
            @Override
            public void clienteEncontrado(Comprador comprador) {
                // Store the found comprador in the Clientes class
                selectedComprador = comprador;
            }
        };
    }
}
