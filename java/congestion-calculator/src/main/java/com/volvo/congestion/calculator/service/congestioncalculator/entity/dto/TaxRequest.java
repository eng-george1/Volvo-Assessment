package com.volvo.congestion.calculator.service.congestioncalculator.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.volvo.congestion.calculator.service.congestioncalculator.entity.Vehicle;
import com.volvo.congestion.calculator.service.congestioncalculator.entity.VehicleTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Getter
public class TaxRequest {
	public VehicleTypes vehicleType;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Amsterdam")
	public LocalDateTime[] dates;
}
