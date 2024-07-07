package com.framallo90.Comprador.Controller;
import com.framallo90.Comprador.Model.Entity.Comprador;
import com.framallo90.Comprador.Model.Repository.CompradorRepository;
import com.framallo90.Comprador.View.CompradorView;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.UsuarioAbstracta.view.UsuarioView;
import com.framallo90.consola.Consola;

import java.util.List;

public class CompradorController {
    private static CompradorView compradorView;
    private static CompradorRepository compradorRepository;
    public CompradorController(CompradorView compradorView, CompradorRepository compradorRepository) {
        CompradorController.compradorView = compradorView;
        CompradorController.compradorRepository = compradorRepository;
    }
    public void compradorMenu(){
        int opt;
        do {
            System.out.println("MENU CLIENTES");
            System.out.println("1. Agregar cliente.");
            System.out.println("2. Modificar cliente.");
            System.out.println("3. Elimniar cliente.");
            System.out.println("4. Buscar un cliente");
            System.out.println("5. Historial de clientes.");
            System.out.println("0. Volver.");
            opt = Consola.ingresarXInteger("opcion");
            switch (opt){
                case 1:
                    add();
                    break;
                case 2:
                    verHisorial();
                    Comprador comprador;
                    try {
                        Integer dniComprador = Integer.parseInt(Consola.ingresarXStringSimple("dni del comprador"));
                        comprador = CompradorController.find(dniComprador);
                        System.out.println("1. Nombre");
                        System.out.println("2. Apellido");
                        System.out.println("3. DNI");
                        System.out.println("4. E-Mail");

                        System.out.println("0. Volver");
                        opt = Consola.ingresarXInteger("un campo para modificar de Comprado");

                        switch (opt) {
                            case 1:
                                comprador.setNombre(Consola.ingresarXString("el Nuevo Nombre"));
                                compradorRepository.update(comprador.getId(),comprador);
                                break;
                            case 2:
                                comprador.setApellido(Consola.ingresarXString("el Nuevo Apellido"));
                                compradorRepository.update(comprador.getId(),comprador);
                                break;
                            case 3:
                                String dni = Consola.ingresarXStringSimple("el Nuevo DNI");
                                if (UsuarioView.isValidDni(dni)) {
                                    comprador.setDni(dni);
                                    compradorRepository.update(comprador.getId(),comprador);
                                }
                                else Consola.soutAlertString("El dni ingresado no es válido.");
                                break;
                            case 4:
                                comprador.setEmail(compradorView.ingresoEmail());
                                compradorRepository.update(comprador.getId(),comprador);
                                break;
                            case 0:
                                break;
                            default:
                                Consola.soutAlertString("Opción Inválida. Reintentar!.");
                                break;
                        }
                        compradorRepository.update(comprador.getId(),comprador);
                    } catch (InvalidIdNotFound e) {
                        Consola.soutAlertString(e.getMessage());
                    }

                    break;
                case 3:
                    verHisorial();
                    remove();
                    break;
                case 4:
                    verHisorial();
                    show();
                    break;
                case 5:
                    verHisorial();
                    break;
                case 0:
                    System.out.println("Saliste del Menu Cliente.");
                    break;
                default:
                    Consola.soutAlertString("Opción Inválida. Reintentar!.");
                    break;
            }

        } while (opt != 0);
    }
    public void add() {
        String nombre = Consola.ingresarXString("el Nombre");
        String apellido = Consola.ingresarXString("el Apellido");
        String dni = Consola.ingresarXStringSimple("el DNI");
        String email = compradorView.ingresoEmail();
        Comprador comprador = new Comprador(nombre, apellido, dni, email);
        compradorRepository.add(comprador);
    }
    public void remove() {
        try{
            compradorRepository.remove(Consola.ingresarXInteger("el ID del Comprador"));
        } catch (InvalidIdNotFound e) {
            Consola.soutAlertString(e.getMessage());
        }
    }
    public void show() {
        Integer id = Consola.ingresarXInteger("el ID del Comprador buscado");
        try{
            Comprador comprador = compradorRepository.find(id);
            compradorView.muestroUnComprador(comprador);
        }catch (InvalidIdNotFound e){
            Consola.soutAlertString(e.getMessage());
        }
    }
    public static Comprador find(Integer dni) throws InvalidIdNotFound{
        //compradorView.muestroCompradores(compradorRepository.getsetCompradores());
        return compradorRepository.find(dni);
    }
    public List<Comprador> findXFiltro(String dni) throws InvalidIdNotFound{
        //compradorView.muestroCompradores(compradorRepository.getsetCompradores());
        return compradorRepository.findXFiltro(dni);
    }
    public void verHisorial() {
        compradorView.muestroCompradores(compradorRepository.getsetCompradores());
    }
}
