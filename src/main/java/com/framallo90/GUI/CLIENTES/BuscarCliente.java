package com.framallo90.GUI.CLIENTES;

import com.framallo90.Automovil.Controller.AutomovilController;
import com.framallo90.Comprador.Controller.CompradorController;
import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Empleados.Controller.EmpleadosController;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.Login.Login;
import com.framallo90.UsuarioAbstracta.view.UsuarioView;
import com.framallo90.Venta.Controller.VentaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscarCliente extends JFrame {
    private Comprador selectedComprador;
    public ClienteEncontrado callback;
    private JPanel buscarCliente;
    private JPanel panel;
    private JTextField dniInput;
    private JButton btnBuscar;
    private JTextField apellidoField;
    private JTextField dniField;
    private JTextField emailField;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JTextField nombreField;

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
    public BuscarCliente(Login login, CompradorController compradorController,
                         AutomovilController automovilController,
                         EmpleadosController empleadosController,
                         VentaController ventaController, ClienteEncontrado callback)  {
        setContentPane(buscarCliente);
        setTitle("Buscar cliente");
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
                new Clientes(login, compradorController,automovilController,empleadosController,ventaController);
                dispose();
            }
        });

        //BUSCAR
        btnBuscar.addActionListener(new ActionListener() {
            Integer dni;
            String dniValue=null;
            @Override
            public void actionPerformed(ActionEvent e) {
                dniValue = dniInput.getText();
                dni = Integer.parseInt(dniValue);
                if (UsuarioView.isValidDni(dni)) {
                    // Valid DNI, perform search based on your logic (replace with your search implementation)
                    Comprador comprador = CompradorController.find(dni);
                    if (comprador != null) {
                        nombreField.setText(comprador.getNombre());
                        apellidoField.setText(comprador.getApellido());
                        emailField.setText(comprador.getEmail());
                        dniField.setText(dniValue); // Pre-fill DNI field (optional)
                        btnAceptar.setEnabled(true); // Enable "Aceptar" button
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                        // Reset fields and disable button (optional)
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "DNI inválido.");
                    // Reset fields and disable button (optional)
                }
            }
        });

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedComprador != null) {
                    // Cliente encontrado, call the callback with the selectedComprador
                    callback.clienteEncontrado(selectedComprador);
                    dispose(); // Close the window
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                }
            }
        });
    }
    public interface ClienteEncontrado {
        void clienteEncontrado(Comprador comprador);
    }
}
