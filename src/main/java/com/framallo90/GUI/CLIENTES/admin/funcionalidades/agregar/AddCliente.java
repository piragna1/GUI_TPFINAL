package com.framallo90.GUI.CLIENTES.admin.funcionalidades.agregar;

import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import com.framallo90.Automovil.Controller.AutomovilController;
import com.framallo90.Comprador.Controller.CompradorController;
import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Empleados.Controller.EmpleadosController;
import com.framallo90.Login.Login;
import com.framallo90.UsuarioAbstracta.view.UsuarioView;
import com.framallo90.Venta.Controller.VentaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCliente extends JFrame {
    private JPanel addCliente;
    private JPanel panel;
    private JTextField apellido;
    private JTextField dni;
    private JTextField email;
    private JButton btnEnviar;
    private JTextField nombre;
    private JButton btnCancelar;

    /**
     * Constructs a new frame that is initially invisible.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @throws HeadlessException if GraphicsEnvironment.isHeadless()
     *                           returns true.
     * @see GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefaultLocale
     */
    public AddCliente(GestionConsecionaria gestionConsecionaria)  {
        setContentPane(addCliente);
        setTitle("Agregar cliente");
        setSize(450,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        //VOLVER
        btnCancelar.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //AGREGAR CLIENTE
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre1 = null,apellido1=null,email1=null;
                nombre1 = nombre.getText().trim();
                apellido1 = apellido.getText().trim();
                String dniText = dni.getText().trim();
                email1 = email.getText().trim();

                // Validate all fields
                if (!gestionConsecionaria.compradorController.validarNombre(nombre1)) {
                    JOptionPane.showMessageDialog(null, "Completar campo de nombre.");
                }
                if (!gestionConsecionaria.compradorController.validarNombre(apellido1)) {
                    JOptionPane.showMessageDialog(null, "Completar campo de apellido.");
                }
                if (!UsuarioView.isValidDni(dniText)) {
                    JOptionPane.showMessageDialog(null, "DNI inválido.");
                }
                if (!UsuarioView.isValidEmail(email1)) {
                    JOptionPane.showMessageDialog(null, "Email inválido.");
                }

                if (gestionConsecionaria.compradorController.validarNombre(nombre1)&&
                    gestionConsecionaria.compradorController.validarNombre(apellido1)&&
                    UsuarioView.isValidEmail(email1) &&
                    UsuarioView.isValidDni(dniText)){
                        JOptionPane.showMessageDialog(null, "Cliente creado correctamente.");
                         gestionConsecionaria.compradorController.add(nombre1,apellido1,dniText,email1);
                        dispose();
                }
            }
        });
    }
}