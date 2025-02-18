package com.spring.restapi.service;

import com.spring.restapi.dao.UserDao;
import com.spring.restapi.exception.UserNotFoundException;
import com.spring.restapi.model.Address;
import com.spring.restapi.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    @Transactional(readOnly = true)
    public Optional<User> findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Transactional(readOnly = true)
    public Optional<User> findUserById(Integer id) {
        if (userDao.findById(id).isPresent()) {
            return userDao.findById(id);
        }
        throw new UserNotFoundException(id);
    }

    @Transactional
    public User addAddressToUser(Integer id, Address address) {
        Optional<User> user = findUserById(id);
        if (user.isPresent()) {
            user.get().getAddresses().add(address);
            return userDao.save(user.get());
        }
        throw new UserNotFoundException(id);
    }
}
