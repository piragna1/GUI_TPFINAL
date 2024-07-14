package com.framallo90.GUI.CLIENTES.auxiliar;

import com.framallo90.Comprador.Model.Entity.Comprador;

public class ClienteEncontrado implements com.framallo90.GUI.Interfaces.ClienteEncontrado {
    public static Comprador comprador;

    public ClienteEncontrado() {
    }

    @Override
    public void clienteEncontrado(Comprador comprador) {
        this.comprador = comprador;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }
}
