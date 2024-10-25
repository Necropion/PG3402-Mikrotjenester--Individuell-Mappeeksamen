package com.vehicle.dealership.Exception;

public class ItemProductIDExistsException extends RuntimeException{
    public ItemProductIDExistsException(String message) {
        super(message);
    }
}
