package com.carservice.carServiceDocker.service.exception;

public class CarException extends Exception {

    private final String message;

    public CarException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
