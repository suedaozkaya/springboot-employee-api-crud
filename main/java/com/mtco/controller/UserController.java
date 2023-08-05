package com.mtco.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mtco.dto.request.UserRegisterDTO;
import com.mtco.dto.request.UserUpdateDTO;
import com.mtco.dto.response.UserDTO;
import com.mtco.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// welcome page
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to User Controller";
	}
	
	// get all users
	@GetMapping("/all")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		List<UserDTO> allUsers = userService.getAllUsers();
		return ResponseEntity.ok(allUsers);
	}
	
	// get a user by id
	@GetMapping("/query") // http://localhost:8080/api/user/query?id=2
	public ResponseEntity<UserDTO> getUserById(@RequestParam("id") Long id){
		UserDTO userDTO = userService.findUserById(id);
		return ResponseEntity.ok(userDTO);
	}
	
	// create a new user
	@PostMapping
	public ResponseEntity<Map<String, String>> createUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO){
		userService.createUser(userRegisterDTO);
		Map<String, String> map = new HashMap<>();
		map.put("message", "User is saved successfully.");
		map.put("status","true");
		
		return new ResponseEntity<>(map,HttpStatus.CREATED);
	}
	
	// update an existing user
	@PutMapping("/{id}")
	public ResponseEntity<Map<String,String>> updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateDTO userUpdateDTO ){
		userService.updateUser(id, userUpdateDTO);
		Map<String,String> map = new HashMap<>();
		map.put("message","User is updated successfully.");
		map.put("status","true");
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	// delete user
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String,String>> deleteUser(@PathVariable("id") Long id){
		userService.deleteUserById(id);
		Map<String,String> map = new HashMap<>();
		map.put("message","User deleted successfully.");
		map.put("status","true");
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	
	//**************************
	// get a user by id and email
	@GetMapping("/sql")
	public ResponseEntity<UserDTO> findUserByIdAndEmail(@RequestParam("id") Long id, @RequestParam("age") Integer age){
		UserDTO userDTO = userService.findUserByIdAndAge(id, age);
		return ResponseEntity.ok(userDTO);
		
	}
	
	// get users by age in a particular interval
	@GetMapping("/agebetween")
	public ResponseEntity<List<UserDTO>> findUsersBetweenAge(@RequestParam("lower") Integer lowerAgeLimit, @RequestParam("upper") Integer upperAgeLimit){
		List<UserDTO> usersInAgeInterval = userService.findUsersBetweenAge(lowerAgeLimit,upperAgeLimit);
		return ResponseEntity.ok(usersInAgeInterval);
		
	}
	
}
