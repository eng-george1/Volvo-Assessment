package com.volvo.congestion.calculator.service.congestioncalculator.controller;

import com.volvo.congestion.calculator.service.congestioncalculator.entity.*;


import com.volvo.congestion.calculator.service.congestioncalculator.entity.dto.*;
import com.volvo.congestion.calculator.service.congestioncalculator.handleException.UnsupportedVehicleTypeException;
import com.volvo.congestion.calculator.service.congestioncalculator.service.CongestionTaxCalServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/congestiontaxcal")
public class CongestionTaxCalController {

    private CongestionTaxCalServiceImpl CongestionTaxCalService;

    private static final Logger LOG = LoggerFactory.getLogger(CongestionTaxCalController.class);

@PostMapping
    public ResponseEntity<TaxResponse> getTax(@RequestBody TaxRequest taxRequest) {
        LOG.info("http call with type: " + taxRequest.getVehicleType() + ", times: " + taxRequest.getDates());
        TaxResponse response;
        Vehicle vehicle;
        if (taxRequest.getVehicleType() == VehicleTypes.CAR) {
            vehicle = new Car();
        } else if (taxRequest.getVehicleType() == VehicleTypes.MOTORCYCLE) {
            vehicle = new Motorbike();
        } else throw new UnsupportedVehicleTypeException("Unsupported vehicle type: " + taxRequest.getVehicleType());
        try {
            int tax = CongestionTaxCalService.getTax(vehicle, taxRequest.getDates());
            response = new TaxResponse( tax,                    "Tax calculated, Vehicle :" + vehicle.getVehicleType() + " Amount : " + tax,null);
            return new ResponseEntity<TaxResponse>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new TaxResponse(0,e.getLocalizedMessage(),
                    "Tax calculation failed.");
            return new ResponseEntity<TaxResponse>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
