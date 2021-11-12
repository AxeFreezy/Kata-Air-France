package com.kata.user.service;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;


import  com.kata.user.entity.User;
import  com.kata.user.entity.UserAlreadyExistException;
import  com.kata.user.entity.UserException;
import  com.kata.user.impl.UserServiceImpl;
import  com.kata.user.repository.UserRepository;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;



public class UserServiceTest {
	  private UserService userService;
	  private UserRepository mockRepository;
	 
	    @Before
	    public void init(){
	        this.mockRepository = Mockito.mock(UserRepository.class);
	        userService = new UserServiceImpl(mockRepository);
	    }


	    @Test
	    public void should_CreateUser() throws UserException, UserAlreadyExistException{

	        // GIVEN // argument
	        String name = "Toto";
	        LocalDate birthDate =  LocalDate.of(1997, 1, 10);
	        String country = "France";
	        String phoneNumber = "0652532936";
	        String gender = "male";
	        // WHEN= on creer un user
	        User toto = userService.createUser(new User(name,birthDate,country,phoneNumber,gender));

	        //THEN on teste
	        assertEquals("Toto", toto.getName());
	        assertEquals(LocalDate.of(1997, 1, 10).toString(), toto.getBirthDate().toString());
	        assertEquals("France",toto.getCountry());
	        assertEquals("0652532936",toto.getPhoneNumber());
	        assertEquals("male", toto.getGender());
	    }
	    
	    @Test(expected = UserException.class)
	    public void should_ThrowExcpetion_When_NullCountry() throws UserException, UserAlreadyExistException {
	    	String name = "Toto";
	        LocalDate birthDate =  LocalDate.of(1997, 1, 10);
	        String country = null;
	        String phoneNumber = "0652532936";
	        String gender = "male";
	        User toto = userService.createUser(new User(name,birthDate,country,phoneNumber,gender));

	    }
	    
	    @Test(expected = UserException.class)
	    public void should_ThrowExcpetion_When_EmptyCountry() throws UserException, UserAlreadyExistException {
	    	String name = "Toto";
	        LocalDate birthDate =  LocalDate.of(1997, 1, 10);
	        String country = "";
	        String phoneNumber = "0652532936";
	        String gender = "male";
	        User toto = userService.createUser(new User(name,birthDate,country,phoneNumber,gender));

	    }
	    @Test(expected = UserException.class)
	    public void should_ThrowExcpetion_When_NullBirthdate() throws UserException, UserAlreadyExistException {
	    	String name = "Toto";
	        LocalDate birthDate =  null;
	        String country = "Germany";
	        String phoneNumber = "0652532936";
	        String gender = "male";
	        User toto = userService.createUser(new User(name,birthDate,country,phoneNumber,gender));

	    }
	    @Test(expected = UserException.class)
	    public void should_ThrowExcpetion_When_NullName() throws UserException, UserAlreadyExistException {
	    	String name = null;
	        LocalDate birthDate =  LocalDate.of(1997, 1, 10);
	        String country = "Germany";
	        String phoneNumber = "0652532936";
	        String gender = "male";
	        User toto = userService.createUser(new User(name,birthDate,country,phoneNumber,gender));

	    }
	    @Test(expected = UserException.class)
	    public void should_ThrowExcpetion_When_EmptyName() throws UserException, UserAlreadyExistException {
	    	String name = "";
	        LocalDate birthDate =  LocalDate.of(1997, 1, 10);
	        String country = "Germany";
	        String phoneNumber = "0652532936";
	        String gender = "male";
	        User toto = userService.createUser(new User(name,birthDate,country,phoneNumber,gender));

	    }
	    @Test(expected = UserException.class)
	    public void should_ThrowExcpetion_When_CountryDifferentFromFrance() throws UserException, UserAlreadyExistException {
	    	String name = "";
	        LocalDate birthDate =  LocalDate.of(1997, 1, 10);
	        String country = "Germany";
	        String phoneNumber = "0652532936";
	        String gender = "male";
	        User toto = userService.createUser(new User(name,birthDate,country,phoneNumber,gender));

	    }
	    @Test(expected = UserException.class)
	    public void should_ThrowExcpetion_When_UserIsMinor() throws UserException, UserAlreadyExistException {
	    	String name = "";
	        LocalDate birthDate =  LocalDate.of(2008, 1, 10);
	        String country = "France";
	        String phoneNumber = "0652532936";
	        String gender = "male";
	        User toto = userService.createUser(new User(name,birthDate,country,phoneNumber,gender));

	    }
	    @Test(expected = UserException.class)
	    public void should_ThrowExcpetion_When_PhoneNumberIsNot10Digit() throws UserException, UserAlreadyExistException {
	    	String name = "Toto";
	        LocalDate birthDate =  LocalDate.of(2008, 1, 10);
	        String country = "France";
	        String phoneNumber = "065253293";
	        String gender = "male";
	        User toto = userService.createUser(new User(name,birthDate,country,phoneNumber,gender));

	    }
	    @Test
	    public void should_CreateUser_When_PhoneNumberIsNull() throws UserException, UserAlreadyExistException {
	        String name = "Toto";
	        LocalDate birthDate = LocalDate.of(1997, 1, 10);
	        String country = "France";
	        String gender = "male";
	        User toto = userService.createUser(new User(name, birthDate, country, null, gender));

	    }
	    @Test
	    public void should_CreateUser_When_GenderIsNull() throws UserException, UserAlreadyExistException {
	        String name = "Toto";
	        LocalDate birthDate = LocalDate.of(1997, 1, 10);
	        String country = "France";
	        String phoneNumber = "0652532936";
	        User toto = userService.createUser(new User(name, birthDate, country, phoneNumber, null));

	    }
	    @Test(expected = UserException.class)
	    public void should_ThrowException_When_GenderIsWrong() throws UserException, UserAlreadyExistException {
	        String name = "Toto";
	        LocalDate birthDate = LocalDate.of(1997, 1, 10);
	        String country = "France";
	        String gender = "abcd";
	        User toto = userService.createUser(new User(name, birthDate, country, null, gender));

	    }


