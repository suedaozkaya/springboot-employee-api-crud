package com.mtco.service.abstracts;

import java.util.List;

import javax.validation.Valid;

import com.mtco.dto.request.UserRegisterDTO;
import com.mtco.dto.request.UserUpdateDTO;
import com.mtco.dto.response.UserDTO;

public interface UserService {

	// get all users
	public List<UserDTO> getAllUsers();
	
	// get a UserDTO by id
	public UserDTO findUserById(Long id);

	// create a new user
	public void createUser(@Valid UserRegisterDTO userRegisterDTO);

	// update an existing user
	public void updateUser(Long id, UserUpdateDTO userUpdateDTO);
	
	// delete a user method 
	public void deleteUserById(Long id);

	// get a user by id and email
	public UserDTO findUserByIdAndAge(Long id, Integer age);
	
	// get users by age in a particular interval
	public List<UserDTO> findUsersBetweenAge(Integer lowerAgeLimit, Integer upperAgeLimit);
	
}
