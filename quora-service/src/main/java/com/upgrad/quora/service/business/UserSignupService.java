package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.UserDAO;
import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserSignupService {
/*
    @Autowired
    UserDAO userDAO;

    @Autowired
    private PasswordCryptographyProvider cryptographyProvider;

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
    @Autowired
    private UserAdminService userAdminService;


    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity signup(UserEntity userEntity) throws SignUpRestrictedException{
        return userAdminService.createUser(userEntity);
    }


}
