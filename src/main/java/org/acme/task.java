package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;


@Entity
public class task extends PanacheEntityBase {
    public String createdAt;
    public boolean completed;
    public String description;
    public String title;
    public Long id;
}
/*change*/
