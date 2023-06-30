package com.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.UserDto;
import com.demo.entity.User;
import com.demo.exception.InvalidExamIdException;
import com.demo.exception.NullUserFoundException;
import com.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<User> viewAllUser() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public String deleteUser(UserDto user) {
        // Add logic to delete user from repository or service
        logger.info("Deleting user: {}", user);
        // ...
        return null;
    }

    @Override
    public User loginUser(String email, String password) throws NullUserFoundException {
        logger.info("Logging in user with email: {}", email);
        if (email.equals("") || email.equals("string") || password.equals("") || password.equals("string")) {
            throw new NullUserFoundException("Email or Password Cannot be empty");
        }

        User user = userRepository.findUserByEmailPassword(email, password);

        if (user == null) {
            throw new NullUserFoundException("Cannot login. Email and password do not match");
        }

        return user;
    }

    @Override
    public User updateUser(UserDto user) {
        logger.info("Updating user: {}", user);
        return userRepository.save(user);
    }

    @Override
    public List<User> getUserByuserId(int userId) throws InvalidExamIdException {
        logger.info("Fetching user by userId: {}", userId);
        List<User> users = new ArrayList<>();
        for (User user : users) {
            if (user.getUserId() == userId) {
                return users;
            }
        }

        throw new InvalidExamIdException("User not found with userId: " + userId);
    }

    @Override
    public User addUser(UserDto user) {
        logger.info("Adding a new user: {}", user);
        return userRepository.save(user);
    }

}
