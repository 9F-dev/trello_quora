package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.AnswerEntity;
import com.upgrad.quora.service.exception.InvalidQuestionException;
import com.upgrad.quora.service.exception.SignUpRestrictedException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class AnswerDao {

    @PersistenceContext
    private EntityManager entityManager;


    public AnswerEntity createAnswer(AnswerEntity answerEntity) throws InvalidQuestionException {
        try {
            entityManager.persist(answerEntity);
            return answerEntity;
        } catch (PersistenceException ex) {
            Throwable t = ex.getCause();

            if (t instanceof ConstraintViolationException) {

                if (((ConstraintViolationException) t).getConstraintName().toString()
                        .equalsIgnoreCase("users_questionId_key")) {
                    throw new InvalidQuestionException("QUES-001",
                            "The question entered is invalid");
                }
            }
            return null;
        }
    }
    public AnswerEntity getAnswerById(String uuid)
    {
        try {
            return entityManager.createNamedQuery("getAnswerFromId", AnswerEntity.class).setParameter("uuid", uuid)
                    .getSingleResult();
        }catch (NoResultException nre)
        {
            return null;
        }
    }

    public AnswerEntity checkAnswerBelongToUser(String auuid, String uuuid)
    {

        try {
            return entityManager.createNamedQuery("checkAnswerBelongToUser", AnswerEntity.class).setParameter("auuid", auuid).setParameter("uuuid",uuuid).getSingleResult();
        }catch (NoResultException nre)
        {
            return null;
        }
    }

    public AnswerEntity updateAnswer(AnswerEntity answerEntity)
    {
        return entityManager.merge(answerEntity);
    }

    public AnswerEntity deleteAnswer(AnswerEntity answerEntity)
    {
        AnswerEntity newAnswerEntity = getAnswerById(answerEntity.getUuid());
        entityManager.remove(newAnswerEntity);
        return answerEntity;
    }

    public List<AnswerEntity> getAllAnswers(String questionId)
    {
        return entityManager.createNamedQuery("getAllAnswers",AnswerEntity.class)
                .setParameter("uuid",questionId).getResultList();
    }
}
