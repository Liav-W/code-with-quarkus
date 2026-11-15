package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
public class TaskServiceTest {

    @Inject
    TaskService taskService;

    @Test
    public void testCreateAndRetrieveTaskDirectly() {
        Task task = new Task();
        task.title = "Service Test";
        task.description = "Direct CDI injection test";

        Task created = taskService.createTask(task);
        Assertions.assertNotNull(created.id);
        Assertions.assertNotNull(created.createdAt);

        List<Task> allTasks = taskService.getAllTasks();
        Assertions.assertFalse(allTasks.isEmpty());

        boolean deleted = taskService.deleteTask(created.id);
        Assertions.assertTrue(deleted);
    }
}