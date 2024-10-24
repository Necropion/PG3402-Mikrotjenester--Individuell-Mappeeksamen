package com.vehicle.authentication.Exception;

public class NoUserFoundException extends RuntimeException {
    public NoUserFoundException(String message) {
        super(message);
    }
}
