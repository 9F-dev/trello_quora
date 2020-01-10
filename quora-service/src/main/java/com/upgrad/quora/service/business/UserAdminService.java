package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.UserDAO;
import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.exception.SignUpRestrictedException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@Service
public class UserAdminService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    private PasswordCryptographyProvider cryptographyProvider;

    @PersistenceContext
    private EntityManager entityManager;

    public UserEntity createUser(UserEntity userEntity) throws SignUpRestrictedException {

        String password = userEntity.getPassword();
        if (password == null) {
            userEntity.setPassword("quora@123");
        }
        String[] encryptedText = cryptographyProvider.encrypt(userEntity.getPassword());
        userEntity.setSalt(encryptedText[0]);
        userEntity.setPassword(encryptedText[1]);

        try {
            entityManager.persist(userEntity);
            return userEntity;
        } catch (PersistenceException ex) {
            Throwable t = ex.getCause();

            if (t instanceof ConstraintViolationException) {

                if (((ConstraintViolationException) t).getConstraintName().equalsIgnoreCase("users_username_key")) {
                    throw new SignUpRestrictedException("SGR-001", "Try any other Username, this Username has already been taken");
                } else if (((ConstraintViolationException) t).getConstraintName().equalsIgnoreCase("users_email_key")) {
                    throw new SignUpRestrictedException("SGR-002", "This user has already been registered, try with any other emailId");
                }
            }
            return null;
        }

    }

/*
    //Method to signup and persist userAuthentication detail in Database
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserEntity signup(UserEntity userEntity) throws SignUpRestrictedException {
        String[] encryptedText = cryptographyProvider.encrypt(userEntity.getPassword());
        userEntity.setSalt(encryptedText[0]);
        userEntity.setPassword(encryptedText[1]);
        UserEntity createdUser = new UserEntity();
        return userDAO.createUser(userEntity);
    }
*/
/*
    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity createUser(final UserEntity userEntity) {


        UserEntity entityUserName = userDAO.findByUsername(signupUserRequest.getUserName());
        if (entityUserName != null) {
            try {
                throw new SignUpRestrictedException("SGR-001", "Try any another Username, this Username has already been taken");
            } catch (SignUpRestrictedException e) {
                e.printStackTrace();
            }
            // throw new SignUpRestrictedException("SGR-001", "Try any other Username, this Username has already been taken");
        }

        UserEntity entityEmail = userDAO.findByEmail(signupUserRequest.getEmailAddress());
        if (entityEmail != null) {
            try {
                throw new SignUpRestrictedException("SGR-002", "This user has already been registered, try with any other emailId");
            } catch (SignUpRestrictedException e) {
                e.printStackTrace();
            }

        }

        if (entityUserName == null && entityEmail == null) {
            UserEntity entity = userDAO.save(userEntity);
            signupUserResponse.setId(entity.getUuid());
            signupUserResponse.setStatus("'USER SUCCESSFULLY REGISTERED");
            return new ResponseEntity<SignupUserResponse>(signupUserResponse, HttpStatus.CREATED);
        }
        String password = userEntity.getPassword();
        if (password == null) {
            userEntity.setPassword("quora@123");
        }
        String[] encryptedText = cryptographyProvider.encrypt(userEntity.getPassword());
        userEntity.setSalt(encryptedText[0]);
        userEntity.setPassword(encryptedText[1]);
        return userDAO.createUser(userEntity);

    }

    public UserEntity getUserByEmail(final String email) {
        try {
            return entityManager.createNamedQuery("userByEmail", UserEntity.class).setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
*/
}
