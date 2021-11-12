package com.kata.user.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kata.user.entity.User;
import com.kata.user.entity.UserAlreadyExistException;
import com.kata.user.entity.UserException;
import com.kata.user.repository.UserRepository;
import com.kata.user.service.UserService;


/**
 * @author Axel Aboyeji
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	
    private final UserRepository userRepository;
    
    /**
     * Constructor of the implementation of the Service
     * @param userRepository
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
    	this.userRepository = userRepository;
    }
    
  
	  /**
	   * method to validate a country
	 * @param country
	 * @throws UserException
	 */
	private void checkIsCountryValid(String country) throws UserException{
		  
		 
		  if(Objects.isNull(country)|| !country.equalsIgnoreCase("France")) {
			   
			   throw new UserException("Country is not France so it's not valid");
		  }
		  
		  
	    }
	
	  /**
	   * method to validate a birthdate
	 * @param birthdate
	 * @throws UserException
	 */
	private void checkIsBirthDateValid(LocalDate birthdate) throws UserException{
		  LocalDate todayDate = LocalDate.now();
		  Period period = Period.between(birthdate, todayDate);
		  if(period.getYears() < 18) {
			  throw new UserException("User is under 18");		  
			  } 
	    }
	
	  /**
	   * method to validate a name
	 * @param name
	 * @throws UserException
	 */
	private void checkIsNameValid(String name) throws UserException{
		  if(Objects.isNull(name)||name.equals("")) {
			   throw new UserException("Name is empty or not defined");
		  } 
	    }
	
	  /**
	   * method to validate a phoneNumber
	 * @param phoneNumber
	 * @throws UserException
	 */
	private void checkIsPhoneNumberValid(String phoneNumber) throws UserException{
		  if(Objects.isNull(phoneNumber)) {
			  return;
		  }
		  if(!Pattern.matches("^[0-9]{10}$", phoneNumber)) {
			   throw new UserException("Phone number is not 10 digits");
		  } 
		
	    }
	
	  /**
	   * method to validate a gender
	 * @param gender
	 * @throws UserException
	 */
	private void checkIsGenderValid(String gender) throws UserException{
		  if(Objects.isNull(gender)) {
			  return;
		  }
		  if(gender.equalsIgnoreCase("male")||gender.equalsIgnoreCase("female")) {		
			  return;
		  }
		 
		  throw new UserException("The gender is wrong");
		  
	  }
	 
	 
	
	/**
	 * method to create a user
	 */
	public User createUser(User user) throws UserException, UserAlreadyExistException {
		checkIsCountryValid(user.getCountry());
		checkIsBirthDateValid(user.getBirthDate());
		checkIsNameValid(user.getName());
		checkIsPhoneNumberValid(user.getPhoneNumber());
		checkIsGenderValid(user.getGender());
		checkIsUserAlreadyExist(user.getName(), user.getBirthDate());
		userRepository.save(user);
		return user;
	}
	
	/**
	 * method to find a user based on the id
	 */
	public Optional<User> findUserById(Long id) {
		return userRepository.findUserById(id);		
	}
	
	/**
	 * method to validate if a user already exist
	 * @param name
	 * @param birthdate
	 * @throws UserAlreadyExistException
	 */
	private void checkIsUserAlreadyExist(String name, LocalDate birthDate)throws UserAlreadyExistException {
		if(userRepository.findUserByNameAndBirthDate(name,birthDate).isPresent()) {
			throw new UserAlreadyExistException("User already exist");		
		}	
	}
	

	  
	  
}
