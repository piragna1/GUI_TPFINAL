package com.framallo90.GUI.CLIENTES;

import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.GUI.CLIENTES.auxiliar.ClienteEncontrado;
import com.framallo90.UsuarioAbstracta.view.UsuarioView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarCliente extends JFrame{
    private GestionConsecionaria gestionConsecionaria;
    private Comprador comprador = ClienteEncontrado.comprador;
    private JTextField nuevoNombre;
    private JTextField nuevoApellido;
    private JTextField nuevoDni;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JLabel nombreActual;
    private JLabel apellidoActual;
    private JLabel dniActual;

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
    public ModificarCliente(GestionConsecionaria gestionConsecionaria,
                            Comprador comprador) throws HeadlessException {
        nombreActual.setText(this.comprador.getNombre());
        apellidoActual.setText(this.comprador.getApellido());
        dniActual.setText(this.comprador.getDni());

        //CANCELAR
        this.cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Clientes(gestionConsecionaria,comprador);
                new Clien
                dispose();
            }
        });
        //ACEPTAR
        this.aceptarButton.addActionListener(new ActionListener() {
            String nombre=nuevoNombre.toString(),
                    apellido=nuevoApellido.toString(),
                    dni=nuevoDni.toString();

            @Override
            public void actionPerformed(ActionEvent e) {
                //VALIDAR DNI
                //COMPROBAR SI EXISTE EL DNI PRIMERO

            }
        });

        nuevoNombre.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }

            /**
             * Gives notification that a portion of the document has been
             * removed.  The range is given in terms of what the view last
             * saw (that is, before updating sticky positions).
             *
             * @param e the document event
             */
            @Override
            public void removeUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }

            /**
             * Gives notification that an attribute or set of attributes changed.
             *
             * @param e the document event
             */
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

            /**
             * Gives notification that a portion of the document has been
             * removed.  The range is given in terms of what the view last
             * saw (that is, before updating sticky positions).
             *
             * @param e the document event
             */
            @Override
            public void removeUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }

            /**
             * Gives notification that an attribute or set of attributes changed.
             *
             * @param e the document event
             */
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

            /**
             * Gives notification that a portion of the document has been
             * removed.  The range is given in terms of what the view last
             * saw (that is, before updating sticky positions).
             *
             * @param e the document event
             */
            @Override
            public void removeUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }

            /**
             * Gives notification that an attribute or set of attributes changed.
             *
             * @param e the document event
             */
            @Override
            public void changedUpdate(DocumentEvent e) {
                habilitarDesHabilitarBoton();
            }
        });
    }

    private void habilitarDesHabilitarBoton() {
        String nuevoNombreS = nuevoNombre.getText().trim();
        String nuevoApellidoS = nuevoApellido.getText().trim();
        String nuevoDniS = nuevoDni.getText().trim();

        boolean camposVacios = nuevoNombreS.isEmpty() || nuevoApellidoS.isEmpty() || nuevoDniS.isEmpty();
        boolean dniValido = UsuarioView.isValidDni(nuevoDniS);
        boolean dniExiste = gestionConsecionaria.compradorController.existeDni(nuevoDniS);

        aceptarButton.setEnabled(!camposVacios && dniValido && !dniExiste);
    }

}
