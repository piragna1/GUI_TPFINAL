package com.framallo90.Venta.Controller;
import com.framallo90.AGestionConsecionaria.GestionConsecionaria;
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
/**
 * Controlador que gestiona las operaciones relacionadas con las ventas de automóviles.
 * Permite agregar, mostrar, actualizar y eliminar ventas, además de proporcionar un menú
 * interactivo para manejar estas operaciones.
 *
 * Ejemplo de uso:
 * ```
 * VentaController ventaController = new VentaController(...);
 * ventaController.menuVentas();
 * ```
 *
 * @author Framallo
 * @version 1.0
 */
public class VentaController {
    private final CompradorController compradorController;
    private final EmpleadosController empleadosController;
    private final AutomovilController automovilController;
    private final VentaView ventaView;
    private final VentaRepository ventaRepository;
    public static MetodoController metodoController;
    /**
     * Constructor que inicializa el controlador de ventas con los controladores y repositorios necesarios.
     *
     * @param ventaView           Vista de ventas.
     * @param ventaRepository     Repositorio de ventas.
     */
    public VentaController(CompradorController compradorController, EmpleadosController empleadosController, AutomovilController automovilController, VentaView ventaView, VentaRepository ventaRepository, MetodoController metodoController) {
        this.compradorController = compradorController;
        this.empleadosController = empleadosController;
        this.automovilController = automovilController;
        this.ventaView = ventaView;
        this.ventaRepository = ventaRepository;
        VentaController.metodoController = metodoController;
    }
    /**
     * Agrega una nueva venta al sistema. Permite seleccionar un empleado vendedor, un comprador,
     * un automóvil en stock, generar el método de pago y registrar la venta en el repositorio.
     *
     * @throws InvalidIdNotFound Si no se encuentra el empleado, comprador o automóvil correspondiente.
     */
    public void add() throws InvalidIdNotFound {
        // Selección del empleado vendedor
        this.empleadosController.mostrarHistorial();
        Empleados empleados = this.empleadosController.find(Consola.ingresarXInteger("el ID del empleado Vendedor"));
        if (empleados == null) {
            throw new InvalidIdNotFound("El Empleado NO se encuentra registrado.");
        }
        // Selección del comprador
        this.compradorController.verHisorial();
        Comprador comprador = this.compradorController.find();
        if (comprador == null) {
            throw new InvalidIdNotFound("El Comprador NO se encuentra registrado.");
        }

        // Selección del automóvil en stock
        this.automovilController.mostrarAutomovilesEnStock();
        Integer id = Consola.ingresarXInteger("el ID del Automovil en Stock");
        Automovil automovil =this.automovilController.find(id);
        if (automovil == null) {
            throw new InvalidIdNotFound("El Automovil NO se encuentra registrado.");
        }

        // Generación del método de pago
        LocalDate fecha = LocalDate.now();
        MetodoDePago metodoDePago = VentaController.metodoController.cargarMDP(automovil.getPrecio());
        Venta venta = this.ventaView.generarVenta(empleados,comprador,automovil,fecha,metodoDePago);
        this.ventaRepository.add(venta);
        this.automovilController.borrarAutomovilEnStockPorId(id);

    }
    /**
     * Muestra los detalles de una venta específica seleccionada por su ID.
     */
    public void show() {
        try{
            Venta buscar = this.ventaRepository.find(Consola.ingresarXInteger("id de la venta"));
            if (buscar != null)
                this.ventaView.mostrarVenta(buscar);
            else Consola.soutAlertString("No existe una venta con el id ingresado.");
        } catch (InvalidIdNotFound e) {
            Consola.soutAlertString(e.getMessage());
        }
    }

    public Venta find() throws InvalidIdNotFound {
        ventaView.mostrarHistorial(ventaRepository.getMap());
        return ventaRepository.find(Consola.ingresarXInteger("id de la venta"));
    }

