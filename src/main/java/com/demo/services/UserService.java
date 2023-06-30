package com.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.dto.UserDto;
import com.demo.entity.User;
import com.demo.exception.InvalidExamIdException;
import com.demo.exception.NullUserFoundException;


@Service
public interface UserService {

	public List<User> viewAllUser();

	public String deleteUser(UserDto user);

	public User loginUser(String email, String password) throws NullUserFoundException;

	public User updateUser(UserDto user);
    
	public List<User> getUserByuserId(int userId) throws InvalidExamIdException;

	public User addUser(UserDto user);
}
