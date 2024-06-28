/**
 * Esta clase proporciona métodos estáticos para la interacción con el usuario a través de la consola.
 *
 * @author Framballo90
 * @since v1.0
 */
package com.framallo90.consola;
import java.util.Scanner;
public class Consola {
    /**
     * Objeto Scanner estático para la lectura de la entrada del usuario.
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Imprime un mensaje en la consola (salida estándar).
     *
     * @param string El mensaje a imprimir.
     */
    public static void soutString(String string) {
        System.out.println(string);
    }

    public static void soutAlertString(String string){
        System.err.println(string);
    }
//ANSI_RED + string + ANSI_RESET
    /**
     * Limpia el buffer del Scanner si hay datos pendientes de lectura.
     *
     * Este método verifica si hay datos sin leer en el buffer del Scanner y, si es así, los consume para evitar problemas de lectura posterior.
     */
    public static void limpiarBuffer() {
        if (scanner.hasNextLine()) {
            scanner.nextLine(); // Consume la línea restante en el buffer
        }
    }
    /**
     * Solicita al usuario que ingrese un String y lo valida para que solo contenga letras y espacios.
     *
     * @param x Un mensaje a mostrar al usuario para indicar qué dato debe ingresar.
     * @return El String ingresado por el usuario.
     * @throws IllegalArgumentException Si el usuario ingresa un dato inválido (no solo letras y espacios).
     */
    public static String ingresarXString(String x) {
        while (true) {
            System.out.println("Ingresar " + x + ": ");
            String s = scanner.nextLine();
            if (s.matches("[a-zA-Z\\s]+")) {
                return s;
            } else {
                soutAlertString("Porfavor ingresar un dato valido!.");
            }
        }
    }

    /**
     * Solicita al usuario que ingrese un String.
     *
     * @param x Un mensaje a mostrar al usuario para indicar qué dato debe ingresar.
     * @return El String ingresado por el usuario.
     */
    public static String ingresarXStringSimple(String x) {
        while (true) {
            System.out.println("Ingresar " + x + ": ");
            return scanner.nextLine();
        }
    }
    /**
     * Solicita al usuario que ingrese un número entero y lo valida para que sea mayor o igual a cero.
     *
     * @param x Un mensaje a mostrar al usuario para indicar qué dato debe ingresar.
     * @return El número entero ingresado por el usuario.
     * @throws IllegalArgumentException Si el usuario ingresa un número negativo.
     */
    public static Integer ingresarXInteger(String x) {
        System.out.println("Ingresar " + x + ": ");
        Integer numero;
        do {
            while (!scanner.hasNextInt()) {
                soutAlertString("El dato ingresado NO es valido. Por favor, ingrese un número entero:");
                scanner.next(); // Limpiar la entrada no válida
            }

            numero = scanner.nextInt();
            scanner.nextLine(); // buffer
            if (numero < 0) {
                soutAlertString("No se aceptan numeros negativos. Ingrese una opcion valida: ");
            }
        } while (numero < 0);
        return numero;
    }

    /**
     * Solicita al usuario que ingrese un número double.
     *
     * @param x Un mensaje a mostrar al usuario para indicar qué dato debe ingresar.
     * @return El número double ingresado por el usuario.
     */
    public static Double ingresarXdouble(String x) {
        System.out.println("Ingresar " + x + ": ");
        while (!scanner.hasNextDouble()) {
            soutAlertString("El dato ingresado NO es valido. Ingrese una opcion valida: ");
            scanner.next(); // Limpiar la entrada no válida
        }
        Double numero = scanner.nextDouble();
        scanner.nextLine(); /// buffer
        return numero;
    }

    /**
     * Solicita al usuario que ingrese una patente de vehículo en el formato AAA-123.
     *
     * @param x Un mensaje a mostrar al usuario para indicar qué dato debe ingresar.
     * @return La patente del vehículo ingresada por el usuario en mayúsculas.
     * @throws IllegalArgumentException Si el usuario ingresa una patente en un formato inválido.
     */
    public static String patente(String x) {
        StringBuilder patente = new StringBuilder();
        while (true) {
            System.out.println("Ingresar " + x + " (letras): ");
            String s = scanner.nextLine();
            if (s.matches("[a-zA-Z]{3}")) {
                patente.append(s.toUpperCase());
                break;
            } else {
                soutAlertString("ERROR! Ingresar un dato válido (3 letras).");
            }
        }

        while (true) {
            Integer numero = ingresarXInteger("numeros (3 dígitos)");
            String num = String.format("%03d", numero); // Asegura que el número tenga 3 dígitos
            if (num.length() == 3) {
                patente.append(num);
                break;
            } else {
                soutAlertString("ERROR! Ingrese solamente 3 números.");
            }
        }

        return patente.toString().toUpperCase();
    }
    /**
     * Imprime el menú para el vendedor en la consola.
     */
    public static void printMenuVendedor() {
        System.out.println("""
                --- MENÚ VENDEDOR ---
                1. Gestión Clientes
                2. Gestión Ventas
                3. Stock Vehiculos
                
                0. SALIR
                ---------------------
                """);
    }
    /**
     * Imprime el menú para el administrador en la consola.
     */
    public static void printMenuAdministrador() {
        System.out.println("""
                 --- MENÚ ADMINISTRADOR ---
                1. Gestión Clientes
                2. Gestión Ventas
                3. Gestión Vehiculos
                4. Gestión Usuarios
                
                0. SALIR
                --------------------------
                """);
    }

    /**
     * Imprime el menú de inicio de sesión en la consola.
     */
    public static void printMenuLogin() {
        System.out.println("Iniciando APP Gestion Concesionaria...");
        System.out.println();
        System.out.println("""
                __________ BIENVENIDO ___________
                *********************************
                1. Iniciar Sesión
                
                0. Salir del Programa
                *********************************
                """);
    }
}