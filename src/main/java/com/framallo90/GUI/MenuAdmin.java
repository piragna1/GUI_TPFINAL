package com.framallo90.GUI;

import com.framallo90.Login.Login;

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

    public MenuAdmin(Login login){
        setContentPane(menuAdmin);
        setTitle("Menu administrador");
        setSize(450,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        //VOLVER
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Pantalla(login);
            }
        });

    }
}
