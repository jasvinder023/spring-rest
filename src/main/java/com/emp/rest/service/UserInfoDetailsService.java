package com.emp.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emp.rest.entity.UserInfo;
import com.emp.rest.repository.UserInfoRepository;



@Service
public class UserInfoDetailsService implements UserDetailsService {

	@Autowired
	UserInfoRepository userInfoRepository;
	

//@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
//				.filter(user -> user.getUsername().equals(username)).findFirst();
//
//		if (!findFirst.isPresent()) {
//			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
//		}
//
//		return findFirst.get();
//	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo=userInfoRepository.findByUsername(username);
		
		return userInfo;
	}

}