    /**
     * Elimina una venta existente seleccionada por su ID.
     *
     * @throws InvalidIdNotFound Si no se encuentra la venta correspondiente.
     */
    public void remover() throws InvalidIdNotFound {
        Venta buscar = this.find();
        if (buscar == null) {
            throw new InvalidIdNotFound("La Venta NO se encuentra registrada.");
        }
        try {
            ventaRepository.restoVenta(buscar);
            this.ventaRepository.remove(buscar.getIdVenta());
        } catch (Exception e) {
            Consola.soutAlertString(e.getMessage());
        }
    }
    /**
     * Permite modificar diferentes aspectos de una venta específica, como el empleado vendedor,
     * el comprador, el automóvil o el método de pago asociado.
     *
     * @param venta La venta a modificar.
     */
    public void modifVenta(Venta venta) {
        while (true) {
            this.ventaView.printMenuModifVenta();
            switch (Consola.ingresarXInteger("un campo para modificar en Venta")) {
                case 1: // Modificar empleado
                    cambiarEmpleadoVenta(venta);
                    break;
                case 2: // Modificar comprador
                    cambiarCompradorVenta(venta);
                    break;
                case 3: //mtodo de pago

                    metodoController.updateMDP(venta, venta.getAutomovil().getPrecio());
                    break;
                case 0: // Salir
                    return;
                default:
                    Consola.soutAlertString("Opción Inválida. Reintentar!.");
                    break;
            }
        }
    }
    public void cambiarEmpleadoVenta(Venta venta){
        this.empleadosController.mostrarHistorial();
        Integer id = Consola.ingresarXInteger("id del vendedor");

        try{
            Empleados nuevo = this.empleadosController.find(id);
            Empleados saco = venta.getEmpleados();
            saco.disminucionAutosVendidos();
            nuevo.aumentoAutosVendidos();
            venta.setEmpleados(nuevo);
            this.empleadosController.update(saco.getId(),saco);
            this.empleadosController.update(nuevo.getId(),nuevo);
            this.ventaRepository.update(venta.getIdVenta(),venta);
        } catch (InvalidIdNotFound e) {
            Consola.soutAlertString(e.getMessage());
        }


    }
    public void cambiarCompradorVenta(Venta venta){
        this.compradorController.verHisorial();
        Integer id = Consola.ingresarXInteger("id del vendedor");

        try{
            Comprador nuevo = CompradorController.find();
            venta.setComprador(nuevo);
            this.ventaRepository.update(venta.getIdVenta(),venta);
        } catch (InvalidIdNotFound e) {
            Consola.soutAlertString(e.getMessage());
        }
    }

    /**
     * Muestra un menú interactivo para manejar las operaciones relacionadas con las ventas.
     * Permite agregar, mostrar, actualizar y eliminar ventas, además de visualizar el historial completo.
     */
    public void menuVentasVendedor() {
        int eleccion = 0;
        do {
            try {
                System.out.println("""
                 --- MENU VENTAS ---
                1. Registrar una Venta
                2. Buscar una Venta
                3. Mostrar Listado de Ventas
                
                0. Volver
                -------------------
                """);
                eleccion = Consola.ingresarXInteger("una opcion del Menu Ventas");
                switch (eleccion) {
                    case 0: // Salir
                        Consola.soutString("Saliste del Menu Ventas.");
                        break;
                    case 1: // Agregar venta
                        this.add();
                        break;
                    case 2: // Mostrar venta
                        this.ventaView.mostrarHistorial(this.ventaRepository.getMap());
                        if (!this.ventaRepository.isEmpty())
                            this.show();
                        break;
                    case 3: // Mostrar todas las ventas
                        this.ventaView.mostrarHistorial(this.ventaRepository.getMap());
                        break;
                    default:
                        Consola.soutAlertString("Opción Inválida. Reintentar!.");
                        break;
                }

            } catch (InvalidIdNotFound e) {
                Consola.soutAlertString(e.getMessage());
            }

        } while (eleccion != 0);

    }
    public void menuVentasAdministrador() {
        int eleccion = 0;
        do {
            try {
                this.ventaView.printMenuVentas();
                eleccion = Consola.ingresarXInteger("una opcion del Menu Ventas");
                switch (eleccion) {
                    case 0: // Salir
                        Consola.soutString("Saliste del Menu Ventas.");
                        break;
                    case 1: // Agregar venta
                        this.add();
                        break;
                    case 2: // Mostrar venta
                        this.ventaView.mostrarHistorial(this.ventaRepository.getMap());
                        if (!this.ventaRepository.isEmpty())
                            this.show();
                        break;
                    case 3: // Modificar venta
                        try {
                            this.ventaView.mostrarHistorial(this.ventaRepository.getMap());
                            Venta ven = this.ventaRepository.find(Consola.ingresarXInteger("Ingrese id de la venta"));
                            modifVenta(ven);
                            ventaRepository.update(ven.getIdVenta(),ven);


                        } catch (InvalidIdNotFound e) {
                            Consola.soutAlertString(e.getMessage());
                        }

                        break;
                    case 4: // Eliminar venta
                        try {
                            this.ventaView.mostrarHistorial(this.ventaRepository.getMap());

                            if (!this.ventaRepository.isEmpty())
                                this.remover();
                            break;
                        } catch (InvalidIdNotFound e) {
                            Consola.soutAlertString(e.getMessage());
                        }
                        break;
                    case 5: // Mostrar todas las ventas
                        this.ventaView.mostrarHistorial(this.ventaRepository.getMap());
                        break;
                    default:
                        Consola.soutAlertString("Opción Inválida. Reintentar!.");
                        break;
                }

            } catch (InvalidIdNotFound e) {
                Consola.soutAlertString(e.getMessage());
            }

        } while (eleccion != 0);

    }


}