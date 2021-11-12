package com.kata.user.web;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import  com.kata.user.entity.User;
import  com.kata.user.entity.UserAlreadyExistException;
import  com.kata.user.entity.UserException;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.Optional;

import  com.kata.user.service.UserService;

public class UserControllerTest {
	@InjectMocks
	private UserController userController;
	@Mock
    private UserService userServiceMock;
	
    

    @Before
    public void init() {

        this.userServiceMock = Mockito.mock(UserService.class);
        this.userController = new UserController(userServiceMock);
    }

    
    @Test
    public void should_returnBadRequest_when_userServiceThrowUserAlreadyExistException() throws UserException,UserAlreadyExistException {
        // given : le service lève une exception
        Mockito.when(userServiceMock.createUser(any()))
                .thenThrow(new UserAlreadyExistException("an exception"));
        // when
        ResponseEntity<User> userResponseEntity = userController.createUser(new User("n", LocalDate.MAX, "country", "000000", "pig"));
        // then
        assertEquals(HttpStatus.BAD_REQUEST,userResponseEntity.getStatusCode());

    }
    @Test
    public void should_returnBadRequest_when_userServiceThrowUserException() throws UserException,UserAlreadyExistException{
        // given : le service lève une exception
        Mockito.when(userServiceMock.createUser(any()))
                .thenThrow(new UserException("an exception"));
        // when
        ResponseEntity<User> userResponseEntity = userController.createUser(new User("n", LocalDate.MAX, "country", "000000", "pig"));
        // then
        assertEquals(HttpStatus.BAD_REQUEST,userResponseEntity.getStatusCode());

    }
    @Test
    public void should_returnCreated_when_userServiceWork() throws UserException,UserAlreadyExistException {
        LocalDate date = LocalDate.of(1997, 7, 5);
    	User user = new User("tata", date, "France","0652532936","male");
    	// given : le service renvoie un user
        Mockito.when(userServiceMock.createUser(any()))
                .thenReturn(user);
        // when
        ResponseEntity<User> userResponseEntity = userController.createUser(user);
        // then
        assertEquals(HttpStatus.CREATED,userResponseEntity.getStatusCode());
    }

    @Test
    public void should_returnNotFound_when_userIsNotFoundByService() {
        // given : le service un optional vide
        Mockito.when(userServiceMock.findUserById(1L))
                .thenReturn(Optional.empty());
        // when
        ResponseEntity<User> userResponseEntity = userController.getUserById(1L);
        // then
        assertEquals(HttpStatus.NOT_FOUND,userResponseEntity.getStatusCode());

    }
    @Test
    public void should_returnOk_when_userIsFoundByService()  {
        // given : le service renvoie un user
    	LocalDate date = LocalDate.of(1997, 7, 5);
    	User user = new User("tata", date, "France","0652532936","male");
    	Mockito.when(userServiceMock.findUserById(1L))
        .thenReturn(Optional.of(user));
        // when
        ResponseEntity<User> userResponseEntity = userController.getUserById(1L);
        // then
        assertEquals(HttpStatus.OK,userResponseEntity.getStatusCode());

    }
    @Test
    public void should_returnBadRequest_when_userIsNull() {
        
        // when
        ResponseEntity<User> userResponseEntity = userController.createUser(null);
        // then
        assertEquals(HttpStatus.BAD_REQUEST,userResponseEntity.getStatusCode());

    }


}
