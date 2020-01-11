package com.upgrad.quora.service.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "answer")
@NamedQueries({
        @NamedQuery(name = "getAnswerByUuid", query = "select u from AnswerEntity u where u.uuid =:uuid"),
        @NamedQuery(name = "getAllAnswersToQuestionId", query = "select u from AnswerEntity u where u.question =:question"),

})
public class AnswerEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "UUID")
    @Size(max = 200)
    private String uuid;

    @Column(name = "ANS")
    @Size(max = 255)
    private String answer;

    @Column(name = "DATE")
    @NotNull
    private ZonedDateTime date;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private QuestionEntity question;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof AnswerEntity))
            return false;
        AnswerEntity that = (AnswerEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getUuid(), that.getUuid())
                && Objects.equals(getAnswer(), that.getAnswer()) && Objects.equals(getDate(), that.getDate())
                && Objects.equals(getUser(), that.getUser()) && Objects.equals(getQuestion(), that.getQuestion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUuid(), getAnswer(), getDate(), getUser(), getQuestion());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }
}

