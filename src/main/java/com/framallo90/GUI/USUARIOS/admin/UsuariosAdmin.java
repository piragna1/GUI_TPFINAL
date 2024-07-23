package com.framallo90.GUI.USUARIOS.admin;

import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.GUI.Interfaces.EmpleadoEncontradoListener;
import com.framallo90.GUI.MenuAdmin;
import com.framallo90.GUI.USUARIOS.admin.funcionalidades.agregar.Agregar;
import com.framallo90.GUI.USUARIOS.admin.funcionalidades.agregar.Buscar;
import com.framallo90.GUI.USUARIOS.auxiliar.EmpleadoEncontrado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.framallo90.GUI.USUARIOS.auxiliar.EmpleadoEncontrado.empleados;

public class UsuariosAdmin extends JFrame implements EmpleadoEncontradoListener {
    private JPanel menuUsuarios;
    private JPanel panel;
    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnExistentes;
    private JButton btnVolver;
    private JLabel usuario;
    private JButton buscarButton;

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
    public UsuariosAdmin(GestionConsecionaria gestionConsecionaria) throws HeadlessException {
        setContentPane(panel);
        setTitle("Menú usuarios - administrador");
        setSize(450,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        EmpleadoEncontrado.addListener(UsuariosAdmin.this);
        if (empleados != null ){
            usuario.setText(empleados.toString());
        }else
            usuario.setText("");
        //VOLVER
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                EmpleadoEncontrado.setEmpleados(null);
                EmpleadoEncontrado.removeListener(UsuariosAdmin.this);
                dispose();
                new MenuAdmin(gestionConsecionaria);
            }
        });
        //AGREGAR
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Agregar(gestionConsecionaria);
            }
        });
        //BUSCAR
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Buscar(gestionConsecionaria, null);
            }
        });
        //MODIFICAR
        //ELIMINAR
        //VER EXISTENTES
    }

    @Override
    public void onEmpleadoChanged(Empleados empleados) {
    }
}
