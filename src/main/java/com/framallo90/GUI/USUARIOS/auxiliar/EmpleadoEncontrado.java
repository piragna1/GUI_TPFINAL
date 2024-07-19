package com.framallo90.GUI.USUARIOS.auxiliar;

import com.framallo90.Empleados.Model.Entity.Empleados;
import com.framallo90.GUI.Interfaces.EmpleadoEncontradoListener;

import java.util.List;

public class EmpleadoEncontrado implements EmpleadoEncontradoListener {
    public static Empleados empleados;
    private static List<EmpleadoEncontradoListener> listeners;

    public EmpleadoEncontrado() {
    }

    @Override
    public void onEmpleadoChanged(Empleados empleadosInput) {
        empleados = empleadosInput;
    }

    public static void setEmpleados(Empleados empleados) {
        EmpleadoEncontrado.empleados = empleados;
    }

    public static void addListener(EmpleadoEncontradoListener empleadoEncontradoListener){
        listeners.add(empleadoEncontradoListener);
    }
    public static void removeListener(EmpleadoEncontradoListener empleadoEncontradoListener){
        listeners.remove(empleadoEncontradoListener);
    }
    private static void notifyListeners(){
        for (EmpleadoEncontradoListener empleadoEncontradoListener : listeners)
            empleadoEncontradoListener.onEmpleadoChanged(empleados);
    }
}
