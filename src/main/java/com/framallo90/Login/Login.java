package com.framallo90.Login;
import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.Empleados.Model.Repository.EmpleadosRepository;
import com.framallo90.consola.Consola;
import java.util.List;
public class Login {
    public static Empleados empleadoIngresado = null;
    private EmpleadosRepository empleadosRepository;
    // Constructor que inicializa el repositorio de empleados.
    public Login() {
        empleadosRepository = new EmpleadosRepository();
    }
    // Función que realiza el login buscando en el JSON.
    public Empleados login() {
        String username = Consola.ingresarXStringSimple("nombre de usuario");
        String password = Consola.ingresarXStringSimple("contrasena");
        // Carga la lista de empleados desde el repositorio.
        List<Empleados> empleados = empleadosRepository.getList();
        if (empleados == null || empleados.isEmpty()) {
            System.err.println("La lista esta vacia o inexistente.");
            return null;
        }
        // Recorre la lista de empleados y valida las credenciales.
        for (Empleados empleado : empleados) {
            if (empleado.getUsername().equals(username) && empleado.getPassword().equals(password)) {
                return empleado;
            }
        }
        // Si no encuentra coincidencias, retorna null.
        return null;
    }
    public Empleados login(String username, String password) {
        // Carga la lista de empleados desde el repositorio.
        List<Empleados> empleados = empleadosRepository.getList();
        if (empleados == null || empleados.isEmpty()) {
            System.err.println("La lista esta vacia o inexistente.");
            return null;
        }
        // Recorre la lista de empleados y valida las credenciales.
        for (Empleados empleado : empleados) {
            if (empleado.getUsername().equals(username) && empleado.getPassword().equals(password)) {
                return empleado;
            }
        }
        // Si no encuentra coincidencias, retorna null.
        return null;
    }
}
