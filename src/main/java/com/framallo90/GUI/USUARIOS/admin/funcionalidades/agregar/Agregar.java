package com.framallo90.GUI.USUARIOS.admin.funcionalidades.agregar;

import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.UsuarioAbstracta.view.UsuarioView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Agregar extends JFrame{
    private JTextField textNombre;
    private JTextField textApellido;
    private JTextField textDni;
    private JTextField textUsuario;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JPasswordField textClave;
    private JPasswordField textConfirmacionClave;
    private JPasswordField textClaveAdmin;
    private JPanel panel;

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
    public Agregar(GestionConsecionaria gestionConsecionaria) throws HeadlessException {
        setContentPane(panel);
        setTitle("Agregar empleado");
        setSize(450,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        //VOLVER
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        //ACEPTAR
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre="",apellido="",dni="",usuario="",clave="";

                nombre = textNombre.toString();
                apellido=textApellido.toString();
                dni = textDni.toString();
                usuario=textUsuario.toString();
                clave=textClave.toString();

                if (nombre.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Completar campo de nombre.");
                }if (apellido.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Completar campo de apellido.");
                }if (!UsuarioView.isValidDni(dni)){
                    JOptionPane.showMessageDialog(null, "DNI inválido.");
                }if (usuario.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Agregar un nombre de usuario.");
                }else{
                    //Validar usuario (verificar que no se encuentre en uso.)
                    if (!gestionConsecionaria.empleadosController.validarUsername(usuario))
                        JOptionPane.showMessageDialog(null,"El nombre de usuario ya se encuentra en uso.");
                }if (clave.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Completar campo de clave.");
                }else{
                    if (!gestionConsecionaria.empleadosController.validarClave(clave))
                        JOptionPane.showMessageDialog(null, "Clave inválida. Reintentar.");
                    else{
                        if (!textConfirmacionClave.toString().equals(clave))
                            JOptionPane.showMessageDialog(null,"Las claves no coinciden. Reintentar.");
                    }
                }

                if (!nombre.isEmpty()&&
                        !apellido.isEmpty()&&
                        UsuarioView.isValidDni(dni)&&
                        !usuario.isEmpty()
                        &&gestionConsecionaria.empleadosController.validarClave(clave)&&
                        textConfirmacionClave.toString().equals(clave)){
                    gestionConsecionaria.empleadosController.crearEmpleado(nombre,apellido,dni,usuario,clave,textClaveAdmin.toString());
                    JOptionPane.showMessageDialog(null,"El empleado ha sido creado correctamente.");
                    dispose();
                }
            }
        });
    }
}
