package com.framallo90.GUI.CLIENTES;

import com.framallo90.Automovil.Controller.AutomovilController;
import com.framallo90.Comprador.Controller.CompradorController;
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
        //MODIFICAR COMPRADOR
        //ELIMINAR COMPRADOR
        //BUSCAR COMPRADOR
        btnBuscar.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuscarCliente(login,compradorController,automovilController,empleadosController,ventaController);
                dispose();
            }
        });
        //VER COMPRADORES COMPRADOR
    }
}
