package com.mtco.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtco.domain.Employee;
import com.mtco.dto.EmployeeDTO;
import com.mtco.exception.ConflictException;
import com.mtco.exception.ResourceNotFoundException;
import com.mtco.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	// get all employees
	public List<EmployeeDTO> getAll() {
		List<Employee> employeeList = employeeRepository.findAll();
		List<EmployeeDTO> employeeDTOList = new ArrayList<>();
		for(Employee employee: employeeList) {
			EmployeeDTO employeeDTO = new EmployeeDTO(employee.getId(),employee.getName(),
					employee.getAge(),employee.getEmail(),employee.getPhoneNumber(),employee.getCreateDate());
			employeeDTOList.add(employeeDTO);
		}
		return employeeDTOList;
	}
	
	// get an employeeDTO by id
	public EmployeeDTO findEmployeeDTOById(Long id) {
		return employeeRepository.findEmployeeDTOById(id).orElseThrow(()-> 
		new ResourceNotFoundException("No employee exists with id: " + id));
	}

	// create a new employee
	public void createEmployee(@Valid Employee employee) {
		if(employeeRepository.existsByEmail(employee.getEmail())) {
			throw new ConflictException("Employee already exists with the following email address: " + employee.getEmail());
		}
		employeeRepository.save(employee); // spring data jpa
	}

	// update an existing employee
	public void updateEmployee(Long id, EmployeeDTO employeeDTO) {
		// first, i will check if the email exists in db
		boolean emailExists = employeeRepository.existsByEmail(employeeDTO.getEmail());
		Employee employee =findEmployeeById(id);
		
		// second, check if new email is not same with her/his previous email
		if(emailExists && !employeeDTO.getEmail().equals(employee.getEmail())) {
			// if the condition expression is true then the user wants to update their email to
			// an email which is already in use but not by him/her. I will handle it by throwing an conflict exception
			throw new ConflictException("Email already in use.");
		}
		
		// the following fields can be updated:
		employee.setName(employeeDTO.getName());
		employee.setAge(employeeDTO.getAge());
		employee.setEmail(employeeDTO.getEmail());
		employee.setPhoneNumber(employee.getPhoneNumber());
		
		employeeRepository.save(employee);
	}
	
	
	// get an employee by id method to use in service layer methods
	private Employee findEmployeeById(Long id) {
		return employeeRepository.findById(id).orElseThrow(()-> 
		new ResourceNotFoundException("No employee exists with id: " + id));
	}

	// delete an employee method 
	public void deleteEmployee(Long id) {
		Employee employee = findEmployeeById(id);
		employeeRepository.delete(employee);
		
	}


	
	
}
