package com.framallo90.AGestionConsecionaria;
import com.framallo90.Automovil.Controller.AutomovilController;
import com.framallo90.Comprador.Controller.CompradorController;
import com.framallo90.Empleados.Controller.EmpleadosController;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.Login.Login;
import com.framallo90.Venta.Controller.VentaController;
import com.framallo90.consola.Consola;
/**
 * Clase principal que gestiona la aplicación de una concesionaria.
 * Permite la gestión de compradores, empleados, automóviles, ventas y métodos de pago.
 */
public class GestionConsecionaria {
    public  CompradorController compradorController;
    public  EmpleadosController empleadosController;
    public  AutomovilController automovilController;
    public VentaController ventaController;
    public Login login;
    /**
     * Constructor de la clase GestionConsecionaria.
     * @param compradorController Controlador para la gestión de compradores.
     * @param empleadosController Controlador para la gestión de empleados.
     * @param automovilController Controlador para la gestión de automóviles.
     * @param ventaController Controlador para la gestión de ventas.
     */
    public GestionConsecionaria(CompradorController compradorController, EmpleadosController empleadosController,
                                AutomovilController automovilController,
                                VentaController ventaController, Login login) {
        this.compradorController = compradorController;
        this.empleadosController = empleadosController;
        this.automovilController = automovilController;
        this.ventaController = ventaController;
        this.login = login;
    }

    /**
     * Método para iniciar la aplicación de gestión de la concesionaria.
     * Incluye el proceso de login y la navegación de menús según el tipo de usuario.
     */
    public void iniciar() {
        Empleados empleadoIngresado = null;
        int eleccion = 0;
        do {
            // LOGIN
            do {
                Consola.printMenuLogin();
                eleccion = Consola.ingresarXInteger("una opcion del Menu");
                if (eleccion == 0) {
                    return;
                }
                else if (eleccion == 1) {
                    empleadoIngresado = login.login();
                    if (empleadoIngresado == null)
                        Consola.soutAlertString("Las Credenciales son Inválidas!. Vuelve a Intentarlo");
                }
                else
                    Consola.soutAlertString("Porfavor ingrese una opción válida (1/0). ");

            } while (empleadoIngresado == null);


            if (empleadoIngresado.getTipo().equalsIgnoreCase("admin") || empleadoIngresado.getTipo().equalsIgnoreCase("administrador")) {
                this.menuAdmin(empleadoIngresado);
            } else if (empleadoIngresado.getTipo().equalsIgnoreCase("vendedor")) {
                this.menuVendedor(empleadoIngresado);
            }

        } while (true);
    }

    /**
     * Método privado para manejar el menú de administrador.
     * @param empleadoIngresado Objeto Empleados que representa al usuario administrador.
     */
    private void menuAdmin(Empleados empleadoIngresado) {
        Integer eleccion;
        do {
            Consola.printMenuAdministrador();
            eleccion = Consola.ingresarXInteger("una opcion del Menu Administrador");
            switch (eleccion) {
                case 0: // salir
                    empleadoIngresado = null;
                    break;
                case 1: // gestion clientes
                    compradorController.compradorMenu();
                    break;
                case 2: // gestion ventas
                    ventaController.menuVentasAdministrador();
                    break;
                case 3: // gestion carros
                    automovilController.menuAutomovilAdmin();
                    break;
                case 4: // gestion usuarios
                    empleadosController.menuControllerEmpleados();
                    break;
                default: // opcion no reconocida
                    Consola.soutAlertString("Opción Inválida. Reintentar!.");
                    break;
            }
        } while (eleccion != 0);

    }

    /**
     * Método privado para manejar el menú de vendedor.
     * @param empleadoIngresado Objeto Empleados que representa al usuario vendedor.
     */
    private void menuVendedor(Empleados empleadoIngresado) {
        Integer eleccion;
        do {
            Consola.printMenuVendedor();
            eleccion = Consola.ingresarXInteger("una opcion del Menu Vendedor");
            switch (eleccion) {
                case 0: // salir
                    empleadoIngresado = null;
                    break;
                case 1: // gestion clientes
                    compradorController.compradorMenu();
                    break;
                case 2: // gestion ventas
                    ventaController.menuVentasVendedor();
                    break;
                case 3: // gestion carros
                    automovilController.menuAutomovilVendedor();
                    break;
            }

        } while (eleccion != 0);
    }
}