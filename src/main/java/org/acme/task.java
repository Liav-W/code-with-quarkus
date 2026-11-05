package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class task extends PanacheEntityBase {
    public String createdAt;
    public boolean completed;
    public String description;
    public String title;
    @Id
    @GeneratedValue
    public Long id;
}
/*change*/
