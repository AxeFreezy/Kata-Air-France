package com.kata.user.entity;

/**
 * @author Axel Aboyeji
 *
 */
public class UserAlreadyExistException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistException(String message) {
		super(message);
	}
}
