package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.UserAuthDao;
import com.upgrad.quora.service.dao.UserDao;
import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAdminBusinessService {

    public enum UserDeleteStatus {
        ATHR001, ATHR002, ATHR003, USR001, SUCCESS;
    }

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserAuthDao userAuthDao;

    @Transactional(propagation = Propagation.REQUIRED)  //check this later

    public UserDeleteStatus delete(String userUuid, String authToken) {

        UserAuthEntity userAuthEntity = userAuthDao.getUserAuthFromAuthToken(authToken);
        UserDeleteStatus userDeleteStatus = null;

        if (userAuthEntity == null) {                               //User has not signed in
            userDeleteStatus = UserDeleteStatus.ATHR001;
        }

        if (userAuthEntity.getLogoutAt() != null) {                 //User is signed out
            userDeleteStatus = UserDeleteStatus.ATHR002;
        }

        if (!userAuthEntity.getUser().getRole().equals("Admin")) {   //Unauthorized Access, Entered user is not an admin
            userDeleteStatus = UserDeleteStatus.ATHR003;
        }

        if (userAuthEntity.getUser().getRole().equals("Admin")) {
                if (userDao.getUser(userUuid) != null) {            //USER SUCCESSFULLY DELETED
                userDao.deleteUser(userUuid);
                userDeleteStatus = UserDeleteStatus.SUCCESS;
            } else {                                                //User with entered uuid to be deleted does not exist
                userDeleteStatus = UserDeleteStatus.USR001;
            }
        }
        return userDeleteStatus;
    }
}