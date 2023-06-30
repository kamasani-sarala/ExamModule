package com.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.UserDto;
import com.demo.entity.User;
import com.demo.exception.NullUserFoundException;
import com.demo.services.UserService;


	@RestController
	@CrossOrigin("http://localhost:4200/")
	@RequestMapping("/User")
	public class UserController {
		@Autowired
		UserService userService;
		
		 final Logger mylogs = LoggerFactory.getLogger(this.getClass());
		
		@GetMapping("/")
		public String defaultMessage() {
		return "Welcome to User Page";
		}
		//http://localhost:8089/User/registerUser
		@PostMapping("/registerUser")
		public User addUser(@RequestBody UserDto user)  {
				return userService.addUser(user);
			
		}
		//http://localhost:8089/User/allUsers
		@GetMapping("/allUsers")
		public List<User> viewAllUsers(){
			return userService.viewAllUser();
		}
		
		//http://localhost:8089/User/updateUser
		@PutMapping("/updateUser")
		public User updateUser(@RequestBody UserDto user) {
			return userService.updateUser(user);
		}
		
		//http://localhost:8089/User/deleteUser
		@DeleteMapping("/deleteUser")
		public String deleteUser(@RequestBody UserDto user)  {
			return userService.deleteUser(user);
		}

		@GetMapping("/loginUser/{email}/{password}")
		public User loginUser(@PathVariable String email, @PathVariable String password) throws NullUserFoundException 
		{
			return userService.loginUser(email, password);
		}
		
	}



	

