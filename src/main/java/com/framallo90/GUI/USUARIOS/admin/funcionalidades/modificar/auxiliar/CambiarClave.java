package com.framallo90.GUI.USUARIOS.admin.funcionalidades.modificar.auxiliar;

import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.GUI.USUARIOS.admin.funcionalidades.modificar.Modificar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class CambiarClave extends JFrame{
    private JPanel panel;
    private JPasswordField nuevaClave;
    private JPasswordField confirmacionNuevaClave;
    private JButton btnAceptar;
    private JButton btnCancelar;

    public CambiarClave(GestionConsecionaria gestionConsecionaria, Empleados empleados){
        setContentPane(panel);
        setTitle("Modificación Clave");
        setSize(450,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        //CANCELAR
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevaClave.setText("");
                confirmacionNuevaClave.setText("");
                dispose();
            }
        });
        //ACEPTAR
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gestionConsecionaria.empleadosController.validarClave(Arrays.toString(nuevaClave.getPassword()))){
                    if (Arrays.toString(nuevaClave.getPassword()).equals(Arrays.toString(confirmacionNuevaClave.getPassword()))){
                        Modificar.actualizarClaveEmpleado(empleados,Arrays.toString(nuevaClave.getPassword()));
                        JOptionPane.showMessageDialog(null,"Clave modificada satisfactoriamente.");
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Las claves no coinciden.");
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"Ingresar una clave válida.");
                }
            }
        });
    }
}
