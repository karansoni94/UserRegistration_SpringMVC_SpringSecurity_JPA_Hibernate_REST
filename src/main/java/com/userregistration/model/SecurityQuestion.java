package com.userregistration.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "security_question")
public class SecurityQuestion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id")
    Integer questionId;

    @Column(name = "question", nullable = false, length = 100)
    String question;

    public SecurityQuestion() {
    }

    public SecurityQuestion(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "SecurityQuestion{" +
                "questionId=" + questionId +
                ", question='" + question + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityQuestion that = (SecurityQuestion) o;
        return Objects.equals(getQuestionId(), that.getQuestionId()) &&
                Objects.equals(getQuestion(), that.getQuestion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuestionId(), getQuestion());
    }
}
