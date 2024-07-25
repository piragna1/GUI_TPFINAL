package com.framallo90.GUI.USUARIOS.admin.funcionalidades.modificar;

import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.GUI.USUARIOS.admin.funcionalidades.modificar.auxiliar.CambiarClave;
import com.framallo90.GUI.USUARIOS.auxiliar.EmpleadoEncontrado;
import com.framallo90.UsuarioAbstracta.view.UsuarioView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


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
                if (!nuevoNombre.getText().isEmpty())
                    empleados.setNombre(nuevoNombre.getText());
                if (!nuevoApellido.getText().isEmpty())
                    empleados.setApellido(nuevoApellido.getText());
                if (!nuevoDni.getText().isEmpty()){
                    if (UsuarioView.isValidDni(nuevoDni.getText()))
                        if (gestionConsecionaria.empleadosController.disponibilidadDNI(nuevoDni.getText()))
                            empleados.setDni(nuevoDni.getText());
                        else JOptionPane.showMessageDialog(null, "Ya existe otro empleado con el DNI ingresado.");
                    else JOptionPane.showMessageDialog(null,"Ingresar un DNI válido.");
                }
                if (!nuevoUsuario.getText().isEmpty()){
                    //VALIDAR USUARIO
                    if (gestionConsecionaria.empleadosController.validarUsername(nuevoUsuario.getText()))
                        empleados.setUsername(nuevoUsuario.getText());
                    else JOptionPane.showMessageDialog(null, "El nombre de usuario no se encuentra disponible.");
                }
                if (!Arrays.toString(nuevaClave.getPassword()).isEmpty()){
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    String clave2 = encoder.encode(Arrays.toString(claveActual.getPassword()));
                    if (encoder.matches(Arrays.toString(nuevaClave.getPassword()), clave2)){
                        new CambiarClave(gestionConsecionaria, empleados);
                    } else JOptionPane.showMessageDialog(null,"La clave ingresada debe coincidir con la actual.");
                }
                if (!nuevoTipo.getText().isEmpty()){
                    //Si el tipo ingresado es administrador o ADMINISTRADOR:
                    if (nuevoTipo.getText().equalsIgnoreCase("administrador")||
                        nuevoTipo.getText().equalsIgnoreCase("vendedor"))
                        empleados.setTipo(nuevoTipo.getText());
                    else JOptionPane.showMessageDialog(null,"tipo de empleado inválido");
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
    }

    public static void actualizarClaveEmpleado(Empleados empleados,String clave){
        empleados.setPassword(clave);
    }

}