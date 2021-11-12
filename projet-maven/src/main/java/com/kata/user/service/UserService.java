package com.kata.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kata.user.entity.User;
import com.kata.user.entity.UserAlreadyExistException;
import com.kata.user.entity.UserException;

/**
 * @author Axel Aboyeji
 *
 */
@Service
public interface UserService {
    User createUser(User user) throws UserException, UserAlreadyExistException;
    Optional<User> findUserById(Long id);
}
