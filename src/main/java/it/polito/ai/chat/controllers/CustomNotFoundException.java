package it.polito.ai.chat.controllers;

public class CustomNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CustomNotFoundException(String msg) {
        super(msg);
    }
}
