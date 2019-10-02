package com.emp.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emp.rest.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!
	
}
