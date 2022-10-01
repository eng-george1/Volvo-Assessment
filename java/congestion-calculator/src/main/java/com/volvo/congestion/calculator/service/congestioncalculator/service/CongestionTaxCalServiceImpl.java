package com.volvo.congestion.calculator.service.congestioncalculator.service;

import com.volvo.congestion.calculator.service.congestioncalculator.configuration.TaxConfiguration;
import com.volvo.congestion.calculator.service.congestioncalculator.controller.CongestionTaxCalController;
import com.volvo.congestion.calculator.service.congestioncalculator.entity.Vehicle;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class CongestionTaxCalServiceImpl implements CongestionTaxCalService {

    private static final Logger LOG = LoggerFactory.getLogger(CongestionTaxCalServiceImpl.class);
    @Autowired
    TaxConfiguration taxConfiguration;
//    private static Map<String, Integer> tollFreeVehicles = new HashMap<>();
//
//    static {
//        tollFreeVehicles.put("Motorcycle", 0);
//        tollFreeVehicles.put("Tractor", 1);
//        tollFreeVehicles.put("Emergency", 2);
//        tollFreeVehicles.put("Diplomat", 3);
//        tollFreeVehicles.put("Foreign", 4);
//        tollFreeVehicles.put("Military", 5);
//
//    }
    
    public int getTax(@NonNull Vehicle vehicle, @NonNull LocalDateTime[] dates)
    {
        int totalFee = 0;

        for (int i = 1; i < dates.length; i++) {
            int tollFee = 0;
            if(Duration.between(dates[i], dates[i - 1]).toMinutes() >taxConfiguration.singlechargeTime||i==0);
           {
                tollFee = getTollFee(dates[i], vehicle);
                totalFee += tollFee;
            }
        }

        if (totalFee > taxConfiguration.singlechargeTime)
            totalFee = taxConfiguration.singlechargeTime;
        return totalFee;
    }

    private boolean isTollFreeVehicle(Vehicle vehicle) {
        return taxConfiguration.tollFreeVehicles.contains(vehicle.getVehicleType());
    }

    public int getTollFee(LocalDateTime dateTime, Vehicle vehicle)
    {
        if (isTollFreeDate(dateTime) || isTollFreeVehicle(vehicle)) {
            return 0;
        }
        for (var entry : taxConfiguration.tollsByTime.entrySet()) {
            String[] split = entry.getKey().split("-");
            LocalTime target = dateTime.toLocalTime();
            LocalTime start = LocalTime.parse(split[0]);
            LocalTime end = LocalTime.parse(split[1]);
            if( target.isAfter(start) && target.isBefore(end)){
                return entry.getValue();
            }
        }
        return 0;
    }

    private Boolean isTollFreeDate(LocalDateTime date)
    {
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY ||date.getDayOfWeek() == DayOfWeek.SUNDAY )
            return true;
        try{
        return taxConfiguration.noTaxDates().contains(date);}
        catch(ParseException e){
            LOG.error(e.getMessage());
        }
        return false;
    }
}
