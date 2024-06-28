package com.framallo90.Venta.View;

import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.MetodoDePago.Model.Entity.MetodoDePago;
import com.framallo90.Venta.Model.Entity.Venta;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Map;

/**
 * Vista que maneja la presentación de las ventas de automóviles y operaciones relacionadas.
 */
public class VentaView {
    private static DecimalFormat df = new DecimalFormat("#,##0.00");

    /**
     * Genera una nueva venta con los parámetros proporcionados.
     *
     * @param empleados   El empleado que realiza la venta.
     * @param comprador   El comprador que realiza la compra.
     * @param automovil   El automóvil vendido.
     * @param fecha       La fecha de la venta.
     * @param transaccion El método de pago utilizado.
     * @return Una nueva instancia de Venta con los datos proporcionados.
     */
    public Venta generarVenta(Empleados empleados,
                              Comprador comprador,
                              Automovil automovil,
                              LocalDate fecha,
                              MetodoDePago transaccion) {
        return new Venta(empleados, comprador, automovil, fecha, transaccion);
    }

    /**
     * Muestra por consola los detalles de una venta específica.
     *
     * @param venta La venta cuyos detalles se van a mostrar.
     */
    public void mostrarVenta(Venta venta) {

        System.out.println("======================================");
        System.out.println("VentaID: " + venta.getIdVenta());
        System.out.println("Fecha: " + venta.getFecha());
        System.out.println("Vendedor: " + venta.getEmpleados().getApellido() + ", " + venta.getEmpleados().getNombre());
        System.out.println("Cliente: " + venta.getComprador().getApellido() + ", " + venta.getComprador().getNombre());
        System.out.println("Detalle de la venta: " + venta.getAutomovil().getMarca() + ", " + venta.getAutomovil().getModelo() + ", " + venta.getAutomovil().getAnio());

        System.out.println("Precio: $" + df.format(venta.getAutomovil().getPrecio()));
        System.out.println("Metodo de pago: " + venta.getTransaccion().getTipo());
        System.out.println("Cantidad de cuotas: " + venta.getTransaccion().getCuotas());
        System.out.println("Precio Financiado: $" + df.format(venta.getTransaccion().getPrecioFinanciado()));
        System.out.println("======================================");
    }

    /**
     * Muestra por consola el historial de ventas almacenado en un mapa.
     *
     * @param map El mapa que contiene las ventas indexadas por su ID.
     */
    public void mostrarHistorial(Map<Integer, Venta> map) {
        if (map.isEmpty())
            System.out.println("Aún no hay ventas registradas...");
        else
            map.values().forEach(v->mostrarVenta(v));
    }

    /**
     * Imprime por consola el menú de opciones para modificar una venta.
     */
    public void printMenuModifVenta() {
        System.out.println("""
                -> MODIFICAR VENTA:
                1. Empleado
                2. Comprador
                3. Metodo de Pago
                
                0. Volver
                """);
    }

    /**
     * Imprime por consola el menú principal de opciones relacionadas con ventas.
     */
    public void printMenuVentas() {
        System.out.println("""
                 --- MENU VENTAS ---
                1. Registrar una Venta
                2. Buscar una Venta
                3. Modificar Venta
                4. Eliminar Venta
                5. Mostrar Listado de Ventas
                
                0. Volver
                -------------------
                """);
    }
}