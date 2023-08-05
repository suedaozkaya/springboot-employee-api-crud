package com.mtco.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mtco.domain.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
// No need to add hibernate annotations, this class will not go to database.
// I added only lombok and validation annotations.
public class EmployeeDTO {
	
	private Long id;
	
	@NotNull(message = "Name cannot be null!")
	@NotBlank(message = "Name cannot be white space!")
	@Size(min = 2, max = 50, message = "Name must be between {min} and {max} long.")
	private String name;
	
	private Integer age;
	
	@Email(message = "Please provide a valid email!")
	private String email;
	
	private String phoneNumber;
	
	private LocalDateTime createDate = LocalDateTime.now();
	
	// this is for the pojo class that comes from db and goes to controller layer
	public EmployeeDTO(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
		this.age = employee.getAge();
		this.email = employee.getEmail();
		this.phoneNumber = employee.getPhoneNumber();
		this.createDate = employee.getCreateDate();
	}
}
