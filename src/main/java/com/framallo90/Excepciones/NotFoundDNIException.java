package com.framallo90.Excepciones;

public class NotFoundDNIException extends Exception{
    private final String message = "El DNI ingresado no se encuentra registrado.";

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public NotFoundDNIException() {
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public NotFoundDNIException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
