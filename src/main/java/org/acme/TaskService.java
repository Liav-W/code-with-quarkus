package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class TaskService {

    @Inject
    TaskDao taskDao;

    public List<Task> getAllTasks() {
        return taskDao.listAllTasks();
    }

    @Transactional
    public Task createTask(Task task) {
        if (task.createdAt == null) {
            task.createdAt = LocalDateTime.now();
        }
        taskDao.saveTask(task);
        return task;
    }

    @Transactional
    public boolean deleteTask(Long id) {
        return taskDao.deleteTaskById(id);
    }
}