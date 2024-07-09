package com.framallo90.GUI;
import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import com.framallo90.Automovil.Controller.AutomovilController;
import com.framallo90.Comprador.Controller.CompradorController;
import com.framallo90.Empleados.Controller.EmpleadosController;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.Login.Login;
import com.framallo90.Venta.Controller.VentaController;
import com.framallo90.consola.Consola;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUILogin extends JFrame{
    private JPanel login;
    private JPanel panel;
    private JLabel textUser;
    private JTextField username;
    private JTextField password;
    private JButton btnLogin;
    private JLabel textPassword;
    private JButton btnVolver;
    private Empleados empleados = null;
    public GUILogin(GestionConsecionaria gestionConsecionaria) {
        setTitle("Ingreso");
        setSize(500,500);
        setContentPane(this.login);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        //VOLVER.
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Pantalla(gestionConsecionaria);
                dispose();
            }
        });
        //INICIO DE SESIÓN.
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Empleados empleados=null;
                while (empleados==null){
                String userName = username.getText();
                String password = GUILogin.this.password.getText();
                empleados = gestionConsecionaria.login.login(userName,password);
                }
                Login.empleadoIngresado = empleados;
                //MENU ADMINISTRADOR
                if (empleados.getTipo().equalsIgnoreCase("admin")) {
                    new MenuAdmin(gestionConsecionaria);
                } else if (empleados.getTipo().equalsIgnoreCase("administrador")) {
                    new MenuAdmin(gestionConsecionaria);
                }
                    //MENU VENDEDOR
                else {
                }
                dispose();
            }
        });
    }
}
