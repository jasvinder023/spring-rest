package com.emp.rest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DummyPasswordCreation {

	private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public static void main(String [] args) {
		System.out.println(passwordEncoder.encode("jasvinder"));

	}
}