	    @Test
	    public void should_SaveUser_When_CreateUser() throws UserException, UserAlreadyExistException {
	        // given
	        String name = "Toto";
	        LocalDate birthDate = LocalDate.of(1997, 1, 10);
	        String country = "France";

	        //when
	        User toto = userService.createUser(new User(name, birthDate, country, null, null));

	        //then
	        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
	        Mockito.verify(mockRepository, Mockito.times(1)).save(argumentCaptor.capture());
	        User userSaved = argumentCaptor.getValue();
	        assertEquals("Toto", userSaved.getName());
	        assertEquals(LocalDate.of(1997, 1, 10), userSaved.getBirthDate());
	        assertEquals("France", userSaved.getCountry());
	        assertNull("PhoneNumber should be null for this test",userSaved.getPhoneNumber());
	        assertNull("Gender should be null for this test",userSaved.getGender());
	    }
	    
	    @Test
	    public void should_findUserThrowException_When_UserIsNotFound() {
	    	
	    	// given : le repo renvoie un optional vide
	    	
	        Mockito.when(mockRepository.findUserById(1L))
	                .thenReturn(Optional.empty());
	        // when
	        Optional<User> user = userService.findUserById(1L);
	        //then
	        assertEquals(Optional.empty(),user);
	    }
	    @Test
	    public void should_getUserReturnAUSer_when_userExist() {
	    	// given : le repo renvoie un optional de user plein
	    	LocalDate date = LocalDate.of(1997, 7, 5);
	    	User user = new User("t",date,"France","0652532936","male");
	        Mockito.when(mockRepository.findUserById(1L))
	                .thenReturn(Optional.of(user));
	        // when
	        userService.findUserById(1L);
	        //then
	        assertEquals(user,userService.findUserById(1L).get());
	        
	    }
	    @Test(expected = UserAlreadyExistException.class)
	    public void should_findUserThrowException_when_userAlreadyExist()throws UserAlreadyExistException, UserException {
	        
	    	// given : le repo un optional de user plein
	    	LocalDate date = LocalDate.of(1997, 7, 5);
	    	User user = new User("t",date,"France","0652532936","male");
	        Mockito.when(mockRepository.findUserByNameAndBirthDate(anyString(), any()))
	                .thenReturn(Optional.of(user));
	        // when
	        userService.createUser(new User("t",date,"France","0652532936","male"));
	        
	        // then
	        // expected = UserAlreadyExistException
	        
	    }
	    @Test
	    public void should_createUser_when_userDontExist() throws UserAlreadyExistException, UserException {
	        
	    	// given : le repo renvoie un optional empty
	    	LocalDate date = LocalDate.of(1997, 7, 5);
	        Mockito.when(mockRepository.findUserByNameAndBirthDate(anyString(), any()))
	                .thenReturn(Optional.empty());
	        // when
	        User toto= userService.createUser(new User("t",date,"France","0652532936","male"));
	        
	        // then
	        assertEquals("t", toto.getName());
	        assertEquals(LocalDate.of(1997, 7, 5).toString(), toto.getBirthDate().toString());
	        assertEquals("France",toto.getCountry());
	        assertEquals("0652532936",toto.getPhoneNumber());
	        assertEquals("male", toto.getGender());
	        
	    }
	  
	    


}
