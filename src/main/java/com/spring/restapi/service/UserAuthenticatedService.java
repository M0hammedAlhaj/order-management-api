package com.spring.restapi.service;

import com.spring.restapi.dao.UserDao;
import com.spring.restapi.exception.UserNotAuthenticationException;
import com.spring.restapi.model.Role;
import com.spring.restapi.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;

@Service
public class UserAuthenticatedService {

    private final UserDao userDao;

    private final BCryptPasswordEncoder passwordEncoder;

    private final RoleService roleService;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserService userService;


    public UserAuthenticatedService(
            UserDao userDao,
            BCryptPasswordEncoder passwordEncoder,
            RoleService roleService,
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            UserService userService) {

        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Transactional
    public User registerUser(User user) throws RoleNotFoundException {
        Role role = roleService.findRoleByAuthority("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(role);
        return userDao.save(user);
    }

    @Transactional(readOnly = true)
    public User userLogin(String email, String password) throws UsernameNotFoundException {

        User user = userService.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));


        if (passwordEncoder.matches(password, user.getPassword()) &&
                user.getEmail().equals(email)) {
            return user;
        }
        throw new UserNotAuthenticationException();
    }

    public String sendTokenAfterLogin(String email) {
        return jwtService.generateToken(email);
    }


}
