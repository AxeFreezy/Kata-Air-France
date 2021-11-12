package com.kata.user.web;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import  com.kata.user.entity.User;
import  com.kata.user.entity.UserAlreadyExistException;
import  com.kata.user.entity.UserException;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import  com.kata.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Axel Aboyeji
 *
 */
@Api(value = "API for CRUD operations of a user")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
    private UserService userService;
	
	
	/**
	 * Constructor of the controller
	 * @param userService
	 */
	public UserController(UserService userService) {
		this.userService = userService;
		
	}
	
	/**
	 * method to retrieve a user based on the ID
	 * @param userId
	 * @return code HTTP threw a responseEntity according to each case
	 */
	@ApiOperation(value = "Get a user with ID")
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId){
			if(Objects.isNull(userId)) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			Optional<User> userById = userService.findUserById(userId);
			if(!userById.isPresent()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(userById.get(),HttpStatus.OK);
	}
	/**
	 * method to create a user 
	 * @param user
	 * @return code HTTP threw a responseEntity according to each case
	 */
	@ApiOperation(value = "Create a user")
	@PostMapping("/create")
	public ResponseEntity<User>  createUser(@RequestBody User user){
		
		if((Objects.isNull(user))) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {	
			userService.createUser(user);		
		}catch (UserException|UserAlreadyExistException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}
}
