package com.km_technology_javafx.views;

import com.km_technology_javafx.models.TodoItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TodoView {
    private ListView<TodoItem> listView = new ListView<>();
    private TextField inputField = new TextField();
    private DatePicker deadlinePicker = new DatePicker();
    private Button addButton = new Button("Pridať úlohu");
    private Button editButton = new Button("Upraviť úlohu");
    private Button deleteButton = new Button("Vymazať úlohu");

    public TodoView() {
        inputField.setPrefWidth(400);
        deadlinePicker.setPrefWidth(400);
        listView.setCellFactory(lv -> createCell());
    }

    public BorderPane createMainLayout() {
        HBox buttonsBox = new HBox(15, addButton, editButton, deleteButton);
        buttonsBox.setAlignment(Pos.CENTER);

        VBox inputVBox = new VBox(10, new Label("Description:"), inputField, new Label("Deadline:"), deadlinePicker, buttonsBox);
        inputVBox.setPadding(new Insets(20, 20, 20, 20));
        inputVBox.setSpacing(10);

        BorderPane root = new BorderPane();
        root.setTop(inputVBox);
        root.setCenter(listView);
        return root;
    }

    private ListCell<TodoItem> createCell() {
        return new ListCell<TodoItem>() {
            @Override
            protected void updateItem(TodoItem item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    String createdStr = " (Created: " + formatter.format(item.getCreatedTime()) + ")";
                    String deadlineStr = (item.getDeadline() != null) ? "Deadline: " + formatter.format(item.getDeadline()) : "No deadline";

                    Label descriptionLabel = new Label(item.getDescription());
                    Label createdLabel = new Label(createdStr);
                    createdLabel.setStyle("-fx-opacity: 0.5;"); // Make created date less visible

                    Label deadlineLabel = new Label(deadlineStr);
                    deadlineLabel.setTextFill(Color.BLACK);
                    HBox.setHgrow(deadlineLabel, Priority.ALWAYS);

                    HBox content = new HBox(5, descriptionLabel, createdLabel, deadlineLabel);
                    content.setAlignment(Pos.CENTER_LEFT);
                    HBox.setMargin(deadlineLabel, new Insets(0, 0, 0, 5));

                    setGraphic(content);
                }
            }
        };
    }

    public void addTodoItem(TodoItem item) {
        listView.getItems().add(item);
    }

    public void editSelectedTodoItem(TodoItem item) {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            listView.getItems().set(selectedIndex, item);
        }
    }

    public void deleteSelectedTodoItem() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            listView.getItems().remove(selectedIndex);
        }
    }

    public void clearInput() {
        inputField.clear();
        deadlinePicker.setValue(null);
    }

    public String getInputText() {
        return inputField.getText();
    }

    public LocalDateTime getDeadlinePickerValue() {
        return deadlinePicker.getValue() != null ? deadlinePicker.getValue().atStartOfDay() : null;
    }

    public void setAddButtonAction(EventHandler<ActionEvent> action) {
        addButton.setOnAction(action);
    }

    public void setEditButtonAction(EventHandler<ActionEvent> action) {
        editButton.setOnAction(action);
    }

    public void setDeleteButtonAction(EventHandler<ActionEvent> action) {
        deleteButton.setOnAction(action);
    }
}
