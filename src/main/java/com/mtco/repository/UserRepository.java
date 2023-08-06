package com.mtco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mtco.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);
	
	@Query(value = "SELECT * FROM users_tbl WHERE id = ?1 AND age = ?2", nativeQuery = true)
	User findUserByIdAndAge(Long id, Integer age);
	
	@Query(value = "SELECT * FROM users_tbl WHERE age BETWEEN ?1 AND ?2",  nativeQuery = true)
	List<User> findUsersBetweenAge(Integer lowerAgeLimit, Integer upperAgeLimit);
}
