package com.kata.user.repository;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  com.kata.user.entity.User;
/**
 * @author Axel Aboyeji
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User save(User user);
	Optional<User> findUserById(Long id);
	Optional<User> findUserByNameAndBirthDate(String name, LocalDate birthDate);
}
