package com.framallo90.GUI;

import com.framallo90.Comprador.Controller.CompradorController;
import com.framallo90.Login.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pantalla extends JFrame {
    private JPanel pantallaIncio;
    private JPanel panel;
    private JButton btnAdmin;
    private JButton btnVendedor;
    private JLabel tituloInicio;
    private JLabel introInicio;
    private JButton btnSalir;

    public Pantalla (Login login, CompradorController compradorController) {
        setContentPane(pantallaIncio);
        setTitle("Proyecto Final 3");
        setSize(500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        btnAdmin.addActionListener(new ActionListener() {
            //IR A MENU ADMIN
            //SET "invisible" menú principal
            @Override
            public void actionPerformed(ActionEvent e) {
                GUILogin lAdmin = new GUILogin(login,compradorController);
                setVisible(false);
            }
        });
        btnVendedor.addActionListener(new ActionListener() {
            //IR A MENU VENDEDOR
            //SET "invisible" menú principal
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        btnSalir.addActionListener(new ActionListener() {
            //CERRAR PROGRAMA
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}