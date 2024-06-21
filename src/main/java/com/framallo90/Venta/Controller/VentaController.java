package com.framallo90.Venta.Controller;

import com.framallo90.Automovil.Controller.AutomovilController;
import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.Comprador.Controller.CompradorController;
import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Empleados.Controller.EmpleadosController;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.MetodoDePago.Controller.MetodoController;
import com.framallo90.MetodoDePago.Model.Entity.MetodoDePago;
import com.framallo90.Venta.Model.Entity.Venta;
import com.framallo90.Venta.Model.Repository.VentaRepository;
import com.framallo90.Venta.View.VentaView;
import com.framallo90.consola.Consola;

import java.time.LocalDate;

public class VentaController {
    private static EmpleadosController empleadosController;
    private static CompradorController compradorController;
    private static AutomovilController automovilController;
    private static MetodoController metodoController;
    private static VentaView ventaView;
    private static VentaRepository ventaRepository;

    public VentaController(EmpleadosController empleadosController, CompradorController compradorController, AutomovilController automovilController, MetodoController metodoController, VentaView ventaView, VentaRepository ventaRepository) {
        VentaController.empleadosController = empleadosController;
        VentaController.compradorController = compradorController;
        VentaController.automovilController = automovilController;
        VentaController.metodoController = metodoController;
        VentaController.ventaView = ventaView;
        VentaController.ventaRepository = ventaRepository;
    }

    //documentar
    public void add() throws InvalidIdNotFound {
        Empleados empleados = EmpleadosController.find(Consola.ingresarXInteger("id del empleado:"));
        if (empleados == null) {
            throw new InvalidIdNotFound("empleado no encontrado");
        }
        Comprador comprador = CompradorController.find(Consola.ingresarXInteger("id del comprador:"));
        if (comprador == null) throw new InvalidIdNotFound("comprador no encontrado");
        Automovil automovil = automovilController.find(Consola.ingresarXInteger("id del automovil"));
        if (automovil == null)throw new InvalidIdNotFound("automovil no encontrado");
        LocalDate fecha = LocalDate.now();
        MetodoDePago metodoDePago = metodoController.cargarMDP(automovil.getPrecio());
        Venta venta = ventaView.generarVenta(empleados,comprador,automovil,fecha,metodoDePago);
        ventaRepository.add(venta);
    }
    public void show() throws InvalidIdNotFound {
        Venta buscar = ventaRepository.find(Consola.ingresarXInteger("id de la venta"));
        if (buscar != null ) ventaView.mostrarVenta(buscar);
        else throw new InvalidIdNotFound("No se ha encontrado una venta");
    }
    public void update() throws InvalidIdNotFound{
        Venta buscar = ventaRepository.find(Consola.ingresarXInteger("id de la venta"));
        if (buscar == null )throw new InvalidIdNotFound("No se ha encontrado una venta.");
        modifVenta(buscar);
    }
    public void remover() throws InvalidIdNotFound{
        Venta buscar = ventaRepository.find(Consola.ingresarXInteger("id de la venta"));
        if (buscar == null )throw new InvalidIdNotFound("No se ha encontrado una venta.");
        try {
            ventaRepository.remove(buscar.getIdVenta());
        } catch (Exception e) {
            Consola.soutString(e.getMessage());
        }
    }

    public void modifVenta(Venta venta){
        while (true){
            ventaView.printMenuModifVenta();
            switch (Consola.ingresarXInteger("eleccion")){
                case 1: //empleado
                    empleadosController.modificarEmpleado(venta.getEmpleados());
                    break;
                case 2: //comprador
                    compradorController.update(venta.getComprador());
                    break;
                case 3: //automovil
                    venta.setAutomovil(automovilController.cambiarCoche(venta.getAutomovil()));
                    break;
                case 4: //fecha
                    updateFecha(venta.getFecha());
                    break;
                case 5: //mtodo de pago
                    metodoController.updateMDP(venta.getTransaccion(), venta.getAutomovil().getPrecio());
                    break;
                case 0: //salir
                    return;
                default:
                    Consola.soutString("saliendo");
                    break;
            }
        }
    }

    public void updateFecha(LocalDate fecha){
        while (true) {
            ventaView.printMenuModifFecha();
            Integer choice = Consola.ingresarXInteger("eleccion");
            switch (choice) {
                case 1:
                    fecha = cambiarDia(fecha);
                    break;
                case 2:
                    fecha = cambiarMes(fecha);
                    break;
                case 3:
                    fecha = cambiarAnio(fecha);
                    break;
                case 0:
                    System.out.println("Saliendo del menú de modificación.");
                    return;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }
    private  LocalDate cambiarDia(LocalDate localDate) {
        int day = Consola.ingresarXInteger("nuevo día");
        try {
            return localDate.withDayOfMonth(day);
        } catch (IllegalArgumentException e) {
            System.out.println("Día inválido para el mes actual. Intente nuevamente.");
            return localDate;
        }
    }

    private LocalDate cambiarMes(LocalDate localDate) {
        Integer month = Consola.ingresarXInteger("nuevo mes");
        try {
            return localDate.withMonth(month);
        } catch (IllegalArgumentException e) {
            System.out.println("Mes inválido. Intente nuevamente.");
            return localDate;
        }
    }

    private static LocalDate cambiarAnio(LocalDate localDate) {
        Integer year = Consola.ingresarXInteger("nuevo año");
        return localDate.withYear(year);
    }

    public void menuVentas(){
        int eleccion;
        while (true){
            ventaView.printMenuVentas();
            eleccion = Consola.ingresarXInteger("eleccion");
            switch(eleccion){
                case 0: //salir
                    Consola.soutString("saliendo...");
                    return;
                case 1://agregar venta
                        try {
                            this.add();
                            break;
                        } catch (InvalidIdNotFound e) {
                            Consola.soutString(e.getMessage());
                        }
                    break;
                case 2://mostrar
                    while(true){
                        try {
                            this.show();
                            break;
                        } catch (InvalidIdNotFound e) {
                            Consola.soutString(e.getMessage());
                        }
                    }
                    break;
                case 3: //modificar una vnta
                    try {
                        this.update();
                        break;
                    } catch (InvalidIdNotFound e) {
                        Consola.soutString(e.getMessage());
                    }
                    break;
                case 4://remover
                    try {
                        this.remover();
                        break;
                    } catch (InvalidIdNotFound e) {
                        Consola.soutString(e.getMessage());
                    }
                    break;
                case 5: //mostrar todas
                    ventaView.mostrarHistorial(ventaRepository.getMap());
                    break;
                default:
                    Consola.soutString("ingresar un dato valido");
                    break;
            }
        }
    }
}