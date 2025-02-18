package com.spring.restapi.controller;

import com.spring.restapi.dto.LoginRequest;
import com.spring.restapi.model.User;
import com.spring.restapi.service.UserAuthenticatedService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping("/api")
public class UserAuthenticationController {
    private final UserAuthenticatedService userAuthenticatedService;

    public UserAuthenticationController(UserAuthenticatedService userAuthenticatedService) {
        this.userAuthenticatedService = userAuthenticatedService;
    }

    @PostMapping("/v1/authenticated/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest) {
        User user = userAuthenticatedService.userLogin(
                loginRequest.getUserName(),
                loginRequest.getPassword());

        String token = userAuthenticatedService.sendTokenAfterLogin(user.getEmail());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/v1/authenticated/register")
    public ResponseEntity<User> register(@Valid @RequestBody User user) throws RoleNotFoundException {
        User user1 = userAuthenticatedService.registerUser(user);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }

}
