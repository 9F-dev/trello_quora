package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.UserAuthDao;
import com.upgrad.quora.service.dao.UserDao;
import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAdminBusinessService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserAuthDao userAuthDao;

    @Transactional(propagation = Propagation.REQUIRED)

    public UserEntity delete(String userUuid, String authToken) throws AuthorizationFailedException, UserNotFoundException {

        UserAuthEntity userAuthEntity = userAuthDao.getUserAuthFromAuthToken(authToken);

        if (userAuthEntity == null) {                               //User has not signed in
            throw new AuthorizationFailedException("ATHR-001", "User has not signed in");
        }

        if (userAuthEntity.getLogoutAt() != null) {                 //User is signed out
            throw new AuthorizationFailedException("ATHR-002", "User is signed out");
        }

        if (!userAuthEntity.getUser().getRole().equals("Admin")) {   //Unauthorized Access, Entered user is not an admin
            throw new AuthorizationFailedException("ATHR-003", "Unauthorized Access, Entered user is not an admin");
        }


        if (userDao.getUser(userUuid) != null) {

            return userDao.deleteUser(userUuid);                      //USER SUCCESSFULLY DELETED
        } else throw new UserNotFoundException("USR-001","User with entered uuid to be deleted does not exist");
    }
}