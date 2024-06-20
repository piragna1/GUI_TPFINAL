package com.framallo90.GUI;

import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.Excepciones.InicioSesionException;
import com.framallo90.Login.Login;
import com.framallo90.consola.Consola;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginVendedor extends JFrame{
    private JPanel loginVendedor;
    private JPanel panel;
    private JTextField userVendedor;
    private JButton btnLoginVendedor;
    private JLabel textUserVendedor;
    private JLabel textContraVendedor;
    private JButton btnVolver;
    private JPasswordField contraVendedor;

    // externos al Form
    Login login = new Login();
    Empleados empleados = null;

    // MainFrame
    public LoginVendedor (Login login) {
        setTitle("Vendedor");
        setContentPane(loginVendedor);
        setSize(500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pantalla pantalla = new Pantalla(login);
                dispose();
            }
        });
        btnLoginVendedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userVendedor.getText();
                String contra = String.valueOf(contraVendedor.getPassword());
                try {
                    empleados = login.login(user,contra);
                } catch (InicioSesionException ex) {
                    Consola.soutString(ex.getMessage());
                }
                if (empleados.getTipo().equalsIgnoreCase("Vendedor")){
                    System.out.println("paso algo random bro");
                }
            }
        });
    }

}
