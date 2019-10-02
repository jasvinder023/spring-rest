package com.emp.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.rest.entity.Employee;
import com.emp.rest.service.EmployeeService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	// expose "/employees" and return list of employees
	@GetMapping("/emp/{username}/list")
	public List<Employee> findAll(@PathVariable String username) {
		return employeeService.findAll();
	}

	// add mapping for GET 
	@GetMapping("/emp/{username}/{employeeId}")
	public Employee getEmployee(@PathVariable String username,@PathVariable int employeeId) {
		
		Employee theEmployee = employeeService.findById(employeeId);
		
		if (theEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		
		return theEmployee;
	}
	
	// add mapping for POST /employees - add new employee	
	@PostMapping("/emp/{username}")
	public Employee addEmployee(@PathVariable String username,@RequestBody Employee theEmployee) {
		
		theEmployee.setGender(theEmployee.getGender().equals("M")?"Male":"Female");
		theEmployee.setEmpId(null);
		
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	// add mapping for PUT /employees - update existing employee	
	@PutMapping("/emp/{username}")
	public Employee updateEmployee(@PathVariable String username,@RequestBody Employee theEmployee) {
		
		employeeService.save(theEmployee);
		
		return theEmployee;
	}	
	
	// add mapping for DELETE /employees/{employeeId} - delete employee	
	@DeleteMapping("/emp/{username}/{employeeId}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable String username,@PathVariable int employeeId) {
		
		Employee tempEmployee = employeeService.findById(employeeId);
		
		// throw exception if null
		
		if (tempEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		
		employeeService.deleteById(employeeId);
		
		return ResponseEntity.noContent().build();
	}
	
}










