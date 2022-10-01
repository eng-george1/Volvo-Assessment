package com.volvo.congestion.calculator.service.congestioncalculator.entity;

import java.util.*;
import java.text.*;

public class Motorbike implements Vehicle {
    @Override
    public String getVehicleType() {
        return "Motorbike";
    }

    @Override
    public VehicleTypes getVehicleTypeEnum() {
        return VehicleTypes.MOTORCYCLE;
    }
}
