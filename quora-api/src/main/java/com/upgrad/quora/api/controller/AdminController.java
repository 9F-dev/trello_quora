package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.UserDeleteResponse;
import com.upgrad.quora.api.model.UserDetailsResponse;
import com.upgrad.quora.service.business.UserAdminBusinessService;
import com.upgrad.quora.service.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AdminController {
    @Autowired
    private UserAdminBusinessService userAdminBusinessService;

    @RequestMapping(method = RequestMethod.DELETE, path = "/admin/user/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDeleteResponse> deleteUser(@PathVariable("userId") final String userId, @RequestHeader("authorization") final String authHeader) {
        String authToken = authHeader.split("Bearer ")[1];
        final UserEntity deletedUserEntity = userAdminBusinessService.delete(userId, authToken);
        UserDeleteResponse userResponse = new UserDeleteResponse().id(deletedUserEntity.getUuid()).status("USER SUCCESSFULLY DELETED");
        return new ResponseEntity<UserDeleteResponse>(userResponse, HttpStatus.OK);
    }
}