package com.upgrad.quora.service.entity;

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

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "UUID")
    @Size(max = 200)
    private String uuid;

    @Column(name = "CONTENT")
    @Size(max = 500)
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
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

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