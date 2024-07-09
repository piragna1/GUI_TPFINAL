package com.framallo90.GUI;
import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Pantalla extends JFrame {
    private JPanel pantallaIncio;
    private JPanel panel;
    private JButton btnAdmin;
    private JButton btnIngreso;
    private JLabel tituloInicio;
    private JButton btnSalir;
    public Pantalla (GestionConsecionaria gestionConsecionaria) {
        setContentPane(pantallaIncio);
        setTitle("Proyecto Final 3");
        setSize(500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        //INGRESO
        btnAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUILogin(gestionConsecionaria);
                dispose();
            }
        });
        //CERRAR PROGRAMA
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}