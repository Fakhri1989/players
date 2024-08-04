package com.intuit.player.microservice.exception;

public class PlayerNotFoundException extends RuntimeException{

    public PlayerNotFoundException(String message) {
        super(message);
    }

    public PlayerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
