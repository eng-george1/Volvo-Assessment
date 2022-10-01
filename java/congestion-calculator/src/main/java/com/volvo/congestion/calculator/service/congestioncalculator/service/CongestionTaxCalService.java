package com.volvo.congestion.calculator.service.congestioncalculator.service;

import com.volvo.congestion.calculator.service.congestioncalculator.entity.Vehicle;

import java.time.LocalDateTime;
import java.util.Date;

public interface CongestionTaxCalService {

     int getTax(Vehicle vehicle, LocalDateTime[] dates);
    int getTollFee(LocalDateTime date, Vehicle vehicle);
}
