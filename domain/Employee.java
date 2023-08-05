package com.mtco.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_tbl")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Name cannot be null!")
	@NotBlank(message = "Name cannot be white space!")
	@Size(min = 2, max = 50, message = "Name must be between {min} and {max} long.")
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column
	private Integer age;
	
	@Column(nullable = false, length = 50, unique = true)
	@Email(message = "Please provide a valid email!")
	private String email;
	
	@Column
	private String phoneNumber;
	
	private LocalDateTime createDate = LocalDateTime.now();
	
}
