package com.framallo90.GUI.CLIENTES.auxiliar;

import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.GUI.Interfaces.ClienteEncontradoListener;

import java.util.ArrayList;
import java.util.List;

public class ClienteEncontrado implements ClienteEncontradoListener {
    public static Comprador comprador;
    private static List<ClienteEncontradoListener> listeners = new ArrayList();

    public ClienteEncontrado() {
    }

    @Override
    public void onCompradorChanged(Comprador comprador) {
        this.comprador = comprador;
    }

    public static Comprador getComprador() {
        return comprador;
    }

    public static void setComprador(Comprador c) {
        comprador = c;
        notifyListeners();
    }
    public static void addListener(ClienteEncontradoListener clienteEncontradoListener){
        listeners.add(clienteEncontradoListener);
    }
    public static void removeListener(ClienteEncontradoListener clienteEncontradoListener){
        listeners.remove(clienteEncontradoListener);
    }
    private static void notifyListeners(){
        for (ClienteEncontradoListener clienteEncontradoListener : listeners)
            clienteEncontradoListener.onCompradorChanged(comprador);
    }
}
