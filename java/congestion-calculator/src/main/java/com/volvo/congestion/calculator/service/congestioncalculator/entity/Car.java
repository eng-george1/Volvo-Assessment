package com.volvo.congestion.calculator.service.congestioncalculator.entity;

import java.util.*;
import java.text.*;

public class Car implements Vehicle {

    public String getVehicleType() {
        return "Car";
    }

    @Override
    public VehicleTypes getVehicleTypeEnum() {
        return VehicleTypes.CAR;
    }
}
