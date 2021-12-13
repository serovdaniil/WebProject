package com.epam.jwd.finalProject.model;

import java.util.Objects;

/**
 * Entity class SectionResult
 *
 * @author Daniil Serov
 */
public class SectionResult implements Entity {
    private final Long id;
    private final String result;

    public SectionResult(Long id, String result) {
        this.id = id;
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SectionResult)) return false;
        SectionResult that = (SectionResult) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, result);
    }

    @Override
    public String toString() {
        return "SectionResult{" +
                "id=" + id +
                ", result='" + result + '\'' +
                '}';
    }
}
