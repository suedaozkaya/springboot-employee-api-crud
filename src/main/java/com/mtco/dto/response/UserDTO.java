package com.mtco.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
public class UserDTO {
	
	private Long id;
	
	private String name;
	
	private Integer age;

	private String email;
	
	private String phoneNumber;
	
	private LocalDateTime createDate;
	
}
