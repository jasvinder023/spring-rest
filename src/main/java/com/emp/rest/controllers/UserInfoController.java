package com.emp.rest.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.emp.rest.entity.UserInfo;
import com.emp.rest.repository.UserInfoRepository;
import com.emp.rest.service.UserInfoDetailsService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserInfoController {
	
	@Autowired
	private UserInfoDetailsService userInfoDetailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	//= new BCryptPasswordEncoder();

	
	@GetMapping("/user-list")
	public List<UserInfo> fetchAllUsers(){
		
		return userInfoDetailService.findAll();
	}
	
	// add mapping for PUT /employees - update existing employee	
		@PutMapping("/user-edit")
		public UserInfo updateUser(@RequestBody UserInfo theUser) {
			
			
			userInfoDetailService.saveUser(theUser);
			
			return theUser;
		}
	
		// add mapping for GET 
		@GetMapping("/getSingleUser/{userId}")
		public UserInfo getSingleUser(@PathVariable int userId) {
			
			UserInfo userTemp = userInfoDetailService.findUserById(userId);
			
			if (userTemp == null) {
				throw new RuntimeException("User id not found - " + userId);
			}
			
			return userTemp;
		}	
	@DeleteMapping("/user-delete/{userId}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long userId) {
		
		UserInfo userTemp = userInfoDetailService.findUserById(userId);
		
		// throw exception if null
		
		if (userTemp == null) {
			throw new RuntimeException("User id not found - " + userId);
		}
		
		userInfoDetailService.deleteUserById(userId);
		
		return ResponseEntity.noContent().build();
	}
	
	// add mapping for POST /employees - add new employee	
	@PostMapping("/user-create")
	public UserInfo addEmployee(@RequestBody UserInfo userTemp) {
		
		//theEmployee.setGender(theEmployee.getGender().equals("M")?"Male":"Female");
		userTemp.setId(null);
		
		String encodedPassword =passwordEncoder.encode(userTemp.getPassword());
		userTemp.setPassword(encodedPassword);
		//userTemp.setEnabled(newUser.getIsActive().equals("YES")?true:false);
		userTemp.setCreationDate(new Date());
			//userInfoRepository.save(newUser);

		
		userInfoDetailService.saveUser(userTemp);
		
		return userTemp;
	}
	
	// add mapping for PUT /employees - update existing employee	
	@PutMapping("/user-update/{userId}")
	public UserInfo updateEmployee( @PathVariable Long userId,@RequestBody UserInfo userTemp) {
		
		userTemp.setId(userId);
		userTemp.setCreationDate(new Date());
		userInfoDetailService.saveUser(userTemp);
		
		return userTemp;
	}	
	


}
