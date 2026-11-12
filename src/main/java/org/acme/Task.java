package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Table(name = "tasks")
public class Task extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasksSeqGenerator")
    @SequenceGenerator(name = "tasksSeqGenerator", sequenceName = "tasks_SEQ", allocationSize = 50)
    public Long id;

    public String title;
    public String description;
    public boolean completed;

    @Column(name = "\"createdAt\"")
    public LocalDateTime createdAt;

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

    public enum Priority {
        low, medium, high
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    public Priority priority = Priority.low;

    @Column(name = "due_date")
    public LocalDateTime dueDate;
}