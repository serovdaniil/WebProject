package com.epam.jwd.finalProject.model;

import java.util.Objects;

/**
 * Entity class Category
 *
 * @author Daniil Serov
 */
public class Category implements Entity {
    private final Long id;
    private final String name;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        StringBuilder s=new StringBuilder(100);
        s.append("Category '" + name + "':\n");
        s.append(" ID - " + id + "\n");
        return s.toString();
    }
}
