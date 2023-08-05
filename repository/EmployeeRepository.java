package com.mtco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mtco.domain.Employee;
import com.mtco.dto.EmployeeDTO;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	boolean existsByEmail(String email);
	
	@Query("SELECT new com.mtco.dto.EmployeeDTO(e) FROM Employee e WHERE e.id=:id")
	Optional<EmployeeDTO> findEmployeeDTOById(@Param("id") Long id);
	
}
