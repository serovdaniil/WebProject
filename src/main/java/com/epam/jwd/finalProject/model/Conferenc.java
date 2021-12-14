package com.epam.jwd.finalProject.model;

import java.util.Objects;

/**
 * Entity class Conferenc
 *
 * @author Daniil Serov
 */
public class Conferenc implements Entity {
    private final Long id;
    private final String name;
    private final String description;
    private final Category category;
    private final Status status;

    public Conferenc(Long id, String name, String description, Category category, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.status = status;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conferenc conferenc = (Conferenc) o;
        return Objects.equals(id, conferenc.id) && Objects.equals(name, conferenc.name) && Objects.equals(description, conferenc.description) && Objects.equals(category, conferenc.category) && Objects.equals(status, conferenc.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, category, status);
    }

    @Override
    public String toString() {
        StringBuilder s=new StringBuilder(100);
        s.append("Conferenc '" + name + "':\n");
        s.append(" ID - " + id + "\n");
        s.append(" Description - " + description + "\n");
        s.append(" Category - " + category + "\n");
        s.append(" Status - " + status + "\n");
        return s.toString();
    }
}
