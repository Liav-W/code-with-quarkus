package org.acme;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

public interface taskResource extends PanacheEntityResource<task, Long> {
}