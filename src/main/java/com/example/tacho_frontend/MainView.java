package com.example.tacho_frontend;

import com.example.tacho_frontend.domain.AssignmentDto;
import com.example.tacho_frontend.service.AssignmentService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.text.SimpleDateFormat;

@Route
public class MainView extends VerticalLayout {

    private AssignmentService assignmentService = AssignmentService.getInstance();
    private Grid<AssignmentDto> grid = new Grid<>(AssignmentDto.class);
    private AssignmentForm form = new AssignmentForm(this);
    private Button addNewEntry = new Button("Add new entry");

    public MainView() {
        grid.setColumns("assignmentId", "time", "duty", "driver");

        addNewEntry.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setAssignmentDto(new AssignmentDto());
        });

        HorizontalLayout toolbar = new HorizontalLayout(addNewEntry);
        HorizontalLayout mainContent = new HorizontalLayout(grid);

        mainContent.setSizeFull();

        form.setAssignmentDto(null);
        grid.setSizeFull();
        grid.asSingleSelect().addValueChangeListener(event -> form.setAssignmentDto(grid.asSingleSelect().getValue()));


        add(toolbar, mainContent, form);

        setSizeFull();
        refresh();


    }

    public void refresh() {
        grid.setItems(assignmentService.getAssignments());
    }

}