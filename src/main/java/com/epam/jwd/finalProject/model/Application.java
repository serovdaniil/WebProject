package com.epam.jwd.finalProject.model;

import java.util.Objects;

/**
 * Entity class Application
 *
 * @author Daniil Serov
 */
public class Application implements Entity {

    private final Long id;
    private final User user;
    private final SectionResult result;
    private final SectionConferenc sectionConferenc;

    public Application(Long id, User user, SectionResult result, SectionConferenc sectionConferenc) {
        this.id = id;
        this.user = user;
        this.result = result;
        this.sectionConferenc = sectionConferenc;
    }

    @Override
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public SectionResult getResult() {
        return result;
    }

    public SectionConferenc getSectionConferenc() {
        return sectionConferenc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(result, that.result)
                && Objects.equals(sectionConferenc, that.sectionConferenc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, result, sectionConferenc);
    }

    @Override
    public String toString() {
        StringBuilder s=new StringBuilder(100);
        s.append("Application '" + user + "':\n");
        s.append(" ID - " + id + "\n");
        s.append(" Result - " + result + "\n");
        s.append(" Section conferenc - " + sectionConferenc + "\n");
        return s.toString();
    }
}
