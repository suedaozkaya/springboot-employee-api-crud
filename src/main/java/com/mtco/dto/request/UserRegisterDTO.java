package com.mtco.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {
	
	@NotBlank(message = "Name cannot be white space!")
	@Size(min = 2, max = 50, message = "Name must be between {min} and {max} long.")
	private String name;
	
	@Min(value = 16, message = "Age should not be less than 16.")
	@Max(value = 100, message = " Age should not ve greater than 100.")
	private Integer age;
	
	@Email(message = "Please provide a valid email!")
	private String email;
	
	@Size(min = 4, max = 20, message = "Please provide a correct size for password.")
	@NotBlank(message = "Please provide your password.")
	private String password;
	
	@Size(min = 14, max = 14)
    @NotBlank(message = "Please provide your phone number.")
	private String phoneNumber;
	
}
