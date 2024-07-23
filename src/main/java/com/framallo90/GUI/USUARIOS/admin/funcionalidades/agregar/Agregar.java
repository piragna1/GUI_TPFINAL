package com.framallo90.GUI.USUARIOS.admin.funcionalidades.agregar;

import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.UsuarioAbstracta.view.UsuarioView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

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
                StringBuilder errorMessage = new StringBuilder(); // Collect validation errors
                boolean isValid = true; // Track overall validity

                String nombre = textNombre.getText().trim();
                String apellido = textApellido.getText().trim();
                String dni = textDni.getText().trim();
                String usuario = textUsuario.getText().trim();
                String clave = new String(textClave.getPassword()).trim(); // Convert char[] to String

                // Validate name (optional)
                if (nombre.isEmpty()) {
                    errorMessage.append("Ingrese un nombre.\n");
                    isValid = false;
                }

                // Validate surname (optional)
                if (apellido.isEmpty()) {
                    errorMessage.append("Ingrese un apellido.\n");
                    isValid = false;
                }

                // Validate DNI using UsuarioView.isValidDni()
                if (!UsuarioView.isValidDni(dni)) {
                    errorMessage.append("DNI inválido.\n");
                    isValid = false;
                }

                if (!gestionConsecionaria.empleadosController.disponibilidadDNI(dni)){
                    errorMessage.append("Ya existe un usuario con el DNI ingresado.\n");
                    isValid = false;
                }

                // Validate username (optional)
                if (usuario.isEmpty()) {
                    errorMessage.append("Ingrese un nombre de usuario.\n");
                    isValid = false;
                } else {
                    // Validate username existence (implement logic using gestionConsecionaria)
                    if (!gestionConsecionaria.empleadosController.validarUsername(usuario)) {
                        errorMessage.append("El nombre de usuario ya se encuentra en uso.\n");
                        isValid = false;
                    }
                }

                // Validate password (use a separate method for complexity checks, for example)
                if (clave.isEmpty()) {
                    errorMessage.append("Ingrese una contraseña.\n");
                    isValid = false;
                } else {
                    if (!gestionConsecionaria.empleadosController.validarClave(clave)) { // Call a method for password complexity checks
                        errorMessage.append("La contraseña debe cumplir con los requisitos de complejidad.\n");
                        isValid = false;
                    } else if (!Arrays.equals(textClave.getPassword(), textConfirmacionClave.getPassword())) {
                        errorMessage.append("Las contraseñas no coinciden.\n");
                        isValid = false;
                    }
                }


                if (isValid) {
                    // Valid data, proceed with employee creation using gestionConsecionaria
                    // ...
                    JOptionPane.showMessageDialog(null, "El empleado ha sido creado correctamente.");
                    gestionConsecionaria.empleadosController.crearEmpleado(nombre,apellido,dni,usuario,clave, new String(textClaveAdmin.getPassword()));
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, errorMessage.toString(), "Error de validación", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
