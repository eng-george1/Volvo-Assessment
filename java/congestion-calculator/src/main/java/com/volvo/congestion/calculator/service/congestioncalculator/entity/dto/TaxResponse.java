package com.volvo.congestion.calculator.service.congestioncalculator.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TaxResponse {


	private String error;
	private int tax;
	private String message;


	public TaxResponse( int tax, String message,String error) {
		this.tax = tax;
		this.message = message;
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public int getTax() {
		return tax;
	}

	public String getMessage() {
		return message;
	}

	/*
	 * returns the current timestamp
	 */
	public Long getTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp.getTime();
	}

}
