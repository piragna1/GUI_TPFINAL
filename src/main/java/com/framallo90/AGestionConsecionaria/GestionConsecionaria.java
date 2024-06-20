package com.framallo90.AGestionConsecionaria;

import com.framallo90.Automovil.Controller.AutomovilController;
import com.framallo90.Automovil.Model.Repository.AutomovilRepository;
import com.framallo90.Automovil.View.AutomovilView;
import com.framallo90.Comprador.Controller.CompradorController;
import com.framallo90.Comprador.Model.Repository.CompradorRepository;
import com.framallo90.Comprador.View.CompradorView;
import com.framallo90.Empleados.Controller.EmpleadosController;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.Empleados.Model.Repository.EmpleadosRepository;
import com.framallo90.Empleados.View.EmpleadosView;
import com.framallo90.Excepciones.InicioSesionException;
import com.framallo90.GUI.Pantalla;
import com.framallo90.Login.Login;
import com.framallo90.MetodoDePago.Controller.MetodoController;
import com.framallo90.MetodoDePago.View.MetodoView;
import com.framallo90.Venta.Controller.VentaController;
import com.framallo90.Venta.Model.Repository.VentaRepository;
import com.framallo90.Venta.View.VentaView;
import com.framallo90.consola.Consola;

public class GestionConsecionaria {
    private static final CompradorView compradorView = new CompradorView();
    private static final CompradorRepository compradorRepository = new CompradorRepository();
    private static final CompradorController compradorController = new CompradorController(compradorView,compradorRepository);
    private static final EmpleadosView empleadosView = new EmpleadosView();
    private static final EmpleadosRepository empleadosRepository = new EmpleadosRepository();
    private static final EmpleadosController empleadosController = new EmpleadosController(empleadosRepository,empleadosView);
    private static final MetodoView metodoView = new MetodoView();
    private static final MetodoController metodoController = new MetodoController(metodoView);
    private static final AutomovilView automovilView = new AutomovilView();
    private static final AutomovilRepository automovilRepository = new AutomovilRepository();
    private static final AutomovilController automovilController = new AutomovilController(automovilRepository,automovilView);
    private static final VentaView ventaView = new VentaView();
    private static final VentaRepository ventaRepository = new VentaRepository();
    private static final VentaController ventaController = new VentaController(empleadosController,compradorController,automovilController,metodoController,ventaView,ventaRepository);

    public static void iniciar(){
        Login login = new Login();
        ejecucion(login);
    //    CompradorView compradorView = new CompradorView();
    //    CompradorRepository compradorRepository = new CompradorRepository();
    //    CompradorController compradorController = new CompradorController(compradorView,compradorRepository);
    //    EmpleadosView empleadosView = new EmpleadosView();
    //    EmpleadosRepository empleadosRepository = new EmpleadosRepository();
    //    EmpleadosController empleadosController = new EmpleadosController(empleadosRepository,empleadosView);
    //    MetodoView metodoView = new MetodoView();
    //    MetodoController metodoController = new MetodoController(metodoView);
    //    AutomovilView automovilView = new AutomovilView();
    //    AutomovilRepository automovilRepository = new AutomovilRepository();
    //    AutomovilController automovilController = new AutomovilController(automovilRepository,automovilView);
    //    VentaView ventaView = new VentaView();
    //    VentaRepository ventaRepository = new VentaRepository();
    //    VentaController ventaController = new VentaController(empleadosController,compradorController,automovilController,metodoController,ventaView,ventaRepository);

        //---------------------
        /*
        int eleccion = 0;

        do {
            if (empleadoIngresado == null) {
                Consola.printMenuLogin();
                eleccion = Consola.ingresarXInteger("eleccion");
                if (eleccion == 0) return;
                else if (eleccion == 1)
                    empleadoIngresado = login.login();
                else Consola.soutString("Ingresar una opción válida!");
            }
            if (empleadoIngresado==null)
                Consola.soutString("Las credenciales son inválidas. Vuelve a intentarlo");
            else{
                if (empleadoIngresado.getTipo().equalsIgnoreCase("admin") || empleadoIngresado.getTipo().equalsIgnoreCase("administrador")){
                    Consola.printMenuAdministrador();
                    eleccion = Consola.ingresarXInteger("elección");
                    switch (eleccion){
                        case 0: // salir
                            empleadoIngresado = null;
                            break;
                        case 1: // gestion clientes
                            compradorController.compradorMenu();
                            break;
                        case 2: // gestion ventas
                            ventaController.menuVentas();
                            break;
                        case 3: // gestion carros
                            automovilController.menuAutomovilAdmin();
                            break;
                        case 4: // gestion usuarios
                            empleadosController.menuControllerEmpleados();
                            break;
                        default: //opcion no reconocida
                            Consola.soutString("No se reconoce la opción ingresada.");
                            break;
                    }
                }
                else if (empleadoIngresado.getTipo().equalsIgnoreCase("vendedor")){
                    Consola.printMenuVendedor();
                    eleccion = Consola.ingresarXInteger("elección");
                    switch (eleccion){
                        case 0: // salir
                            empleadoIngresado = null;
                            break;
                        case 1: // gestion clientes
                            compradorController.compradorMenu();
                            break;
                        case 2: // gestion ventas
                            ventaController.menuVentas();
                            break;
                        case 3: // gestion carros
                            automovilController.menuAutomovilAdmin();
                    }
                }
                else Consola.soutString("Credenciales incorrectas.");
            }
        } while (true);
         */
    }

