package com.emp.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.emp.rest.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{
	
	UserInfo findByUsername(@Param("username")String username);


}
