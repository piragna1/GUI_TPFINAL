package com.framallo90.GUI.CLIENTES.admin;

import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.GUI.CLIENTES.admin.funcionalidades.AddCliente;
import com.framallo90.GUI.CLIENTES.admin.funcionalidades.BuscarCliente;
import com.framallo90.GUI.CLIENTES.admin.funcionalidades.ModificarCliente;
import com.framallo90.GUI.CLIENTES.admin.funcionalidades.VerHistorial;
import com.framallo90.GUI.CLIENTES.auxiliar.ClienteEncontrado;
import com.framallo90.GUI.Interfaces.ClienteEncontradoListener;
import com.framallo90.GUI.MenuAdmin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientesAdmin extends JFrame implements ClienteEncontradoListener {
    private JPanel menuClientes;
    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnVerHistorial;
    private JButton btnVolver;
    private JTextField selectedDni;
    private JTextField selectedApellido;
    private JTextField selectedNombre;

    public ClientesAdmin(GestionConsecionaria gestionConsecionaria){
        setContentPane(menuClientes);
        setTitle("Menu clientes");
        setSize(450,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        ClienteEncontrado.addListener(this);
        if (comprador != null){
            selectedDni.setText(comprador.getDni());
            selectedNombre.setText(comprador.getNombre());
            selectedApellido.setText(comprador.getApellido());
        }
        //VOLVER
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuAdmin(gestionConsecionaria);
                ClienteEncontrado.comprador = null;
                ClienteEncontrado.removeListener(ClientesAdmin.this);
                dispose();
            }
        });
        //AGREGAR COMPRADOR
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GENERO CLIENTE
                new AddCliente(gestionConsecionaria);
            }
        });

        // MODIFICAR COMPRADOR (placeholder for future implementation)
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ClienteEncontrado.comprador != null){
                    new ModificarCliente(gestionConsecionaria,ClienteEncontrado.comprador);
                } else JOptionPane.showMessageDialog(null, "Aún no hay un cliente seleccionado.");
            }
        });
        //ELIMINAR COMPRADOR
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ClienteEncontrado.comprador != null) {
                    // Eliminar el comprador seleccionado (implement logic)
                    gestionConsecionaria.compradorController.remove(ClienteEncontrado.comprador.getId());
                    // Reset selectedComprador after deletion
                    ClienteEncontrado.comprador = null;
                    selectedDni.setText("");
                    selectedNombre.setText("");
                    selectedApellido.setText("");
                    JOptionPane.showMessageDialog(null,"El comprador ha sido removido correctamente.");
                } else JOptionPane.showMessageDialog(null, "Aún no hay un cliente seleccionado.");
            }
        });
        //BUSCAR COMPRADOR
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create BuscarCliente instance with the callback
                new BuscarCliente(gestionConsecionaria, ClienteEncontrado.comprador);
            }
        });
        //VER HISTORIAL
        btnVerHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VerHistorial(gestionConsecionaria);
            }
        });
    }

    @Override
    public void onCompradorChanged(Comprador comprador) {
        selectedDni.setText(comprador.getDni()!=null ? comprador.getDni() : "");
        selectedNombre.setText(comprador.getNombre()!=null ? comprador.getNombre():"");
        selectedApellido.setText(comprador.getApellido()!=null ? comprador.getApellido():"");
    }
}
