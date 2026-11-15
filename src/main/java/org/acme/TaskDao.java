package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class TaskDao implements PanacheRepository<Task> {

    public List<Task> listAllTasks() {
        return listAll();
    }

    public void saveTask(Task task) {
        persist(task);
    }

    public boolean deleteTaskById(Long id) {
        return deleteById(id);
    }
}