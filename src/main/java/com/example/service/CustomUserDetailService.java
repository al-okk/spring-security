package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.CustomUser;
import com.example.entity.Employee;
import com.example.repository.EmployeeRepo;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeRepo.findByEmail(username);
		if(employee==null) {
			throw new UsernameNotFoundException("User not found");
		}else {
			return new CustomUser(employee);
		}
	}

}
