package com.framallo90.GUI;

import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.GUI.CLIENTES.admin.ClientesAdmin;
import com.framallo90.GUI.USUARIOS.admin.UsuariosAdmin;

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
                new ClientesAdmin(gestionConsecionaria);
                dispose();
            }
        });

        //MENU EMPLEADOS
        btnUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UsuariosAdmin(gestionConsecionaria);
                dispose();
            }
        });
    }
}
