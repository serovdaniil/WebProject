package com.epam.jwd.finalProject.model;

import java.sql.Date;
import java.util.Objects;

/**
 * Entity class Question
 *
 * @author Daniil Serov
 */
public class Question implements Entity {
    private final Long id;
    private final String question;
    private final String answer;
    private final Date date;
    private final User user;

    public Question(Long id, String question, String answer, Date date, User user) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.date = date;
        this.user = user;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Date getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(id, question1.id) && Objects.equals(question, question1.question) && Objects.equals(answer, question1.answer) && Objects.equals(date, question1.date) && Objects.equals(user, question1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, answer, date, user);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
