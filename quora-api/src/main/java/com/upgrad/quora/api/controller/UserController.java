package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.SigninResponse;
import com.upgrad.quora.api.model.SignoutResponse;
import com.upgrad.quora.api.model.SignupUserRequest;
import com.upgrad.quora.api.model.SignupUserResponse;
import com.upgrad.quora.service.business.SigninBusinessService;
import com.upgrad.quora.service.business.SignoutBusinessService;
import com.upgrad.quora.service.business.SignupBusinessService;
import com.upgrad.quora.service.business.UserSignupService;
import com.upgrad.quora.service.dao.UserDAO;
import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.exception.AuthenticationFailedException;
import com.upgrad.quora.service.exception.SignOutRestrictedException;
import com.upgrad.quora.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private SignupBusinessService signupBusinessService;

    @Autowired
    private SigninBusinessService signinBusinessService;

    @Autowired
    private SignoutBusinessService signoutBusinessService;

    //Endpoint to register a new user
    @RequestMapping(method = RequestMethod.POST, path = "/user/signup", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SignupUserResponse> signup(final SignupUserRequest signupUserRequest) throws SignUpRestrictedException {

        final UserEntity userEntity = new UserEntity();

        //userEntity.setUuid(UUID.randomUUID().toString());
        //userEntity.setFirstname(signupUserRequest.getFirstName());
        //userEntity.setLastname(signupUserRequest.getLastName());
        //userEntity.setUsername(signupUserRequest.getUserName());
        //userEntity.setEmail(signupUserRequest.getEmailAddress());
        //userEntity.setPassword(signupUserRequest.getPassword());
        //userEntity.setSalt("123abc");
        //userEntity.setCountry(signupUserRequest.getCountry());
        //userEntity.setAboutme(signupUserRequest.getAboutMe());
        //userEntity.setDob(signupUserRequest.getDob());
        //userEntity.setRole("NonAdmin");
        //userEntity.setContactnumber(signupUserRequest.getContactNumber());

        final UserEntity createdUserEntity = signupBusinessService.signup(userEntity);
        SignupUserResponse userResponse = new SignupUserResponse();
        //.id(createdUserEntity.getUuid()).status("USER SUCCESSFULLY REGISTERED");
        return new ResponseEntity<SignupUserResponse>(userResponse, HttpStatus.CREATED);

    }

/*
    //Endpoint for signin user authentication
    @RequestMapping(method = RequestMethod.POST, path = "/user/signin", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SigninResponse> signin(@RequestHeader("authorization") final String authorization) throws AuthenticationFailedException {

        final UserAuthEntity userAuthEntity = signinBusinessService.signin(authorization);
        UserEntity user = userAuthEntity.getUser();
        SigninResponse authorizedUserResponse = new SigninResponse().id(user.getUuid()).message("SIGNED IN SUCCESSFULLY");
        HttpHeaders headers = new HttpHeaders();
        headers.add("access-token", userAuthEntity.getAccessToken());
        return new ResponseEntity<SigninResponse>(authorizedUserResponse, headers, HttpStatus.OK);
    }
*/
    //Method to signout the user of the Application
    //The Methods disables the user access to any other endpoints once signedout of Application
    @RequestMapping(method = RequestMethod.POST, path = "/user/signout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

    public ResponseEntity<SignoutResponse> signout(@RequestHeader("authorization") final String authorization) throws SignOutRestrictedException {

        final UserAuthEntity userAuthEntity = signoutBusinessService.validateUserSignIn(authorization);
        userAuthEntity.setLogoutAt(ZonedDateTime.now());
        signoutBusinessService.updateLogouttime(userAuthEntity);
        UserEntity userEntity = userAuthEntity.getUser();
        SignoutResponse signoutResponse = new SignoutResponse();
        //.id(userEntity.getUuid()).message("SIGNED OUT SUCCESSFULLY");
        return new ResponseEntity<SignoutResponse>(signoutResponse, HttpStatus.OK);
    }




}
