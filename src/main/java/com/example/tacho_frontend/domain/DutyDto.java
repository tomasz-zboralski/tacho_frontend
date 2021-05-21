package com.example.tacho_frontend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


public class DutyDto {

    private Long dutyId;
    private BigDecimal hourlyRate;
    private BigDecimal allowance;
    private String agency;
    private String company;

    public DutyDto(Long dutyId, BigDecimal hourlyRate, BigDecimal allowance, String agency, String company) {
        this.dutyId = dutyId;
        this.hourlyRate = hourlyRate;
        this.allowance = allowance;
        this.agency = agency;
        this.company = company;
    }

    @Override
    public String toString() {
        return "Duty " + dutyId + ": " + agency + "/" + company;
    }
}
