package com.km_technology_javafx.controllers;

import com.km_technology_javafx.views.TodoView;
import com.km_technology_javafx.models.TodoItem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TodoController extends Application {
    private final TodoView view = new TodoView();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("To-Do List");
        view.setAddButtonAction(e -> addTodo());
        view.setEditButtonAction(e -> editTodo());
        view.setDeleteButtonAction(e -> deleteTodo());

        Scene scene = new Scene(view.createMainLayout(), 650, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addTodo() {
        if (!view.getInputText().isEmpty()) {
            TodoItem item = new TodoItem(view.getInputText(), view.getDeadlinePickerValue());
            view.addTodoItem(item);
            view.clearInput();
        }
    }

    private void editTodo() {
        if (!view.getInputText().isEmpty()) {
            TodoItem item = new TodoItem(view.getInputText(), view.getDeadlinePickerValue());
            view.editSelectedTodoItem(item);
        }
    }

    private void deleteTodo() {
        view.deleteSelectedTodoItem();
    }
}

