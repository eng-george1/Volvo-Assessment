package com.volvo.congestion.calculator.service.congestioncalculator.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Configuration
public class TaxConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(TaxConfiguration.class);
    @Value("${vehicles.tollfree}")
    public List<String> tollFreeVehicles;

    @Value("#{${toll.times}}")
    public Map<String, Integer> tollsByTime = new HashMap<>();

    @Value("#{${noTaxMonths}}")
    public   List<Month> noTaxMonths;

    @Value("${noTaxDates}")
    private    List<String> loadnoTaxDates;
public  List<Date> noTaxDates() throws ParseException {
    List<Date> dates=new ArrayList<>();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    for (String s:loadnoTaxDates){
        dates.add(formatter.parse(s));
    }
    return  dates;
}
    @Value("#{${singlecharge.time}}")
    public   Integer singlechargeTime;
}
