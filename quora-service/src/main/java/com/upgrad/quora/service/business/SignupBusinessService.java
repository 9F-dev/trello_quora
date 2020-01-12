package com.upgrad.quora.service.business;


//import com.upgrad.quora.service.dao.UserDAO;
import com.upgrad.quora.service.dao.UserDao;
import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignupBusinessService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordCryptographyProvider cryptographyProvider;

    //Method to signup and persist userAuthentication detail in Database
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserEntity signup(UserEntity userEntity) throws SignUpRestrictedException {
        String[] encrytpedText = cryptographyProvider.encrypt(userEntity.getPassword());
        userEntity.setSalt(encrytpedText[0]);
        userEntity.setPassword(encrytpedText[1]);
        UserEntity createdUser = new UserEntity();
        return userDao.createUser(createdUser);

    }
}

/*
@Service
public class SignupBusinessService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordCryptographyProvider cryptographyProvider;

    //Method to signup and persit userAuthentication detail in Database
  /*  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserEntity signup(UserEntity userEntity, String userName, String userEmail) throws SignUpRestrictedException {

       // UserEntity userEntity = userAuthDao.getUserAuthFromAuthToken(authToken);

        String[] encrytpedText = cryptographyProvider.encrypt(userEntity.getPassword());
        userEntity.setSalt(encrytpedText[0]);
        userEntity.setPassword(encrytpedText[1]);

        UserEntity entityUserName = userDao.findByUsername(userName);
        if (entityUserName != null) {
            throw new SignUpRestrictedException("SGR-001", "Try any other Username, this Username has already been taken");
        }

        UserEntity entityEmail = userDao.findByEmail(userEmail);
        if (entityEmail != null) {
            throw new SignUpRestrictedException("SGR-002", "This user has already been registered, try with any other emailId");
        } */

     /*   if (entityUserName == null && entityEmail == null) {
            UserEntity entity = userDao.save(userEntity);
            signupUserResponse.setId(entity.getUuid());
            signupUserResponse.setStatus("'USER SUCCESSFULLY REGISTERED");
            return new ResponseEntity<SignupUserResponse>(signupUserResponse, HttpStatus.CREATED);
        }

        return null;
    }*/
        //UserEntity createdUser = new UserEntity();
        //   return userDao.createUser(userEntity);
        //return userDAO.createUser(userEntity);
    //}

