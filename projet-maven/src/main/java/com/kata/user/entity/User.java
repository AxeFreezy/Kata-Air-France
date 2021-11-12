package com.kata.user.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @author Axel Aboyeji
 *
 */

@Entity
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
	private String name;
	private LocalDate birthDate;
	private String country;
	private String phoneNumber;
	private String gender;
	/**
	 * Constructor for a user
	 * @param id
	 * @param name
	 * @param birthDate
	 * @param country
	 * @param phoneNumber
	 * @param gender
	 */
	public User(String name, LocalDate birthDate, String country, String phoneNumber, String gender) {
		this.name = name;
		this.birthDate = birthDate;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}
	public User () {
		
	}
	/**
	 * Getter for id
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Getter for name
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter for birthDate
	 * @return the birthDate
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	/**
	 * Getter for country
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * Getter for phoneNumber
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * Getter for gender
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", country=" + country
				+ ", phoneNumber=" + phoneNumber + ", gender=" + gender + "]";
	}
	
}
