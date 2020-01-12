package com.upgrad.quora.service.entity;

<<<<<<< HEAD
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "question")
@NamedQueries({
        @NamedQuery(name = "findAllQuestions", query = "select u from QuestionEntity u where u.user is not null"),
        @NamedQuery(name = "getQuestionByUuid", query = "select u from QuestionEntity u where u.uuid =:uuid"),
        @NamedQuery(name = "getQuestionsByUserId", query = "select u from QuestionEntity u where u.user =:user"),
        @NamedQuery(name = "updateQuestion", query = "update QuestionEntity u SET u.content=:content where u.uuid =:uuid")

})
public class QuestionEntity implements Serializable {

=======
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "question", schema = "quora")
@NamedQueries(
        value = {
                @NamedQuery(name = "questionByUuid", query = "select q from AnswerEntity q where q.uuid = :uuid"),
        }
)

public class QuestionEntity implements Serializable {

    // Columns are: id, uuid, content, date, userId

>>>>>>> 49946125b824dc26dfc903a7114bd494890f5e83
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "UUID")
    @Size(max = 200)
    private String uuid;

    @Column(name = "CONTENT")
    @Size(max = 500)
<<<<<<< HEAD
    private String content;

    @Column(name = "DATE")
    @NotNull
    private ZonedDateTime date;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof QuestionEntity))
            return false;
        QuestionEntity that = (QuestionEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getUuid(), that.getUuid())
                && Objects.equals(getContent(), that.getContent()) && Objects.equals(getDate(), that.getDate())
                && Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUuid(), getContent(), getDate(), getUser());
=======
    private AnswerEntity content;

    @Column(name = "DATE")
    private String date;

    //Relationship with other entity

    @ManyToOne
    @Column(name = "USER_ID")
    private UserEntity userId;

    @OneToMany
    @Column(name = "ANSWER_ID")
    private AnswerEntity answerId;


    @Override
    public boolean equals(Object obj) {
        return new EqualsBuilder().append(this, obj).isEquals();
>>>>>>> 49946125b824dc26dfc903a7114bd494890f5e83
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

<<<<<<< HEAD
=======
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

>>>>>>> 49946125b824dc26dfc903a7114bd494890f5e83
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

<<<<<<< HEAD
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

}

=======
    public AnswerEntity getContent() {
        return content;
    }

    public void setContent(AnswerEntity content) {
        this.content = content;
    }

    public AnswerEntity getAnswerId() {
        return answerId;
    }

    public void setAnswerId(AnswerEntity answerId) {
        this.answerId = answerId;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this).hashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
>>>>>>> 49946125b824dc26dfc903a7114bd494890f5e83
