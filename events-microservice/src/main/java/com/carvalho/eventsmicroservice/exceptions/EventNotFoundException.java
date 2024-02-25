package com.carvalho.eventsmicroservice.exceptions;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException() {
        super("Evento nao encontrado.");
    }

    public EventNotFoundException(String message) {
        super(message);
    }

}
