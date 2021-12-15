package com.epam.jwd.finalProject.model;

import java.util.Objects;

/**
 * Entity class User
 *
 * @author Daniil Serov
 */
public class User implements Entity {
    private final Long id;
    private final String login;
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final Role role;

    public User(Long id, String login, String email, String password, String firstName, String lastName, Role role) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) &&
                Objects.equals(email, user.email) && Objects.equals(password, user.password)
                && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName)
                && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, email, password, firstName, lastName, role);
    }

    @Override
    public String toString() {
        StringBuilder s=new StringBuilder(100);
        s.append("User '" + firstName + "':\n");
        s.append(" ID - " + id + "\n");
        s.append(" Last name - " + lastName + "\n");
        s.append(" Login - " + login + "\n");
        s.append(" Email - " + email + "\n");
        s.append(" Password - " + password + "\n");
        s.append(" Role - " + role + "\n");
        return s.toString();
    }
}
