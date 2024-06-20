package com.framallo90.GUI.CLIENTES;

import com.framallo90.Comprador.Controller.CompradorController;
import com.framallo90.GUI.MenuAdmin;
import com.framallo90.Login.Login;

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
    public Clientes(Login login, CompradorController compradorController){
        setContentPane(menuClientes);
        setTitle("Menu clientes");
        setSize(450,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        //VOLVER AL MENU ADMIN
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuAdmin(login,compradorController);
                dispose();
            }
        });
        //AGREGAR COMPRADOR
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GENERO CLIENTE
                AddCliente addCliente = new AddCliente(login,compradorController);
                dispose();
            }
        });
        //MODIFICAR COMPRADOR
        //ELIMINAR COMPRADOR
        //BUSCAR COMPRADOR
        //VER COMPRADORES COMPRADOR
    }
}
