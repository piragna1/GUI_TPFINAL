package com.framallo90.GUI.USUARIOS.admin.funcionalidades.modificar;

import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.GUI.USUARIOS.admin.funcionalidades.modificar.auxiliar.CambiarClave;
import com.framallo90.GUI.USUARIOS.auxiliar.EmpleadoEncontrado;
import com.framallo90.UsuarioAbstracta.view.UsuarioView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Modificar extends JFrame{
    private GestionConsecionaria gestionConsecionaria;
    private Empleados empleados;
    private JPanel mainPanel;
    private JPanel panelModificar;
    private JLabel nombreActual;
    private JTextField nuevoNombre;
    private JTextField nuevoApellido;
    private JLabel apellidoActual;
    private JTextField nuevoDni;
    private JLabel dniActual;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JPanel panel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panel7;
    private JPasswordField nuevaClave;
    private JLabel usuarioActual;
    private JPasswordField claveActual;
    private JLabel tipoActual;
    private JTextField nuevoUsuario;
    private JTextField nuevoTipo;

    public Modificar(GestionConsecionaria gestionConsecionaria, Empleados empleados){
        setContentPane(mainPanel);
        setTitle("Modificación Empleado");
        setSize(450,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        this.gestionConsecionaria = gestionConsecionaria;
        this.empleados = empleados;
        nombreActual.setText(empleados.getNombre());
        apellidoActual.setText(empleados.getApellido());
        dniActual.setText(empleados.getDni());
        usuarioActual.setText(empleados.getUsername());
        claveActual.setText(empleados.getPassword());
        tipoActual.setText(empleados.getTipo());

        nuevaClave.setText("");

        //CANCELAR
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
                String nombre = nuevoNombre.getText(),
                        apellido = nuevoApellido.getText(),
                        dni = nuevoDni.getText(),
                        usuario = nuevoUsuario.getText(),
                        clave = Arrays.toString(nuevaClave.getPassword()),
                        tipo = nuevoTipo.getText();


                if (!nombre.isEmpty()) empleados.setNombre(nombre);
                if (!apellido.isEmpty()) empleados.setApellido(apellido);
                if (!dni.isEmpty()){
                    if (UsuarioView.isValidDni(dni))
                        if (gestionConsecionaria.empleadosController.disponibilidadDNI(dni))
                            empleados.setDni(dni);
                        else JOptionPane.showMessageDialog(null, "Ya existe otro empleado con el DNI ingresado.");
                    else JOptionPane.showMessageDialog(null,"Ingresar un DNI válido.");
                }
                if (!usuario.isEmpty()){
                    //VALIDAR USUARIO
                    if (gestionConsecionaria.empleadosController.validarUsername(usuario))
                        empleados.setUsername(usuario);
                    else JOptionPane.showMessageDialog(null, "El nombre de usuario no se encuentra disponible.");
                }
                if (!clave.isEmpty()){
                    if (empleados.getPassword().equals(clave)){
                        new CambiarClave(gestionConsecionaria, empleados);
                    } else JOptionPane.showMessageDialog(null,"La clave ingresada debe coincidir con la actual.");
                }
                if (!tipo.isEmpty()){
                    //Si el tipo ingresado es administrador o ADMINISTRADOR:
                        //Solicitar al usuario que ingrese la clave de administrador.
                            //Si la clave es correcta, cambiar el tipo de usuario a administrador.
                            //En caso contrario mostrar mensaje de error (permitir seguir intentado 2 veces más y cancelar).
                    empleados.setTipo(tipo);
                }
                try {
                    gestionConsecionaria.empleadosController.update(empleados.getId(), empleados);
                    JOptionPane.showMessageDialog(null, "Datos modificados correctamente.");
                    EmpleadoEncontrado.setEmpleados(empleados);
                    dispose();
                } catch (InvalidIdNotFound ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                    dispose();
                }
            }
        });
        //DOCUMENT LISTENERS
        nuevoNombre.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }
        });

        nuevoApellido.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }
        });

        nuevoDni.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }
        });

        nuevoUsuario.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }
        });
        nuevaClave.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }
        });
        nuevoTipo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }
        });
    }
    private void habilitarDesHabilitarBoton() {

        boolean dniValido = false;
        if (nuevoDni.getText().trim().isEmpty()) dniValido = true;
        else {
            if (!gestionConsecionaria.compradorController.existeDni(nuevoDni.getText().trim())||
                    this.empleados.getDni().equals(nuevoDni.getText().trim())) dniValido = true;
        }

        boolean usuarioValido = false;
        if (nuevoUsuario.getText().trim().isEmpty()) usuarioValido = true;
        else {
            if (gestionConsecionaria.empleadosController.validarUsername(nuevoUsuario.getText())||
                    this.empleados.getUsername().equals(nuevoUsuario.getText().trim())) usuarioValido = true;
        }

        boolean claveValida;
        if (Arrays.toString(nuevaClave.getPassword()).trim().isEmpty())
            claveValida=true;
        else{
            claveValida = Arrays.toString(claveActual.getPassword()).equals(Arrays.toString(nuevaClave.getPassword()));
        }
        aceptarButton.setEnabled(true);
    }

    public static void actualizarClaveEmpleado(Empleados empleados,String clave){
        empleados.setPassword(clave);
    }

}
