package com.userregistration.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_security_qa")
public class SecurityQA implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer qaId;

    @ManyToOne(targetEntity = User.class, optional = false)
    User user;

    @ManyToOne(targetEntity = SecurityQuestion.class, optional = false)
    SecurityQuestion question;

    @Column(name = "answer", nullable = false, length = 50)
    String answer;

    public SecurityQA(Integer questionId, String answer) {
        this.question = new SecurityQuestion(questionId);
        this.answer = answer;
    }

    public SecurityQA() {
    }

    public Integer getQaId() {
        return qaId;
    }

    public void setQaId(Integer qaId) {
        this.qaId = qaId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SecurityQuestion getQuestion() {
        return question;
    }

    public void setQuestion(SecurityQuestion question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityQA that = (SecurityQA) o;
        return Objects.equals(getQaId(), that.getQaId()) &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getQuestion(), that.getQuestion()) &&
                Objects.equals(getAnswer(), that.getAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQaId(), getUser(), getQuestion(), getAnswer());
    }

    @Override
    public String toString() {
        return "SecurityQA{" +
                "qaId=" + qaId +
                ", user=" + user +
                ", question=" + question +
                ", answer='" + answer + '\'' +
                '}';
    }

    public SecurityQAVO convertToSecurityQAVO() {
        return new SecurityQAVO(this.question.getQuestionId(), this.answer);
    }
}
