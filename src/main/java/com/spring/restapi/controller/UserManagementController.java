package com.spring.restapi.controller;

import com.spring.restapi.exception.UserNotFoundException;
import com.spring.restapi.model.Address;
import com.spring.restapi.model.User;
import com.spring.restapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping("/api")
public class UserManagementController {


    private final UserService userService;


    public UserManagementController(UserService userService) {
        this.userService = userService;
    }


    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Object> roleNotFound(RoleNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }


    @GetMapping("/v1/admin/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) throws UserNotFoundException {
        return userService.findUserById(userId)
                .map((user) -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @GetMapping("/v1/users/profile")
    public ResponseEntity<User> getUser(Authentication authentication) {

        User user = userService.findUserByEmail(authentication.getName())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("v1/users/{id}/addresses")
    public ResponseEntity<User> addAddress(@PathVariable Integer id, @RequestBody Address address) throws UserNotFoundException {
        return new ResponseEntity<>(userService.addAddressToUser(id, address), HttpStatus.OK);
    }
}
