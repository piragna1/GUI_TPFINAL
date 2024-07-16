package com.framallo90.GUI.CLIENTES;

import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.UsuarioAbstracta.view.UsuarioView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarCliente extends JFrame{
    private GestionConsecionaria gestionConsecionaria;
    private Comprador comprador ;
    private JTextField nuevoNombre;
    private JTextField nuevoApellido;
    private JTextField nuevoDni;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JLabel nombreActual;
    private JLabel apellidoActual;
    private JLabel dniActual;
    private JPanel menuModificar;
    private JTextField nuevoCorreo;
    private JLabel correoActual;

    public ModificarCliente(GestionConsecionaria gestionConsecionaria,
                            Comprador comprador) {
        setContentPane(menuModificar);
        setTitle("Menu modificación");
        setSize(450,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        this.gestionConsecionaria = gestionConsecionaria;
        this.comprador = comprador;
        nombreActual.setText(this.comprador.getNombre());
        apellidoActual.setText(this.comprador.getApellido());
        dniActual.setText(this.comprador.getDni());
        correoActual.setText(this.comprador.getEmail());
        //CANCELAR
        this.cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        //ACEPTAR
        this.aceptarButton.addActionListener(new ActionListener() {
            String nombre=nuevoNombre.toString(),
                    apellido=nuevoApellido.toString(),
                    dni=nuevoDni.toString(),
                    email = nuevoCorreo.toString();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (nombre.isEmpty()) nombre = comprador.getNombre();
                if (apellido.isEmpty()) apellido = comprador.getApellido();
                if (dni.isEmpty()) dni = comprador.getDni();
                if (email.isEmpty()) email = comprador.getEmail();
                Comprador nuevo = new Comprador(nombre,apellido,dni,email);
                try {
                    gestionConsecionaria.compradorController.updateComprador(comprador.getId(),
                            nuevo);
                    JOptionPane.showMessageDialog(null,"Datos modificados correctamente.");
                    dispose();
                } catch (InvalidIdNotFound ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                    dispose();
                }
            }
        });

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

        nuevoCorreo.getDocument().addDocumentListener(new DocumentListener() {
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
        String nuevoDniS = nuevoDni.getText().trim();
        String nuevoCorreoS = nuevoCorreo.getText().trim();

        boolean dniValido = false;
        if (nuevoDniS.isEmpty()) dniValido = true;
        else {
            if (!gestionConsecionaria.compradorController.existeDni(nuevoDniS)||
                this.comprador.getDni().equals(nuevoDniS)) dniValido = true;
        }
        boolean correoValido = false;
        if (nuevoCorreoS.isEmpty()) correoValido = true;
        else {
            if (!gestionConsecionaria.compradorController.existeCorreo(nuevoCorreoS)||
                    this.comprador.getEmail().equals(nuevoCorreoS)) correoValido = true;
        }
        aceptarButton.setEnabled(dniValido && correoValido);
    }
}
