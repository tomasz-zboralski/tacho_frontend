package com.example.tacho_frontend.service;

import com.example.tacho_frontend.domain.DutyDto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class DutyService {

    private Set<DutyDto> duties;
    public static DutyService dutyService;

    public DutyService() {
        this.duties = exampleData();
    }

    public static DutyService getInstance() {
        if (dutyService == null) {
            dutyService = new DutyService();
        }
        return dutyService;
    }

    public Set<DutyDto> exampleData() {
        DutyDto duty1 = new DutyDto(1L, BigDecimal.TEN, BigDecimal.ZERO, "Agency1", "Company1");
        DutyDto duty2 = new DutyDto(2L, BigDecimal.TEN, BigDecimal.ZERO, "Agency2", "Company2");
        Set<DutyDto> data = new HashSet<>();
        data.add(duty1);
        data.add(duty2);

        return data;

    }

    public Set<DutyDto> getDuties() {
        return new HashSet<>(duties);
    }
}
