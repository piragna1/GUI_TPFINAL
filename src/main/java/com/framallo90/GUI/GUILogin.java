package com.framallo90.GUI;
import com.framallo90.Comprador.Controller.CompradorController;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.Excepciones.InicioSesionException;
import com.framallo90.Login.Login;
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

    public GUILogin(Login login, CompradorController compradorController) {
        setTitle("Administrador");
        setSize(500,500);
        setContentPane(this.login);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        btnVolver.addActionListener(new ActionListener() {
            //VOLVER al punto de partida.
            @Override
            public void actionPerformed(ActionEvent e) {
                Pantalla pantalla = new Pantalla(login,compradorController);
                dispose();
            }
        });
        //Inicio sesion.
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuAdmin menuAdmin;
                Empleados empleados = null;
                while (true){
                String userName = username.getText();
                String password = GUILogin.this.password.getText();
                    try {
                        empleados = login.login(userName,password);
                        if (empleados!=null)break;
                    } catch (InicioSesionException ex) {
                        Consola.soutString(ex.getMessage());
                    }
                //Interfaz menú empleado.
                }
                if (empleados.getTipo().equalsIgnoreCase("admin"))
                    menuAdmin = new MenuAdmin(login,compradorController);
                else if(empleados.getTipo().equalsIgnoreCase("administrador"))
                    menuAdmin = new MenuAdmin(login,compradorController);
                else {} //MENU VENDEDOR
                dispose();
            }
        });
    }
}
