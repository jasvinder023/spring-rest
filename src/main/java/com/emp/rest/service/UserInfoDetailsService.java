package com.emp.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emp.rest.entity.Employee;
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
	// @Transactional(propagation=Propagation.REQUIRED, readOnly=true,
	// noRollbackFor=Exception.class)



	@Override
	// @Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = userInfoRepository.findByUsername(username);

		return userInfo;
	}

	public List<UserInfo> findAll() {
		return userInfoRepository.findAll();
	}

	public void saveUser(UserInfo theUser) {
		userInfoRepository.save(theUser);
		
	}
	public UserInfo findUserById(long userId) {
		Optional<UserInfo> result = userInfoRepository.findById(userId);
		UserInfo userTemp = null;

		if (result.isPresent()) {
			userTemp = result.get();
		} else {
			// we didn't find the employee
			throw new RuntimeException("Did not find user id - " + userId);
		}

		return userTemp;
	}
	public void deleteUserById(long userId)
	{
		 userInfoRepository.deleteById(userId);
	}

}