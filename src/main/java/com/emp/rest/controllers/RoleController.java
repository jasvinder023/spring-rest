package com.emp.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.rest.entity.Role;
import com.emp.rest.entity.UserInfo;
import com.emp.rest.service.RoleService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	@GetMapping("/fetch-roles")
	public List<Role> fetchAllUsers(){
		
		return roleService.fetchAllRoles();
	}
}
