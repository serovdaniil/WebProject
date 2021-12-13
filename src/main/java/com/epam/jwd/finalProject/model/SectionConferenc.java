package com.epam.jwd.finalProject.model;

import java.util.Objects;

public class SectionConferenc implements Entity{
    private final Long id;
    private final String name;
    private final String description;
    private final Conferenc conferenc;
    private final Status status;

    public SectionConferenc(Long id, String name, String description, Conferenc conferenc, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.conferenc = conferenc;
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

    public Conferenc getConferenc() {
        return conferenc;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectionConferenc that = (SectionConferenc) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(conferenc, that.conferenc) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, conferenc, status);
    }

    @Override
    public String toString() {
        return "SectionConferenc{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", conferenc=" + conferenc +
                ", status=" + status +
                '}';
    }
}
