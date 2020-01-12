package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.exception.SignUpRestrictedException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@Repository
public class UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public UserEntity createUser(UserEntity userEntity) throws SignUpRestrictedException{
        try {
            entityManager.persist(userEntity);
            return userEntity;
        } catch (PersistenceException ex) {
            Throwable t = ex.getCause();

            if (t instanceof ConstraintViolationException) {

                if (((ConstraintViolationException) t).getConstraintName().toString().equalsIgnoreCase("users_username_key")) {
                    throw new SignUpRestrictedException("SGR-001", "Try any other Username, this Username has already been taken");
                } else if (((ConstraintViolationException) t).getConstraintName().toString().equalsIgnoreCase("users_email_key")) {
                    throw new SignUpRestrictedException("SGR-002", "This user has already been registered, try with any other emailId");
                }
            }
            return null;
        }
    }

    public UserEntity findByUsername(final String userName) {
        try {
            return entityManager.createNamedQuery("userByUsername", UserEntity.class).setParameter("username", userName).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public UserEntity findByEmail(final String userEmail) {
        try {
            return entityManager.createNamedQuery("userByEmail", UserEntity.class).setParameter("email", userEmail).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public UserAuthEntity validateUser(final String token) {
        try {
            return entityManager.createNamedQuery("userByAccessToken", UserAuthEntity.class).setParameter("token", token).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public UserAuthEntity getUserAuthDetails(String userUuid, String token) {
        try {
            return entityManager.createNamedQuery("getUserAuthDetailsByUuid", UserAuthEntity.class).setParameter("userUuid", userUuid).setParameter("token", token).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public UserEntity getUser(final String userUuid) {
        return entityManager.createNamedQuery("userByUuid", UserEntity.class).setParameter("uuid", userUuid).getSingleResult();
    }

    public UserEntity deleteUser (final String userUuid){
        return entityManager.createNamedQuery("deleteByUserUuid", UserEntity.class).setParameter("uuid", userUuid).getSingleResult();
    }
}
