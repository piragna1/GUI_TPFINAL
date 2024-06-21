package com.framallo90.MetodoDePago.Controller;
import com.framallo90.MetodoDePago.Model.Entity.MetodoDePago;
import com.framallo90.MetodoDePago.View.MetodoView;
import com.framallo90.consola.Consola;
public class MetodoController {
    private static MetodoView metodoView;

    public MetodoController(MetodoView metodoView) {
        MetodoController.metodoView = metodoView;
    }
    public MetodoDePago cargarMDP(Double precioVehiculo) {
        return metodoView.cargarMetodoDP(precioVehiculo);
    }
    public void updateMDP(MetodoDePago metodoDePago, Double precioVehiculo){
        int eleccion;
        String tipo = metodoDePago.getTipo();
        metodoView.printMenuModifMDP();
        eleccion=Consola.ingresarXInteger("eleccion");
        if (eleccion==2) return;
        else if (eleccion==1)
            metodoDePago=this.cargarMDP(precioVehiculo);
    }

}
