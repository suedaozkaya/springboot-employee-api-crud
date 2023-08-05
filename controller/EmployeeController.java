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

import com.mtco.domain.Employee;
import com.mtco.dto.EmployeeDTO;
import com.mtco.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	// welcome page
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to Employee Controller";
	}
	
	// get all employees
	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> getAllUsers(){
		List<EmployeeDTO> employees = employeeService.getAll();
		return ResponseEntity.ok(employees);
	}
	
	// get an employee by id
	@GetMapping("/query") // http://localhost:8080/api/employees/query?id=2
	public ResponseEntity<EmployeeDTO> getEmployeeById(@RequestParam("id") Long id){
		EmployeeDTO employee = employeeService.findEmployeeDTOById(id);
		return ResponseEntity.ok(employee);
	}
	
	// create a new employee
	@PostMapping
	public ResponseEntity<Map<String, String>> createEmployee(@Valid @RequestBody Employee employee){
		employeeService.createEmployee(employee);
		Map<String, String> map = new HashMap<>();
		map.put("message", "Employee is saved successfully.");
		map.put("status","true");
		return new ResponseEntity<>(map,HttpStatus.CREATED);
	}
	
	// update an existing employee
	@PutMapping("/{id}")
	public ResponseEntity<Map<String,String>> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDTO employeeDTO ){
		employeeService.updateEmployee(id, employeeDTO);
		Map<String,String> map = new HashMap<>();
		map.put("message","Employee is updated successfully.");
		map.put("status","true");
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	// delete employee
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String,String>> deleteEmployee(@PathVariable("id") Long id){
		employeeService.deleteEmployee(id);
		Map<String,String> map = new HashMap<>();
		map.put("message","Employee deleted successfully.");
		map.put("status","true");
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	
	
}
