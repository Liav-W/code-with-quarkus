package org.acme;

import jakarta.persistence.Entity;


@Entity
public class task {
    public String createdAt;
    public boolean completed;
    public String description;
    public String title;
    public Long id;
}

