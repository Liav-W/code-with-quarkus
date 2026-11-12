package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class TaskController {

    @Inject
    TaskService taskService;

    public List<Task> handleGetAllTasks() {
        return taskService.getAllTasks();
    }

    public Task handleCreateTask(Task task) {
        return taskService.createTask(task);
    }

    public boolean handleDeleteTask(Long id) {
        return taskService.deleteTask(id);
    }
}