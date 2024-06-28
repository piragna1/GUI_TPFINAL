package com.framallo90.Excepciones;
public class CeroAdminsException extends Exception{
    private final String message = "Ha ocurrido un error. No puede eliminarse el único administrador existente en el sistema.";

    public CeroAdminsException() {
    }
    @Override
    public String getMessage() {
        return message;
    }
}
