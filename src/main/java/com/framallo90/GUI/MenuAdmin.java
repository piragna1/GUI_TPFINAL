package com.framallo90.GUI;

import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import com.framallo90.Automovil.Controller.AutomovilController;
import com.framallo90.Comprador.Controller.CompradorController;
import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Empleados.Controller.EmpleadosController;
import com.framallo90.GUI.CLIENTES.Clientes;
import com.framallo90.Login.Login;
import com.framallo90.Venta.Controller.VentaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAdmin extends JFrame{
    private JPanel menuAdmin;
    private JPanel panel;
    private JButton btnVentas;
    private JButton btnCarros;
    private JButton btnUsuarios;
    private JButton btnVolver;
    private JButton btnClientes;

    public MenuAdmin(GestionConsecionaria gestionConsecionaria){
        setContentPane(menuAdmin);
        setTitle("Menu administrador");
        setSize(450,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        //VOLVER
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Pantalla(gestionConsecionaria);
                dispose();
            }
        });

        // MENU CLIENTES
        btnClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Clientes(gestionConsecionaria, new Comprador());
                dispose();
            }
        });
    }
}
