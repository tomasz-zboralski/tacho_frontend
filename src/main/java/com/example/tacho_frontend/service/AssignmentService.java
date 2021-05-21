package com.example.tacho_frontend.service;

import com.example.tacho_frontend.domain.AssignmentDto;
import com.example.tacho_frontend.domain.DutyDto;
import com.example.tacho_frontend.domain.EntryDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AssignmentService {
    private Set<AssignmentDto> assignments;
    public static AssignmentService assignmentService;

    public AssignmentService() {
        this.assignments = exampleData();
    }

    public static AssignmentService getInstance() {
        if (assignmentService == null) {
            assignmentService = new AssignmentService();
        }
        return assignmentService;
    }
    public void save(AssignmentDto assignmentDto) {
        this.assignments.add(assignmentDto);
    }

    public void delete(AssignmentDto assignmentDto) {
        this.assignments.remove(assignmentDto);
    }

    public Set<AssignmentDto> getAssignments() {
        return new HashSet<>(assignments);
    }

    public Set<AssignmentDto> exampleData() {

        DutyDto duty1 = new DutyDto(1L, BigDecimal.TEN, BigDecimal.ZERO, "Agency1", "Company1");
        DutyDto duty2 = new DutyDto(2L, BigDecimal.TEN, BigDecimal.ZERO, "Agency2", "Company2");

        EntryDto entryDto = new EntryDto(3L, "test", LocalDateTime.now(), LocalDateTime.now());

        AssignmentDto assignmentDto1 = new  AssignmentDto(
                1L, LocalDateTime.now().minusHours(9), LocalDateTime.now(), duty1, new ArrayList<>()
        );
        //assignmentDto1.getEntries().add(entryDto);
        Set<AssignmentDto> assignmentsDto = new HashSet<AssignmentDto>();
        assignmentsDto.add(
                new AssignmentDto(1L, LocalDateTime.now().minusHours(9), LocalDateTime.now(), duty1, new ArrayList<>() ));
        assignmentsDto.add(
                new AssignmentDto(2L, LocalDateTime.now().minusHours(7), LocalDateTime.now(), duty2, new ArrayList<>() ));
        assignmentsDto.add(assignmentDto1);

        return assignmentsDto;
    }
}
