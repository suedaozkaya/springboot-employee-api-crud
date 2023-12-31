package com.mtco.service.concretes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.mtco.domain.User;
import com.mtco.dto.request.UserRegisterDTO;
import com.mtco.dto.request.UserUpdateDTO;
import com.mtco.dto.response.UserDTO;
import com.mtco.exception.ConflictException;
import com.mtco.exception.ResourceNotFoundException;
import com.mtco.mapper.UserMapper;
import com.mtco.repository.UserRepository;
import com.mtco.service.abstracts.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	private UserMapper userMapper;
	
	// Constructor Based Dependency Injection
	/*public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}*/ // @AllArgsConstructor annotation is added

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> userList = userRepository.findAll();
		List<UserDTO> userDTOList = userMapper.map(userList);
		return userDTOList;
	}
	
	@Override
	public UserDTO findUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(()-> 
		new ResourceNotFoundException("No user exists with id: " + id));
		
		return userMapper.userToUserDTO(user);
	}

	@Override
	public void createUser(@Valid UserRegisterDTO userRegisterDTO) {
		if(userRepository.existsByEmail(userRegisterDTO.getEmail())) {
			throw new ConflictException("User already exists with the following email address: " + userRegisterDTO.getEmail());
		}
		
		User user = new User();
		user.setName(userRegisterDTO.getName());
		user.setAge(userRegisterDTO.getAge()); // email pass phone
		user.setEmail(userRegisterDTO.getEmail());
		user.setPassword(userRegisterDTO.getPassword());
		user.setPhoneNumber(userRegisterDTO.getPhoneNumber());
		
		userRepository.save(user); // spring data jpa
	}

	@Override
	public void updateUser(Long id, UserUpdateDTO userUpdateDTO) {
		
		// first, i will check if the email exists in db since it must be unique
		boolean emailExists = userRepository.existsByEmail(userUpdateDTO.getEmail());
		User user = userRepository.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("No user exists with id: " + id));
		
		// second, check if new email is not same with the user's previous email
		if(emailExists && !userUpdateDTO.getEmail().equals(user.getEmail())) {
			// if the condition expression is true then the user wants to update their email to
			// an email which is already in use but not by the user. I will handle it by throwing a conflict exception
			throw new ConflictException("Email is already in use.");
		}
		
		// the following fields can be updated:
		user.setName(userUpdateDTO.getName());
		user.setAge(userUpdateDTO.getAge());
		user.setEmail(userUpdateDTO.getEmail());
		user.setPhoneNumber(userUpdateDTO.getPhoneNumber());
		
		userRepository.save(user);
	}
	
	@Override
	public void deleteUserById(Long id) {
		User user = userRepository.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("No user exists with id: " + id));
		userRepository.delete(user);
		
	}

	@Override
	public UserDTO findUserByIdAndAge(Long id, Integer age) {
		User user = userRepository.findUserByIdAndAge(id, age);
		
		return userMapper.userToUserDTO(user);
	}
	
	@Override
	public List<UserDTO> findUsersBetweenAge(Integer lowerAgeLimit, Integer upperAgeLimit){
		List<User> userList = userRepository.findUsersBetweenAge(lowerAgeLimit, upperAgeLimit);
		List<UserDTO> userDTOs = userMapper.map(userList);
		return userDTOs;
	}
	
}
