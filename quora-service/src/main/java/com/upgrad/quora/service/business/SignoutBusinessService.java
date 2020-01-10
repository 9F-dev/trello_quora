package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.UserDAO;
import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.exception.SignOutRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignoutBusinessService {

    @Autowired
    private UserDAO userDAO;

    //Method to check if user is signed in or not
    @Transactional(propagation = Propagation.REQUIRED)
    public UserAuthEntity validateUserSignIn(final String accessToken) throws SignOutRestrictedException {
        UserAuthEntity userAuthTokenEntity = userDAO.validateUser(accessToken);
        if (userAuthTokenEntity == null) {
            throw new SignOutRestrictedException("SGR-001", "User is not Signed in");
        }
        return userAuthTokenEntity;
    }

    //Method to update LogoutTime of the user
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateLogouttime(final UserAuthEntity userAuthTokenEntity) {
        userDAO.persisAuthtokenEntity(userAuthTokenEntity);
    }


}