    private static void ejecucion(Login login){
        Pantalla pantalla = new Pantalla(login,compradorController);
        /*
        int eleccion = 0;
        Empleados  empleadoIngresado = null;
        do {
            empleadoIngresado = iniciarSesion(login,eleccion,empleadoIngresado);
            if (empleadoIngresado==null) return; //FIN PROGRAMA
            else{
                menu(eleccion,empleadoIngresado);
            }
        } while (true);
         */
    }

    private static Empleados iniciarSesion(Login login, Integer eleccion, Empleados empleadoIngresado){
        Consola.printMenuLogin();
        eleccion = Consola.ingresarXInteger("eleccion");
        if (eleccion == 0) return null; //SALIR DEL PROGRAMA
        else if (eleccion == 1) {
            while (true){
                try{
                    empleadoIngresado = login.login(); //PROCESO LOGIN
                    if (empleadoIngresado != null)
                        break;
                }
                catch (InicioSesionException e){
                    Consola.soutString(e.getMessage());
                }
            }
        }
        else{ Consola.soutString("Ingresar una opción válida!");}
        return empleadoIngresado;
    }

    private static void menu(Integer eleccion,Empleados empleadoIngresado){
        {
            if (empleadoIngresado.getTipo().equalsIgnoreCase("admin")){
                menuAdmin(eleccion, empleadoIngresado);
            }
            else if (empleadoIngresado.getTipo().equalsIgnoreCase("vendedor")){
                menuVendedor(eleccion, empleadoIngresado);
            }
            else Consola.soutString("Credenciales incorrectas.");
        }
    }

    private static void menuAdmin( Integer eleccion,Empleados empleadoIngresado){
            Consola.printMenuAdministrador();
            eleccion = Consola.ingresarXInteger("elección");
            switch (eleccion){
                case 0: // salir
                    empleadoIngresado = null;
                    break;
                case 1: // gestion clientes
                    compradorController.compradorMenu();
                    break;
                case 2: // gestion ventas
                    ventaController.menuVentas();
                    break;
                case 3: // gestion carros
                    automovilController.menuAutomovilAdmin();
                    break;
                case 4: // gestion usuarios
                    empleadosController.menuControllerEmpleados();
                    break;
                default: //opcion no reconocida
                    Consola.soutString("No se reconoce la opción ingresada.");
                    break;
        }
    }

    private static void menuVendedor(Integer eleccion, Empleados empleadoIngresado){
        Consola.printMenuVendedor();
        eleccion = Consola.ingresarXInteger("elección");
        switch (eleccion){
            case 0: // salir
                empleadoIngresado = null;
                break;
            case 1: // gestion clientes
                compradorController.compradorMenu();
                break;
            case 2: // gestion ventas
                ventaController.menuVentas();
                break;
            case 3: // gestion carros
                automovilController.menuAutomovilAdmin();
        }
    }
}
