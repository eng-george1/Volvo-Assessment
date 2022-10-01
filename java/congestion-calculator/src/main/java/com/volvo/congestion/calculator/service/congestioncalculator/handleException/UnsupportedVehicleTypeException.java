package com.volvo.congestion.calculator.service.congestioncalculator.handleException;

public class UnsupportedVehicleTypeException extends RuntimeException {
    public UnsupportedVehicleTypeException(String message) {
        super(message);
    }
}
