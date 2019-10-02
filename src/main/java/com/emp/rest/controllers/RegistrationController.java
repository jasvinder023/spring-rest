package com.emp.rest.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.emp.rest.entity.UserInfo;

import com.emp.rest.repository.UserInfoRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RegistrationController {

	@Autowired
	UserInfoRepository userInfoRepository;

	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private Logger logger = Logger.getLogger(getClass().getName());

	@PostMapping("/register/createUser")
	public ResponseEntity<UserInfo> createUser(@RequestBody UserInfo newUser) {

		System.out.println("user" + newUser.getUsername() + "--" + "Pass" + newUser.getPassword());
		// encrypt the password
		String encodedPassword = passwordEncoder.encode(newUser.getPassword());

		UserInfo tempUser = new UserInfo(newUser.getUsername(), encodedPassword, null, null, null, new Date());

		userInfoRepository.save(tempUser);

		return new ResponseEntity<UserInfo>(newUser, HttpStatus.OK);
	}

}
