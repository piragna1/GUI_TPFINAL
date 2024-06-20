package com.framallo90.Login;

import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.Empleados.Model.Repository.EmpleadosRepository;
import com.framallo90.Excepciones.InicioSesionException;
import com.framallo90.consola.Consola;

import java.util.List;

public class Login {
    private EmpleadosRepository empleadosRepository;

    // Constructor que inicializa el repositorio de empleados.
    public Login() {
        empleadosRepository = new EmpleadosRepository();
    }

    // Función que realiza el login buscando en el JSON.
    public Empleados login() throws InicioSesionException {
        String username = Consola.ingresarXStringSimple("nombre de usuario");
        String password = Consola.ingresarXStringSimple("contrasena");
        // Carga la lista de empleados desde el repositorio.
        List<Empleados> empleados = empleadosRepository.getList();
        if (empleados == null || empleados.isEmpty()) {
            throw new InicioSesionException("La lista esta vacia o inexistente.");
        }

        // Recorre la lista de empleados y valida las credenciales.
        for (Empleados empleado : empleados) {
            if (empleado.getUsername().equals(username) && empleado.getPassword().equals(password)) {
                Consola.soutString("Iniciando sesion...");
                return empleado;
            }
        }
        // Si no encuentra coincidencias, retorna null.
        throw new InicioSesionException("No se ha encontrado al usuario.");
    }
    public Empleados login(String username, String password) throws InicioSesionException{
        //String username = Consola.ingresarXStringSimple("nombre de usuario");
        //String password = Consola.ingresarXStringSimple("contrasena");
        // Carga la lista de empleados desde el repositorio.
        List<Empleados> empleados = empleadosRepository.getList();
        if (empleados == null || empleados.isEmpty()) {
            System.err.println("La lista esta vacia o inexistente.");
            throw new InicioSesionException("La lista esta vacia o inexistente.");
        }

        // Recorre la lista de empleados y valida las credenciales.
        for (Empleados empleado : empleados) {
            if (empleado.getUsername().equals(username) && empleado.getPassword().equals(password)) {
                return empleado;
            }
        }
        // Si no encuentra coincidencias, retorna null.
        throw new InicioSesionException("No se ha encontrado al usuario.");
    }

}
