package com.framallo90.AGestionConsecionaria;
import com.framallo90.Automovil.Controller.AutomovilController;
import com.framallo90.Automovil.Model.Repository.AutomovilRepository;
import com.framallo90.Automovil.View.AutomovilView;
import com.framallo90.Comprador.Controller.CompradorController;
import com.framallo90.Comprador.Model.Repository.CompradorRepository;
import com.framallo90.Empleados.Controller.EmpleadosController;
import com.framallo90.Empleados.Model.Repository.EmpleadosRepository;
import com.framallo90.Empleados.View.EmpleadosView;
import com.framallo90.GUI.Pantalla;
import com.framallo90.Login.Login;
import com.framallo90.MetodoDePago.Controller.MetodoController;
import com.framallo90.MetodoDePago.View.MetodoView;
import com.framallo90.Venta.Controller.VentaController;
import com.framallo90.Venta.Model.Repository.VentaRepository;
import com.framallo90.Venta.View.VentaView;

public class GestionConsecionaria {
    private CompradorView compradorView;
    private EmpleadosView empleadosView;
    private MetodoView metodoView;
    private AutomovilView automovilView;
    private VentaView ventaView ;
    private CompradorRepository compradorRepository;
    private EmpleadosRepository empleadosRepository;
    private AutomovilRepository automovilRepository;
    private VentaRepository ventaRepository ;
    private static  CompradorController compradorController;
    private static  EmpleadosController empleadosController ;
    private static  AutomovilController automovilController;
    private static  VentaController ventaController;
    private static Login login;
    public static void iniciar(){

        ejecucion(login);
    }
    private static void ejecucion(Login login){
        new Pantalla(login,compradorController, automovilController,empleadosController,ventaController);
    }
}