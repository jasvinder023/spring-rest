package com.emp.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.rest.entity.Role;
import com.emp.rest.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	@Override
	public List<Role> fetchAllRoles() {
		return roleRepository.findAll();
	}
	

}
