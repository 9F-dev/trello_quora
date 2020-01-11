package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public UserEntity createUser(UserEntity userEntity) {
        entityManager.persist(userEntity);
        return userEntity;
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
