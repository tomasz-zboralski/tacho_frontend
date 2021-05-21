package com.example.tacho_frontend;

import com.example.tacho_frontend.domain.AssignmentDto;
import com.example.tacho_frontend.domain.DutyDto;
import com.example.tacho_frontend.domain.EntryDto;
import com.example.tacho_frontend.service.AssignmentService;
import com.example.tacho_frontend.service.DutyService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.ListDataProvider;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AssignmentForm extends FormLayout {

    LocalTime start = LocalTime.now();
    LocalTime end = LocalTime.now();
    String type;

    private DutyService dutyService = DutyService.getInstance();
    private MainView mainView;
    private AssignmentService service = AssignmentService.getInstance();
    private ComboBox<DutyDto> duty = new ComboBox<>("Duty");
    private TimePicker startTimePicker = new TimePicker();
    private TimePicker endTimePicker = new TimePicker();
    private TextField driver = new TextField("Driver");
    private Grid<EntryDto> entries = new Grid<>(EntryDto.class);
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    RadioButtonGroup<String> entryType = new RadioButtonGroup<>();
    private Binder<AssignmentDto> binder = new Binder<AssignmentDto>(AssignmentDto.class);

    Button addButton = new Button("Add Item", event -> addEntry());


    public AssignmentForm(MainView mainView) {
        duty.setItems(dutyService.getDuties());
        entries.setColumns("type", "time", "duration");
        entryType.setItems("Drive", "Rest", "Work", "POA");
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        HorizontalLayout entryButtons = new HorizontalLayout(entryType, startTimePicker, endTimePicker, addButton);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        startTimePicker.addValueChangeListener(
                event -> start = startTimePicker.getValue());
        endTimePicker.addValueChangeListener(
                event -> end = endTimePicker.getValue());

        entryType.addValueChangeListener(event -> type = entryType.getValue());
        entryType.setValue("Drive");

        entries.addComponentColumn(item -> createRemoveButton(entries, item))
                .setHeader("Actions");


        add(entries, entryButtons, driver, duty, buttons);

        binder.bindInstanceFields(this);
        this.mainView = mainView;
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }

    private void save() {
        AssignmentDto assignmentDto = binder.getBean();
        service.save(assignmentDto);
        mainView.refresh();
        setAssignmentDto(null);
    }

    private void delete() {
        AssignmentDto assignmentDto = binder.getBean();
        service.delete(assignmentDto);
        mainView.refresh();
        setAssignmentDto(null);
    }

    public void setAssignmentDto(AssignmentDto assignmentDto) {
        binder.setBean(assignmentDto);

        if (assignmentDto == null) {
            setVisible(false);
        } else {
            setVisible(true);
            driver.focus();
            entries.setItems(assignmentDto.getEntries());
        }
    }

    public void addEntry(){



        if (type != null & start != null & end != null) {
            AssignmentDto assignmentDto = binder.getBean();
            assignmentDto.getEntries().add(new EntryDto(3L, type, LocalDate.now().atTime(start), LocalDate.now().atTime(end)));
            entries.getDataProvider().refreshAll();
            startTimePicker.setValue(end);
            startTimePicker.setEnabled(false);
            endTimePicker.setMinTime(end);
        }

    }

    private Button createRemoveButton(Grid<EntryDto> grid, EntryDto item) {
        @SuppressWarnings("unchecked")
        Button button = new Button("Remove", clickEvent -> {
            ListDataProvider<EntryDto> dataProvider = (ListDataProvider<EntryDto>) grid
                    .getDataProvider();
            dataProvider.getItems().remove(item);
            dataProvider.refreshAll();
        });
        return button;
    }

}

